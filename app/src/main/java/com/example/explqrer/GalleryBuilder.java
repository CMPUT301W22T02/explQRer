package com.example.explqrer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 * Builds the Gallery with the gridView and the updated listOfImages to populate the gallery
 */
public class GalleryBuilder {
    /**
     * This populates the gallery with a GalleryAdapter
     *  Link: https://www.androidauthority.com/how-to-build-an-image-gallery-app-718976/
     *  Author: Adam Sinicki
     */
    public static void populateGallery(PlayerProfile player, RecyclerView galleryRecyclerView,
                                       Context context, GameCodeFragment.GameCodeFragmentHost host){

        //set up the RecyclerView for the gallery
        galleryRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,4);
        galleryRecyclerView.setLayoutManager(gridLayoutManager);

        ArrayList<GalleryListItem> listOfImages = updateImageList(player);

        GalleryAdapter galleryListAdapter = new GalleryAdapter(context, listOfImages, host);
        galleryRecyclerView.setAdapter(galleryListAdapter);
    }

    /**
     * updates the list of images that the gallery displays
     * @param player
     *    a PlayerProfile object
     * @return
     *    the list of images of the QRs
     */
    public static ArrayList<GalleryListItem> updateImageList(PlayerProfile player){

        HashMap<String,GameCode> codes = player.getCodes();
        Collection<GameCode> qrCodes = codes.values();
        System.out.println("this is updateImageList "+ qrCodes);
        ArrayList<GalleryListItem> listOfImages = new ArrayList<>();

        for(GameCode qr : qrCodes ){

            GalleryListItem galleryListItem = new GalleryListItem();
            if (qr.getPhoto() == null) {
                galleryListItem.setImage(UserProfileActivity.defaultQr);
            } else {
                galleryListItem.setImage(qr.getPhoto());
            }
            galleryListItem.setHashCode(qr.getSha256hex());
            listOfImages.add(galleryListItem);
        }
        return listOfImages;
    }



}
