package com.example.explqrer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LeaderBoardAdapter extends ArrayAdapter<Player>{
    // this arraylist only contains 6 player
    private ArrayList<Player> players;
    private Context context;
    public LeaderBoardAdapter(Context context, ArrayList<Player> players){
        // input arrayList is already sorted by calling sort method in LeaderBoard Class
        super(context, 0,players);
        this.context = context;
        this.players = players;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.leaderboard_content,parent,false);
        }

        Player player = players.get(position);

        TextView rankView = view.findViewById(R.id.rank_in_leaderboard);
        TextView nameView = view.findViewById(R.id.name_in_leaderboard);

        String rank = Integer.toString(position+1);
        String name = player.getName();

        if ((position+1) == 1){
            rankView.setText(rank+"st");
        }else if ((position+1) == 2){
            rankView.setText(rank+"nd");
        }else if ((position+1) == 3){
            rankView.setText(rank+"rd");
        }else{
            rankView.setText(rank+"th");
        }

        nameView.setText(name);

        return view;


    }

}