package com.yuqi.webley;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.yuqi.webley.webley.order;

/**
 * Created by yuki on 8/30/17.
 */
public class main {
    public static void main(String[] args) {
        //parsing variable
        String csvFile = args[0];
        BufferedReader br = null;
        String line = "";
        boolean head = true;
        //value
        List<Item> items = new ArrayList<>();
        double target = -1;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                //read csv file line by line
                String[] a = line.split(",");
                if (head) {
                    //target price is always the first number
                    target = Double.parseDouble(a[1].trim().substring(1, a[1].trim().length()));
                } else {
                    //parse name and price, saved as Item
                    double price = Double.parseDouble(a[1].substring(1, a[1].length()));
                    items.add(new Item(a[0], price));
                }
                head = false;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //sort the prices
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (o1.getval() > o2.getval()) return 1;
                else if (o1.getval() < o2.getval()) return -1;
                else return 0;
            }
        });

        double[] price = new double[items.size()];
        String[] name = new String[items.size()];
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) != null) {
                price[i] = items.get(i).getval();
            }
            if (items.get(i) != null) {
                name[i] = items.get(i).getName();
            }
        }


        List<List<String>> res = order(price, name, target);
        if (res.isEmpty()) System.out.println("no combination of dishes equal to the target price");
        for (List<String> a : res) {
            for (String s : a) {
                System.out.print(s + "    ");
            }
            System.out.println();
        }
    }
}
