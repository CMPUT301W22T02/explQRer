package com.example.explqrer;

import android.app.AlertDialog;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class GameCodeFragment extends DialogFragment {
    private static final String HASH = "Hash";
    private static final String LOCATION = "Location";
    private Bitmap codeImage;

    private Button deleteButton;

    public interface deleteCodeListener {

    }

//      TODO: uncomment after merging with maps stuff
//    public static GameCodeFragment newInstance(GameCode.CodeLocation codeLocation) {
//        Bundle args = new Bundle();
//        args.putSerializable(HASH, codeLocation.hash);
//        args.putParcelable(LOCATION, codeLocation.location);
//        GameCodeFragment fragment = new GameCodeFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    public static GameCodeFragment newInstance(String hash,Bitmap codeImage, int codePts, String codeDesciption) {
        Bundle args = new Bundle();
        args.putString("Hash",hash);
        args.putParcelable("Image", codeImage);
        args.putInt("Points", codePts);
        args.putString("Description", codeDesciption);
        GameCodeFragment fragment = new GameCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_gamecode, null);


        String codeHash = getArguments().getString("Hash");
        Bitmap codeImage = getArguments().getParcelable("Image");
        String codeDescription = getArguments().getString("Description");
        int codePoints = getArguments().getInt("Points");
        String completeDescription = codePoints+ " pts; "+ codeDescription;

        ImageView fragmentImageView = view.findViewById(R.id.gamecode_image);
        TextView fragmentDescriptionView = view.findViewById(R.id.gamecode_description);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bitmap scaledImage = Bitmap.createScaledBitmap(codeImage, fragmentImageView.getWidth(), fragmentImageView.getHeight(), true);
                fragmentImageView.setImageBitmap(scaledImage);
                System.out.println(completeDescription);
                fragmentDescriptionView.setText(completeDescription);
            }
        }, 500);


//        deleteButton = view.findViewById(R.id.whatever);

//        if (getActivity() instanceof MapActivity) {
//            String hash = (String) getArguments().get(HASH);
//            Location location = (Location) getArguments().get(LOCATION);
//
//            deleteButton.setVisibility(View.GONE);
//        } else if(something) {
//
//        } else {
//
//        }






        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setView(view)
                .setPositiveButton("hash", null)
                .create();
    }
}
