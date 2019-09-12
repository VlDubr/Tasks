package ru.dubr;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException{
        ArrayList<Float> resultArray = new ArrayList<>(16);
        ArrayList<Float> tempArray;
        for (int i = 1; i < 6; i++) {
            tempArray = getCashArray(args[0], i);
            resultArray = sumArrays(resultArray, tempArray);
        }

        int maxNum = 0;
        float maxCount = 0;
        for (int i = 0; i < 16; i++) {
            if (maxCount < resultArray.get(i)) {
                maxCount = resultArray.get(i);
                maxNum = i + 1;
            }
        }

        System.out.println(maxNum);
    }

    private static ArrayList<Float> getCashArray(String path, int num) throws IOException {
        BufferedInputStream buffInputStream = new BufferedInputStream(new FileInputStream(path + "\\Cash" + num + ".txt"));
        byte[] chars = new byte[buffInputStream.available()];
        buffInputStream.read(chars);
        buffInputStream.close();

        ArrayList<Float> arr = new ArrayList<>(16);
        BufferedReader buf = new BufferedReader(new StringReader(new String(chars)));
        String s = buf.readLine();
        while (s != null) {
            arr.add(Float.parseFloat(s));
            s = buf.readLine();
        }
        buf.close();
        return arr;
    }

    private static ArrayList<Float> sumArrays(ArrayList<Float> arr1, ArrayList<Float> arr2) {
        if (arr1.size() == 0)
            return arr2;
        if (arr2.size() == 0)
            return arr1;
        ArrayList<Float> result = new ArrayList<>(16);
        for (int i = 0; i < 16; i++) {
            result.add(arr1.get(i) + arr2.get(i));
        }
        return result;
    }
}
