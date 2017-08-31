package com.yuqi.webley;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yuki on 8/30/17.
 */

public class webley {

    public static List<List<String>> order(double[] prices, String[] names, double target) {
        List<List<String>> res = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        help(prices, names, target, res, temp, 0);
        return res;
    }
    //assume each dish can only be ordered one time for each combination
    //use backtrack to get the reasult
    private static void help(double[] prices, String[] names, double target, List<List<String>> res, List<String> temp, int index){
        if(target<0.0) return;
        else if(target==0.0){
            res.add(new ArrayList<>(temp));
        }else {
            for(int i=index; i < prices.length;i++){
                temp.add(names[i]);
                help(prices, names,target-prices[i], res, temp, i+1);
                temp.remove(temp.size()-1);
            }
        }
    }

}

