package ru.dubr;


import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedInputStream buffInputStream = new BufferedInputStream(new FileInputStream(args[0]));
        byte[] chars = new byte[buffInputStream.available()];
        buffInputStream.read(chars); //Читаю весь файл в массив байт
        buffInputStream.close();

        ArrayList<Integer[]> arr = new ArrayList<>();
        BufferedReader buf = new BufferedReader(new StringReader(new String(chars)));
        String s = buf.readLine();
        Integer[] visitor;
        while (s != null) { //Считываю из массива байт по одной строке и трансформирую её в массив Int
            visitor = getTimeArray(s);
            arr.add(visitor);
            s = buf.readLine();
        }
        buf.close();

        ArrayList<Period> periodsArr = new ArrayList<>();
        Period period = new Period(0, 800, 800);

        for (int time = 800; time < 2000; time++) { //Прохожусь по временному отрезку работы банка, с 8:00 до 20:00
            if (time % 100 == 60) // Костыль для сокращения времени работы программы.
                time += 40; // По сути, он ни на что не влияет, но у нас же время, зачем обрабатывать 8:60, например?

            if (time == 2000) { //Если наступило 20:00, банк закрывается и период заканчивается
                period.setEndTime(time);
                periodsArr.add(period);
                break;
            }

            int count = 0;
            for (Integer[] t: arr) { //Смотрим, сколько посетителей в настоящий момент находится в банке
                if (t[0] <= time && t[1] > time)
                    count++;
            }

            if (count == period.getVisitorsCount()) {
                period.setEndTime(time); // Сдвиг времени окончания промежутка, если количество посетителей не изменилось.
            }
            else {
                period.setEndTime(time);
                periodsArr.add(period); //Если количество посетителей изменилось, начинаем новый промежуток.
                period = new Period(count, time, time);
            }
        }

        int max = 0;
        for (Period p: periodsArr) { // Находим максимальное значение количества посетителей
            if (max < p.getVisitorsCount())
                max = p.getVisitorsCount();
        }

        for (Period p: periodsArr) { //Выводим все промежутки с максимальным количеством посетителей.
            if (max == p.getVisitorsCount())
                p.print();
        }
    }

    private static Integer[] getTimeArray(String s) {
        String[] tStr = s.split(" "); //Трансформирую строку в два числа, время входа и время выхода.
        Integer[] time = new Integer[2];  // Так как я не придумал, как нормально работать с форматом Date, пришлось переводить в int

        String[] inTime = tStr[0].split(":"); //Убираем двоеточие из строки,
        String[] outTime = tStr[1].split(":");// получаем массив из двух строк с числами

        time[0] = Integer.parseInt(inTime[0] + inTime[1]);
        time[1] = Integer.parseInt(outTime[0] + outTime[1]);

        return time;
    }

}
