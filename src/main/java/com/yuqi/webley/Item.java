package com.yuqi.webley;

/**
 * Created by yuki on 8/30/17.
 */
public  class Item {

    private String name;
    private double price;
    Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public double getval(){
        return price;
    }
    public String getName(){
        return name;
    }

}
