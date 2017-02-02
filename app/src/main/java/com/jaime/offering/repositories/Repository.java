package com.jaime.offering.repositories;

import com.jaime.offering.R;
import com.jaime.offering.models.Offer;

import java.util.ArrayList;

/**
 * Created by jaime on 01/02/2017.
 */

public class Repository extends ArrayList<Offer> {
    private static Repository instance;


    public static Repository getInstance() {
        if (instance == null)
            instance = new Repository();

        return instance;
    }


    private Repository() {
        add(new Offer("Espejo redondo", "Ikea", "01/02/2017", R.mipmap.ic_home, R.color.importance_normal));
        add(new Offer("Bicicleta de paseo", "Decathlon", "27/01/2017", R.mipmap.ic_sports, R.color.importance_low));
        add(new Offer("iPhone 7", "Apple Store", "15/01/2017", R.mipmap.ic_mobile, R.color.importance_high));
    }


    public void addOffer(Offer offer) {
        add(offer);
    }
}
