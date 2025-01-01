package com.ll.chat_pr.domain.home.main.Utility;

import java.util.LinkedHashMap;
import java.util.Map;

public class Ut {
    public static <K,V> Map<K,V> mapOf(Object... args){
        Map<K,V> map = new LinkedHashMap<>();
        int size = args.length/2; //키값 키값 이런 식으로 들어온다고 가정

        for(int i=0;i<size;i++){
            int keyIdx = i*2;
            int valueIdx = keyIdx+1;
            K key= (K)args[keyIdx];
            V value = (V)args[valueIdx];
            map.put(key,value);
        }

        return map;
    }
}
