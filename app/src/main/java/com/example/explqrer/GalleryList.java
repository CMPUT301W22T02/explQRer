package com.example.explqrer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Link: https://www.androidauthority.com/how-to-build-an-image-gallery-app-718976/
 * Author: Adam Sinicki
 * populates the Gallery images list
 */
public class GalleryList extends AppCompatActivity {

    public static ArrayList<GalleryListItem> updateGallery(PlayerProfile player){

        HashMap<GameCode,GameCode> qrCodes = player.getCodes();
        Set<GameCode> qrCodesSet = qrCodes.keySet();
        System.out.println("user list: "+ qrCodesSet);

        //loop through all the imagePoints and imageIds populate galleryListItem objects with the image's point value and the image
        ArrayList<GalleryListItem> listOfImages = new ArrayList<>();
        //loop through the QRs scanned by the player
        for(GameCode qr : qrCodesSet ){
            GalleryListItem galleryListItem = new GalleryListItem();

            //get the hash of the qr and set it to the galleryListItem
            galleryListItem.setHashCode(qr.getSha256hex());
            //get image of the qr scanned and set the images
            galleryListItem.setImageId(qr.getPhoto());
            listOfImages.add(galleryListItem);
        }
        System.out.println("before listof images");
        return listOfImages;
    }
}