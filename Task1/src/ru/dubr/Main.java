package ru.dubr;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedInputStream buffInputStream = new BufferedInputStream(new FileInputStream(args[0]));
        ArrayList<Integer> arr = new ArrayList<>();

        byte[] chars = new byte[buffInputStream.available()];
        buffInputStream.read(chars);
        buffInputStream.close();
        BufferedReader buf = new BufferedReader(new StringReader(new String(chars)));
        String s = buf.readLine();
        while (s != null) {
            arr.add(Integer.parseInt(s));
            s = buf.readLine();
        }
        buf.close();
        Collections.sort(arr);
        double max = Collections.max(arr);
        double min = Collections.min(arr);
        double av = average(arr);
        double med = median(arr);
        double perc = arr.get(((9 * arr.size()) / 10) - 1);

        System.out.printf("%.2f\n%.2f\n%.2f\n%.2f\n%.2f", perc, med, max, min, av);

    }

    public static double median(ArrayList<Integer> arr) {
        double median;
        int size = arr.size();
        if (size % 2 != 0) {
            median = arr.get(size / 2);
        }
        else {
            median = (arr.get(size / 2) + arr.get((size / 2) - 1)) / 2.0;
        }
        return median;
    }

    public static double average(ArrayList<Integer> arr) {
        double average = 0;
        int count = 0;
        for (Integer i : arr) {
            average += i;
            count++;
        }
        average /= count;
        return average;
    }
}
