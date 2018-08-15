package com.tele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WrongSort {
    public static List<Integer> readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        List<Integer> data = new ArrayList<Integer>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                if(line>1)
                    data.add(Integer.parseInt(tempString));
                line++;
            }
            System.out.println("count:" + data.size());
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return data;
    }
    public static void main(String[] args){
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date starttime = new Date(System.currentTimeMillis());
        String startStr = sdFormatter.format(starttime);
        long start = WrongSort.getCurrentTime();
        String filePath = "E:\\yanxj\\inputtest1.txt";
        List<Integer> data = WrongSort.readFileByLines(filePath);
        List<Map<Integer,Object>> resultList = WrongSort.getWrongSort(data);
        for(Map<Integer,Object> item:resultList){
            System.out.println(item.entrySet());
        }
        long end = WrongSort.getCurrentTime();
        System.out.println(end-start);
        Date endtime = new Date(System.currentTimeMillis());
        String endStr = sdFormatter.format(starttime);
        System.out.println("startStr:"+startStr);
        System.out.println("endStr:"+endStr);
    }
    public static List<Map<Integer,Object>> getWrongSort(List<Integer> arrayList){
        List<Map<Integer,Object>> resultList = new ArrayList<Map<Integer, Object>>();
        for(int i=0;i<arrayList.size()-1;i++){
            for(int j=i+1;j<arrayList.size();j++){
                System.out.println(arrayList.get(i)%arrayList.get(j));
                if(arrayList.get(i)>arrayList.get(j)*(1.05)){
                    Map<Integer,Object> itemMap = new HashMap<Integer, Object>();
                    itemMap.put(arrayList.get(i),arrayList.get(j));
                    resultList.add(itemMap);
                }
//                if(arrayList.get(i)%arrayList.get(j)==0?arrayList.get(i)/arrayList.get(j)>1.05:arrayList.get(i)/arrayList.get(j)>=1.05){
//                    Map<Integer,Object> itemMap = new HashMap<Integer, Object>();
//                    resultList.add(itemMap);
//                }
            }
        }
        return resultList;
    }
    public static long getCurrentTime(){
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date nowTime = new Date(System.currentTimeMillis());
        System.out.println(sdFormatter.format(nowTime));
        return System.currentTimeMillis();
    }
}
