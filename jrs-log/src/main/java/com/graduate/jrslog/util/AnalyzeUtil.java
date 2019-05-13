package com.graduate.jrslog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AnalyzeUtil{


    public static List<String> analyzeMessage(String message){
        ArrayList<String> list = new ArrayList<>();
        int authorityIndex = message.indexOf("Authority:");
        int userNameIndex = message.indexOf("UserName:");
        int messageIndex = message.indexOf("Message:");
        String authority = message.substring(authorityIndex+10,userNameIndex-1);
        String username = message.substring(userNameIndex+9,messageIndex-1);
        String searchMessage = message.substring(messageIndex+8);
        list.add(username);
        list.add(searchMessage);
        list.add(authority);
        return list;
    }
    public static List<List<String>> analyzeLog() throws IOException {
        ArrayList<String> populaceList = new ArrayList<>();
        ArrayList<String> judicialOfficerList = new ArrayList<>();
        HashMap<String, Integer> populaceMap = new HashMap<>();
        HashMap<String, Integer> judicialOfficerMap = new HashMap<>();
        File file = new File("E:\\opt\\spring\\log\\spring.log");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String log;
        String authority;
        String message;
        while ((log = bufferedReader.readLine())!=null){
            if(log.contains("Search-")){
                int authorityIndex = log.indexOf("Authority:");
                int messageIndex = log.indexOf("Message:");
                authority = log.substring(authorityIndex+10,messageIndex);
                message = log.substring(messageIndex+8);
                if(authority.equals("populace")){
                    if(populaceMap.containsKey(message)){
                        populaceMap.put(message,populaceMap.get(message)+1);
                    }else{
                        populaceMap.put(message,1);
                    }
                }else if(authority.equals("judicialOfficer")){
                    if(judicialOfficerMap.containsKey(message)){
                        judicialOfficerMap.put(message,populaceMap.get(message)+1);
                    }else{
                        judicialOfficerMap.put(message,1);
                    }
                }
            }
        }
        ArrayList<Map.Entry<String, Integer>> populaceEntries = new ArrayList<>(populaceMap.entrySet());
        ArrayList<Map.Entry<String, Integer>> judicialOfficerEntries = new ArrayList<>(judicialOfficerMap.entrySet());
        desMap(populaceEntries);
        desMap(judicialOfficerEntries);
        for (int i=0;i<judicialOfficerEntries.size();i++){
            if (i>5)
                break;
            judicialOfficerList.add(judicialOfficerEntries.get(i).getKey());
        }
        for (int i=0;i<populaceEntries.size();i++){
            if(i>5)
                break;
            System.out.println(populaceEntries.get(i).getKey()+""+populaceEntries.get(i).getValue());
            populaceList.add(populaceEntries.get(i).getKey());
        }
        ArrayList<List<String>> lists = new ArrayList<>();
        lists.add(populaceList);
        lists.add(judicialOfficerList);
        return lists;
    }
    private static void desMap(List<Map.Entry<String, Integer>> list){
        list.sort((Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) -> {
            if (o1.getValue().compareTo(o2.getValue()) < 0) {
                return 1;
            }
            if (o1.getValue().compareTo(o2.getValue()) == 0) {
                return 0;
            }
            return -1;
        });
    }

//    public static void main(String[] args) throws IOException {
//        analyzeLog();
//    }
}
