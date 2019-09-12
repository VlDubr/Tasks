package ru.dubr;

import java.io.*;
import java.util.ArrayList;

public class CoordsReader {

    public static ArrayList<Float[]> getArrayFromFile(String path) throws IOException {
        BufferedInputStream buffInStrDots = new BufferedInputStream(new FileInputStream(path));
        byte[] chars = new byte[buffInStrDots.available()];
        buffInStrDots.read(chars); //Получаем массив байт из файла.
        buffInStrDots.close();
        return createCoordsArray(chars); // Получаем и возвращаем ArrayList с точками.
    }

    public static ArrayList<Float[]> createCoordsArray(byte[] chars) throws IOException {
        BufferedReader buf = new BufferedReader(new StringReader(new String(chars)));
        ArrayList<Float[]> arr = new ArrayList<>();

        String s = buf.readLine(); //Считываем одну строку из массива байт
        String[] xyStr;
        while (s != null) {
            xyStr = s.split(" "); //Делим строку на две по пробелу
            Float[] xyNum = new Float[2];
            xyNum[0] = Float.parseFloat(xyStr[0]); // Парсим из двух строк координаты точки
            xyNum[1] = Float.parseFloat(xyStr[1]);

            arr.add(xyNum); //Добавляем координаты в ArrayList

            s = buf.readLine(); // Считываем одну строку из массива байт
        }
        buf.close();

        return arr;
    }

}
