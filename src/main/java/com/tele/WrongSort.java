package com.tele;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class WrongSort {
    /**
     * 从文件中读取数据
     * @param fileName
     * @return List
     */
    public static List<Integer> readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        List<Integer> data = new ArrayList<Integer>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                if(line>1)
                    data.add(Integer.parseInt(tempString));
                line++;
            }
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

    /**
     * 创建输出流以写入读取的数据
     * @param path
     * @return OutputStream
     */
    public static OutputStream outFile(String path){
        File file = new File(path);
        OutputStream output = null;
        try {
            if(!file.exists()){ //如果文件的目录不存在
                file.createNewFile(); //创建目录
            }
            output = new FileOutputStream(file);
            return output;
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断错排序，并将错排序的一对数据写入输出流中
     * @param arrayList
     * @param outputStream
     */
    public static void getWrongSort(Integer[] arrayList,OutputStream outputStream){
        int count=0;
        Integer[] arr;
        try {
            for(int i=0;i<arrayList.length-1;i++){
                arr = Arrays.copyOfRange(arrayList,i,arrayList.length);
                int middle = WrongSort.getMiddle(arr,arrayList[i]/1.05f,0,arr.length-1);
                for(int j=0;j<middle;j++){
                    String outStr = "("+arrayList[i]+","+arr[j]+")\n";
                    outputStream.write(outStr.getBytes());
//                System.out.println("("+arrayList[i]+","+arr[j]+")");
                }
                count+=middle;
            }
            outputStream.write(("count:"+count+"\n").getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 获取子数组中的与基准值相对应的索引值middle，通过middle可以快速获取错排序数的总数（借鉴二分法）
     * @param arr
     * @param key
     * @param low
     * @param high
     * @return int
     */
    public static int getMiddle(Integer[] arr,float key,int low,int high){
        while(low<high){
            while(low<high && arr[high]>key){
                high--;
            }
            arr[low] = arr[high];
            while(low<high && arr[low]<key){
                low++;
            }
            arr[high]=arr[low];
        }
        return low;
    }

    /**
     * 程序主函数
     * @param args
     */
    public static void main(String[] args){
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//格式化时间

        long start = System.currentTimeMillis();
        Date starttime = new Date(start);
        String startStr = sdFormatter.format(starttime);//开始时间


        String readfilePath = WrongSort.class.getResource("").getPath()+"inputtest1.txt";//读取文件路径
        System.out.println(readfilePath);
        String writefilePath = WrongSort.class.getResource("").getPath()+"outputtest1.txt";//写入文件路径
        System.out.println(writefilePath);

        List<Integer> data = (ArrayList<Integer>) WrongSort.readFileByLines(readfilePath);
        Integer[] inData = data.toArray(new Integer[data.size()]);
        OutputStream outputStream = null;
        try {
            outputStream = WrongSort.outFile(writefilePath);

            WrongSort.getWrongSort(inData,outputStream);//获取错排序并写入文件

            long end = System.currentTimeMillis();
            Date endtime = new Date(end);
            String endStr = sdFormatter.format(endtime);//结束时间

            outputStream.write(("time:"+(end-start)+"\n").getBytes());
            outputStream.write(("startTime:"+startStr+"\n"+"endTime:"+endStr+"\n").getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}