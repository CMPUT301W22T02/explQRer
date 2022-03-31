package com.example.explqrer;


import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class GameCodeFragment extends DialogFragment {
    private static final String HASH = "Hash";
    private static final String LOCATION = "Location";

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

    public static GameCodeFragment newInstance(String hash) {
        Bundle args = new Bundle();
        args.putString("Hash",hash);

        GameCodeFragment fragment = new GameCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_gamecode, null);
        String hash = getArguments().getString("Hash");
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
                .setPositiveButton(hash, null)
                .create();
    }
}
