/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.alibaba.datax.transport.transformer.maskingMethods.anonymity;

import static java.lang.Math.min;
import java.util.Random;

/**
 * Created by LabUser on 2018/5/8.
 */
public class PrefixPreserveMasker {
    public static char charMap(char origin){
        Random ran1 = new Random((int) origin);
        if(Character.isDigit(origin)){
            int randIntResult = ran1.nextInt(10);
            origin = (char) ('0' + randIntResult);
            ran1.setSeed(randIntResult);
        }
        else if(Character.isLetter(origin)){
            int randIntResult = ran1.nextInt(26);
            int offset = 1 + ran1.nextInt(26);
            origin = origin<=90? (char)('A' + offset):(char)('a' + offset);
            ran1.setSeed(randIntResult);
        }
        return origin;
    }

    public static String randomInt(int numberLength){
        Random ran1 = new Random(numberLength);
        double basic = Math.pow(10, numberLength-1);
        int base = (int) basic;
        int result = base + ran1.nextInt(base*9 - 1);
        return Integer.toString(result);
    }

    /*
    * mask long integer.
    * */
    public static long mask(long origin, int preserveNum) throws Exception{
        long temp = origin;
        // when origin number is smaller than zero.
        if(temp < 0){
            origin = 0 - origin;
        }
        String ori_str = String.valueOf(origin);
        char[] result = new char[ori_str.length()];
        String randomSuffix = randomInt(ori_str.length() - preserveNum);
        for(int i=0;i<ori_str.length();i++){
            // result[i] = i<preserveNum ? ori_str.charAt(i):charMap(ori_str.charAt(i));
            result[i] = i < preserveNum ? ori_str.charAt(i):randomSuffix.charAt(i-preserveNum);
        }
        if (temp < 0){
            return 0 - Long.valueOf(String.valueOf(result));
        }
        return Long.valueOf(String.valueOf(result));
    }

    public static String mask(String origin, int preserveNum) throws Exception{
        preserveNum = min(preserveNum, origin.length());
        char[] str = new char[origin.length()+1];
        int i;
        for(i=0;i<preserveNum;i++){
            str[i] = origin.charAt(i);
        }
        for(;i<origin.length();i++){
            str[i] = charMap(origin.charAt(i));
        }
        return String.valueOf(str);
    }
}
