package com.example.explqrer;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class LargerImageFragment extends Fragment {
    private ImageView enlargedImage;
    private TextView imageDescription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_larger_image,null);

        enlargedImage = view.findViewById(R.id.expanded_image);
        imageDescription = view.findViewById(R.id.image_description);

    }

    interface enlargedImageListner{
        void getImageToEnlargen(String text);
    }

    @Override
    public void onAttach(Context context, Bitmap smallImage) {
        super.onAttach(context);
        enlargedImage.setImageBitmap(smallImage);

    }
}
