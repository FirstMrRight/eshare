package com.example.ltx.eshare.stream;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Liutx
 * @date 2021/2/10 22:11
 * @Description
 */
public class HashSetTest {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet<String>();
//        hashSet.add("C");
//        hashSet.add("Java");
//        hashSet.add("C#");
//        hashSet.add("Js");


        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("语文", 1);
        map.put("数学", 2);
        map.put("英语", 3);
        map.put("历史", 4);
        map.put("政治", 5);
        map.put("地理", 6);
        map.put("生物", 7);
        map.put("化学", 8);
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
