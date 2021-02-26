package com.luckysix.Numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by AsX on 7/3/2017.
 */

public class urna {

    private long seed = System.nanoTime();
    private final int marimeUrna = 49;
    public ArrayList<number> Numere;

    public urna(){
        Numere = new ArrayList<number>();
        for(int i=0;i<marimeUrna;i++)
            Numere.add(new number(i));
    }

    public void shuffle(){
        Collections.shuffle(this.Numere, new Random(seed));

    }

    public void get(ArrayList<number> Numere, ArrayList<number> Extras){
        Extras.add(Numere.get(0));
        Numere.remove(0);
    }

    public int getMarimeUrna(){
        return marimeUrna;
    }

}
