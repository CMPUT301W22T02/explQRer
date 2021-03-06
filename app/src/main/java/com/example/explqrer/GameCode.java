package com.example.explqrer;

import static com.google.common.math.IntMath.pow;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Store information about a code scanned by a player, so that it
 * can be displayed to the player. Also updates the database on creation
 * with a new image and/or location.
 */
public class GameCode implements Serializable {
    private final String sha256hex;

    private final int score;
    private double lon, lat;
    private boolean locationRecorded = false;
    private ProxyBitmap photo;
    private String description;
    private static HashFunction hash =  Hashing.sha256();

    /**
     * Constructor for GameCode.
     * @param barcode   a scanned barcode to be recorded
     * @param location  geolocation information. Can be null
     * @param photo     an image of the location of the barcode. Can be null
     */
    public GameCode(@NonNull String barcode, @Nullable Location location, @Nullable Bitmap photo) {
        sha256hex = hashCode(barcode);
        score = calculateScoreFromRaw(barcode);
        if (location != null) {
            locationRecorded = true;
            lon = location.getLongitude();
            lat = location.getLatitude();
        }
        if (photo != null) {
            setPhoto(photo);
        }
    }
  
    public GameCode(String hash) {
        sha256hex = hash;
        score = calculateScoreFromHash(hash);
    }

    /**
     * Calculate the score of the barcode data
     * @return The score as an integer
     */
    public static int calculateScoreFromRaw(String rawValue) {
        return calculateScoreFromHash(hashCode(rawValue));
    }

    /**
     * Calculate the score of the hash string
     * @return The score as an integer
     */
    public static Integer calculateScoreFromHash(String sha256hex) {
        int ret = 0;
        int repeats = 0;
        char prevChar = sha256hex.charAt(0);

        for (int i = 1; i < sha256hex.length(); i++) {
            if (sha256hex.charAt(i) == prevChar) {
                repeats++;
            }
            else {
                if (repeats > 0) {
                    ret += pow(Integer.parseInt(String.valueOf(prevChar), 16), repeats);
                }
                repeats = 0;
            }
            prevChar = sha256hex.charAt(i);
        }
        if (repeats > 0) {
            ret += Integer.parseInt(String.valueOf(prevChar), 16);
        }
        if (ret == 0) {
            ret = 1;
        }
        return ret;
    }

    public static String hashCode(String rawValue) {
        return hash.hashString(rawValue, StandardCharsets.US_ASCII).toString();
    }

    /**
     * Get the score
     * @return The score as an integer
     */
    public int getScore() {
        return score;
    }

    /**
     * Get the hash
     * @return The hash as a string
     */
    public String getSha256hex() {
        return sha256hex;
    }

    /**
     * Get the stored location of the code
     * @return
     */
    public Location getLocation() {
        Location location = new Location("");
        location.setLongitude(lon);
        location.setLatitude(lat);
        return locationRecorded ? location : null;
    }

    /**
     * Get the bitmap of the image stored
     * @return
     */
    public Bitmap getPhoto() {
        return photo == null ? null : photo.getBitmap();
    }

    /**
     * Set the location info
     * @param location
     */
    public void setLocation(Location location) {
        if (location != null) {
            lon = location.getLongitude();
            lat = location.getLatitude();
            locationRecorded = true;

        }
    }

    /**
     * Set the photo with a bitmap
     * @param photo
     */
    public void setPhoto(Bitmap photo) {
        if (photo == null) { return; }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        this.photo = new ProxyBitmap(BitmapFactory.decodeByteArray(stream.toByteArray(), 0, stream.size()));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return true if the object equals the GameCode
     * @param o the object to check
     * @return true if they are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCode gameCode = (GameCode) o;
        return getSha256hex().equals(gameCode.getSha256hex());
    }

    @Override
    public int hashCode() {
        return Objects.hash(sha256hex);
    }

    public static class CodeLocation implements Parcelable {
        private String hash;
        private Location location;

        public Location getLocation() {
            return location;
        }

        public String getHash() {
            return hash;
        }

        public CodeLocation(String hash, Location location) {
            this.hash = hash;
            this.location = location;
        }

        protected CodeLocation(Parcel in) {
            hash = in.readString();
            location = in.readParcelable(Location.class.getClassLoader());
        }

        public static final Creator<CodeLocation> CREATOR = new Creator<CodeLocation>() {
            @Override
            public CodeLocation createFromParcel(Parcel in) {
                return new CodeLocation(in);
            }

            @Override
            public CodeLocation[] newArray(int size) {
                return new CodeLocation[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(hash);
            parcel.writeParcelable(location, i);
        }
    }
}
