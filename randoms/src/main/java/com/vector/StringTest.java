package com.vector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringTest {
    public String greater(String a,String b) {
        char[] arrA = a.toCharArray();
        char[] arrB = b.toCharArray();
        System.out.println(Arrays.compare(arrA, arrB));
        return Arrays.compare(arrA, arrB)>0?"Yes":"No";  
    }

    public static void splitToken(String a) {
      a = a.trim();
      String[] arr = a.split("[^\\p{Alpha}]+");
      int length = a.isEmpty()?0:arr.length;
      System.out.println(length);
      for(String s : arr) 
        System.out.println(s);
    }
    public static void main(String[] args) throws IOException {
      splitToken("     hello");
      try {
        Pattern.compile("aj");
      }catch(PatternSyntaxException e) {
        System.err.println("Invalid");
      }
    }
}

