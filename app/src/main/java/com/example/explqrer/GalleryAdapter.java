package com.example.explqrer;


import static com.example.explqrer.R.id.image;
import static com.example.explqrer.R.id.image_gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Link: https://www.androidauthority.com/how-to-build-an-image-gallery-app-718976/
 * Author: Adam Sinicki
 * This is a class for a custom adapter for the recycler View
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private ArrayList<GalleryListItem> galleryList;
    private Context context;

    /**
     * Constructor for the class
     * @param context
     *   context is where the adapter is being used
     * @param galleryList
     *  galleryList is the array list for the adapter
     */
    public GalleryAdapter(Context context, ArrayList<GalleryListItem> galleryList) {
        this.galleryList = galleryList;
        this.context = context;
        System.out.println("in adapter");
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
     * This class creates the ViewHolder which makes it easier to iterate through images
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        public ViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.image);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context,"clicked="+ getBindingAdapterPosition(),Toast.LENGTH_SHORT).show();
                    ImageView enlarged_image =  view.findViewById(R.id.expanded_image);

//                    enlarged_image.setVisibility(View.VISIBLE);
                    System.out.println(galleryList.get(getBindingAdapterPosition()).getImage());
                    enlarged_image.setImageBitmap(galleryList.get(getBindingAdapterPosition()).getImage());
                }
            });
        }
    }

}
