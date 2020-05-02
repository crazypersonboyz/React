/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.server;


import com.example.test.GetLongestLengthRequest;
import com.example.test.GetLongestLengthResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author neera
 */
public class UtilHelper {
    
    public static Map<String, Map<GetLongestLengthRequest,GetLongestLengthResponse>> jobMap;
    public static AtomicInteger atomicInt;
    public static int count=0;
    
    static {
        atomicInt  = new AtomicInteger(0);
        jobMap = new HashMap<String,Map<GetLongestLengthRequest,GetLongestLengthResponse>>();
        
    }
    
    public static boolean findDuplicate(String str) {

        char[] inp = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (inp[i] == inp[j]) {
                    return false;
                    // break;
                }
            }
        }
        return true;
    }

    public static List concatinateString(List<String> lst) {

        List<String> retList = new ArrayList<String>();
        for (int i = 0; i < lst.size(); i++) {
            for (int j = 0; j < lst.size(); j++) {
                if (lst.get(i).toString() != lst.get(j).toString()) {
                    retList.add(lst.get(i).toString() + lst.get(j).toString());
                }
            }
        }

        return retList;
    }

    public static int getLongestStringLength(List<String> lst) {
        int length = 0;
        for (String str : lst) {
            if (findDuplicate(str)) {
                if (str.length() > length) {
                    length = str.length();
                }

            }
        }

        return length;
    }

    public static List<String> generatePerm(final int n, final List<String> syllables, final String currentWord, List<String> permList) { // example of N = 3

        if (n == 0) {
            permList.add(currentWord);
            System.out.println(currentWord);
        } else {
            for (int i = 0; i < syllables.size(); i++) {
                generatePerm(n - 1, syllables, currentWord + syllables.get(i), permList);
            }
        }
        return permList;
    }

    public static int getRandomNumber(){
        // AtomicInteger val  = new AtomicInteger(0); 
      return atomicInt.getAndIncrement();
    }
}
