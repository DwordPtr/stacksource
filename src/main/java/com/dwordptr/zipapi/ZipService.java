package com.dwordptr.zipapi;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ZipService {
    private static final Set<Integer> zipCodes = new HashSet<>();
    public boolean isValidZip(String param){
        boolean isInt;
        try {
            int n = Integer.valueOf(param);
            return n > 0 && param.length() ==5;
        } catch (Exception e){
            return false;
        }
    }

    /*Note this averages O(k) but it can theoretically degrade to O(n) */
    public boolean inList(String param){
        return isValidZip(param) && zipCodes.contains(Integer.valueOf(param));
    }

    public boolean insert(String param){
        if(!isValidZip(param)){
            return false;
        } else if(inList(param)){
            return false;
        }

        return zipCodes.add(Integer.valueOf(param));
    }

    public String getZipCodeString(){
        Map<Integer, Integer> zipCodeRunMap = new HashMap<>();
        zipCodes.stream()
                .filter(x -> !zipCodes.contains(x-1))
                .forEach(x -> {
            int ri = 0;
            while(zipCodes.contains(x + ri)){
                ri++;
            }
            zipCodeRunMap.put(x, ri -1);
        });
        return zipCodeRunMap.keySet().stream()
                .map(k -> {
                    int run = zipCodeRunMap.get(k);
                    if(zipCodeRunMap.get(k) == 0){
                        return String.valueOf(k);
                    }
                    return String.format("%d-%d", k, k+zipCodeRunMap.get(k));
                }).collect(Collectors.joining(", "));
    }

    public boolean delete(String param){
        if(!isValidZip(param) || !inList(param)){
            return false;
        }
        return zipCodes.remove(Integer.valueOf(param));
    }
}
