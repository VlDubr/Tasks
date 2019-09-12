package ru.dubr;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayList<Float[]> rectangleArray = CoordsReader.getArrayFromFile(args[0]);
        ArrayList<Float[]> dotsArray = CoordsReader.getArrayFromFile(args[1]);

        int[] results = new int[dotsArray.size()];
        int i = 0;
        for (Float[] dot: dotsArray) {
            results[i] = getResult(dot, rectangleArray); // Проверяем положение каждой точки относительно кравдрата
            i++;
        }

        for (int x = 0; x < results.length; x++) {
            System.out.println(results[x]);
        }
    }

    private static int getResult(Float[] dot, ArrayList<Float[]> rectangleArray) {
        Float[] rectDot1 = rectangleArray.get(0);
        Float[] rectDot3 = rectangleArray.get(2);

        if ((dot[0] < rectDot1[0] || dot[1] < rectDot1[1])
            || (dot[0] > rectDot3[0] || dot[1] > rectDot3[1]))
            return 3; // Если точка по ХУ меньше чем первая или больше чем вторая, то она снаружи.

        if ((dot[0] > rectDot1[0] && dot[1] > rectDot1[1])
                && (dot[0] < rectDot3[0] && dot[1] < rectDot3[1]))
            return 2; // Если точка по ХУ больше чем первая но меньше чем вторая, то она внутри.

        for (Float[] rectDot: rectangleArray) {
            if (dot[0].equals(rectDot[0]) && dot[1].equals(rectDot[1]))
                return 0; // Если точка равна хоть одной точке квадрата, то она является одной из вершин.
        }
        return 1; //В ином случае, она является одной из сторон.
    }
}

