package com.jaime.offering.models;

import java.util.Comparator;

/**
 * Created by jaime on 01/02/2017.
 */

public class Offer {
    private String name;
    private String shop;
    private String date;
    private int image;
    private int importance;


    public Offer(String name, String shop, String date, int image, int importance) {
        this.name = name;
        this.shop = shop;
        this.date = date;
        this.image = image;
        this.importance = importance;
    }


    public static final Comparator<Offer> ORDER_ASC = new Comparator<Offer>() {
        @Override
        public int compare(Offer offer, Offer t1) {
            return offer.getName().compareToIgnoreCase(t1.getName());
        }
    };


    public static final Comparator<Offer> ORDER_DES = new Comparator<Offer>() {
        @Override
        public int compare(Offer offer, Offer t1) {
            return t1.getName().compareToIgnoreCase(offer.getName());
        }
    };


    public static final Comparator<Offer> ORDER_TYPE = new Comparator<Offer>() {
        @Override
        public int compare(Offer offer, Offer t1) {
            if (offer.getImage() < t1.getImage())
                return -1;
            else if (offer.getImage() == t1.getImage())
                return 0;
            else
                return 1;
        }
    };


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getShop() {
        return shop;
    }


    public void setShop(String shop) {
        this.shop = shop;
    }


    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public int getImage() {
        return image;
    }


    public void setImage(int image) {
        this.image = image;
    }


    public int getImportance() {
        return importance;
    }


    public void setImportance(int importance) {
        this.importance = importance;
    }
}
