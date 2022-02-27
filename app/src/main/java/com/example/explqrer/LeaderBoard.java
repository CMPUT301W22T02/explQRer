package com.example.explqrer;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.j256.ormlite.stmt.query.In;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LeaderBoard {
    private HashMap<Player, Integer> unsortedMap;
    private HashMap<Player, Integer> sortedMap;
    private Player topPlayer;

    // need to change the type if discuss the type of input
    // and what is the format of the data we want to store
    // how many table
    // one for each rank or three different
    public LeaderBoard(HashMap<Player, Integer> gameData) {
        this.unsortedMap = gameData;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public HashMap<Player, Integer> sort(HashMap<Player, Integer> unsortedMap) {
        // this return a sorted arraylist only contains
        // 6 player info with the hignest grade

        // create a list from elements of unsortedMap
        List<Map.Entry<Player, Integer>> list = new LinkedList<Map.Entry<Player, Integer>>(unsortedMap.entrySet());

        // sort the list based on the value
        Collections.sort(list, new Comparator<Map.Entry<Player, Integer>>() {
            @Override
            public int compare(Map.Entry<Player, Integer> t1, Map.Entry<Player, Integer> t2) {
                return t1.getValue().compareTo(t2.getValue());
            }
        });

        // top player
        topPlayer = list.get(0).getKey();

        // put data from list to hashmap
        sortedMap = new LinkedHashMap<Player, Integer>();
        for (Map.Entry<Player, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public Player getRank(){
        return this.topPlayer;
    }

}


