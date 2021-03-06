package com.example.explqrer;


import static com.example.explqrer.R.id.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * takes images and binds them to view holders
 * Link: https://www.androidauthority.com/how-to-build-an-image-gallery-app-718976/
 * Author: Adam Sinicki
 * This is a class for a custom adapter for the recycler View
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private static ArrayList<GalleryListItem> galleryList;
    private static Context context;
    private static GameCodeFragment.GameCodeFragmentHost host;
    private PlayerProfile player;
    private static GalleryAdapter galleryAdapterInstance;

    /**
     * Constructor for the class
     * @param context
     *   context is where the adapter is being used
     * @param galleryList
     *  galleryList is the array list for the adapter
     */
    public GalleryAdapter(Context context, ArrayList<GalleryListItem> galleryList,
                          GameCodeFragment.GameCodeFragmentHost host) {
        this.galleryList = galleryList;
        this.context = context;
        this.host = host;
        this.player = host.getPlayer();

    }

    /**
     *sets up the ViewHolder for the recycler View
     * @param viewGroup
     *    where the viewHolder will be grouped
     * @param viewGroup object and int i
     * @return ViewHolder with the defined view parameters
     */
    @NonNull
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_cell_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     *
     * @param viewHolder
     *      The ViewHolder to hold the
     * @param viewHolder
     *       What holds the images in the layout
     * @param i
     *      The index of the view
     *
     */
    @Override
    public void onBindViewHolder(GalleryAdapter.ViewHolder viewHolder, int i) {
        viewHolder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewHolder.image.setImageBitmap(galleryList.get(i).getImage());
    }

    /**
     * This gets how many items are in the adapter
     * @return size of the galleryList of type int
     */
    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    /**
     * remove the image in the galleryList
     */
    public void removeImage(GameCode codeImage){
        galleryList.remove(codeImage);

    }

    /**
     * This class creates the ViewHolder which makes it easier to iterate through images
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        public ViewHolder(View view) {
            super(view);

            image = view.findViewById(R.id.image);
            view.setOnClickListener(view1 -> {

                String codeHash = galleryList.get(getBindingAdapterPosition()).getHashCode();
                GameCode code = player.getCode(codeHash);
                host.createFragment(code.getSha256hex());
            });
        }
    }

    /**
     * get instance of the GalleryAdapater
     * @return galleryAdapterInstance
     */
    public static GalleryAdapter getInstance() {
        if (galleryAdapterInstance == null) {
            galleryAdapterInstance = new GalleryAdapter(context,galleryList,host);
        }
        return galleryAdapterInstance;
    }


}
