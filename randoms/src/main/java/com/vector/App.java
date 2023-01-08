package com.vector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static boolean isAnagramv2(String a,String b) {
        Map<Character,Integer> arrA = new HashMap<>();
        Map<Character,Integer> arrB = new HashMap<>();

        char newChar;
        a = a.toLowerCase();
        b = b.toLowerCase();
        for(int i=0;i<a.length();i++) {
            if(a.charAt(i)==' ') continue;
            newChar = a.charAt(i);
            if(arrA.containsKey(newChar)) {
                arrA.put(newChar,arrA.get(newChar).intValue()+1);
            }else {
                arrA.put(newChar,1);
            }
            
        }

        for(int i =0;i<b.length();i++) {
            if(b.charAt(i)==' ') continue;
            newChar = b.charAt(i);
            if(arrB.containsKey(newChar)) {
                arrB.put(newChar,arrB.get(newChar).intValue()+1);
            }else {
                arrB.put(newChar,1);
            }

        }

        for(Map.Entry<Character,Integer> setA : arrA.entrySet()) {
            if(!arrB.containsKey(setA.getKey())) return false;
            if(arrB.get(setA.getKey()) != setA.getValue()) return false;
        }
        return true;
    }
    public static boolean isAnagram(String a,String b) {

        a = a.toLowerCase();
        b = b.toLowerCase();
        int[] fCountA = new int[26];
        char[] arrA = new char[26];
        int[] fCountB = new int[26];
        char[] arrB = new char[26];
        int arrAlen = 0;
        int arrBlen = 0;

        boolean exists;
        int index = 0;
        int k = 0;
        for(int i=0;i<a.length();i++) {
            if(a.charAt(i)==' ') continue;
            exists = false;
            for(int j=0;j<arrAlen;j++){
                if(a.charAt(i)==arrA[j]) {
                    index = j;
                    exists = true;
                }
            }
            if(exists) {
                ++fCountA[index];
            }
            else {
                arrA[k] = a.charAt(i);
                fCountA[k]++;
                k++;
                arrAlen++;
            }
        }

        index = 0;
        k=0;
        for(int i=0;i<b.length();i++) {
            if(b.charAt(i)==' ') continue;
            exists = false;
            for(int j=0;j<arrBlen;j++){
                if(b.charAt(i)==arrB[j]) {
                    index = j;
                    exists = true;
                }
            }
            if(exists) {
                ++fCountB[index];
            }
            else {
                arrB[k] = b.charAt(i);
                fCountB[k]++;
                k++;
                arrBlen++;
            }
        }

        if(arrAlen !=arrBlen) return false;
        for(int i=0;i<arrAlen;i++) {
            for(int j=0;j<arrBlen;j++) {
                if(arrA[i] ==arrB[j]) {
                    if(fCountA[i] != fCountB[j]) {
                        return false;
                    }
                }
            }
        }
        // System.out.printf("%-15s%-15s%n","char","count");
        // for(int i=0;i<arrAlen;i++) {
        //     System.out.printf("%-15c%-15d%n",arrA[i],fCountA[i]);
        // }
        // System.out.println();
        // for(int i=0;i<arrBlen;i++) {
        //     System.out.printf("%-15c%-15d%n",arrB[i],fCountB[i]);
        // }
        return true;
    }


    public static boolean isAnagramv3(String a,String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
      
        if(a.length() != b.length()) return false;
        int countA,countB;
        for(int i =0;i<a.length();i++) {
            countA = 0;
            countB =0;
            for(int j=0;j<b.length();j++) {
                if(a.charAt(i) == a.charAt(j)) countA++;
                if(b.charAt(i) == b.charAt(j)) countB++;
            }
            if(countA !=countB) return false;
        }
        return true;
    }

    public static boolean isAnagramv4(String a,String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();

        char[] arrA = a.replaceAll(" ", "").toCharArray();
        char[] arrB = b.replaceAll(" ", "").toCharArray();

        Arrays.sort(arrA);
        Arrays.sort(arrB);
        return Arrays.equals(arrA, arrB);
    }

    public static void main(String[] args) {
        System.out.println(isAnagramv4("timus", "sumit"));
        System.out.println(isAnagramv4("To be or not to be that is the question whether tis nobler in the mind to suffer the slings and arrows of outrageous fortune",
         "In one of the Bards best thought of tragedies our insistent hero Hamlet queries on two fronts about how life turns rotten"));
    }
}
