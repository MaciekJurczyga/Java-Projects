package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Integer> placeWaterTanks(String s) {
        List<Integer> tankIndexes = new ArrayList<>();
        int n = s.length();
        boolean[] isWaterCollected = new boolean[n];
        for(int i = 1; i<n; i++){
            if(i != n-1 && s.charAt(i-1) == 'H' && s.charAt(i+1) == 'H' && s.charAt(i) == '-'
            && !isWaterCollected[i-1] && !isWaterCollected[i+1]){
                tankIndexes.add(i);
                isWaterCollected[i-1] = true;
                isWaterCollected[i+1] = true;
            }
        }
        for(int i = 0; i<n; i++){
            if(s.charAt(i) == 'H' && !isWaterCollected[i]){
                if(i!=0 && s.charAt(i-1) == '-'){
                    tankIndexes.add(i-1);
                }
                if(i!=n-1 && s.charAt(i+1) == '-'){
                    tankIndexes.add(i+1);
                }
            }
        }
        return tankIndexes;
    }

    public static void main(String[] args) {
        String s = "---H-H-H-H";
        List<Integer> tankIndexes = placeWaterTanks(s);
        if (tankIndexes.isEmpty()) {
            System.out.println("No water tanks needed.");
        } else {
            System.out.println("Water tanks should be placed at indexes:");
            for (Integer index : tankIndexes) {
                System.out.println(index);
            }
        }
    }
}