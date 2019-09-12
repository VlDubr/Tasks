package ru.dubr;

public class Period {
    private int visitorsCount = 0; //количество посетителей за период
    private int startTime = 0; //начало периода
    private int endTime = 0; //конец периода

    public Period(int visitorsCount, int startTime, int endTime) {
        this.visitorsCount = visitorsCount;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public void print() { //Функция отображения времени периода
        String startTimeStr;
        if (startTime % 100 >= 10) //if предназначен для правильного вывода минут,
            startTimeStr = startTime / 100 + ":" + startTime % 100;// иначе если количество минут будет менее 10
        else                                                      //то выводится без 0 перед цифрой
            startTimeStr = startTime / 100 + ":" + "0" + startTime % 100;

        String endTimeStr;
        if (endTime % 100 >= 10)
            endTimeStr = endTime / 100 + ":" + endTime % 100;
        else
            endTimeStr = endTime / 100 + ":" + "0" + endTime % 100;

        System.out.println(startTimeStr + " " + endTimeStr);
    }

    public void setVisitorsCount(int visitorsCount) {
        this.visitorsCount = visitorsCount;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getVisitorsCount() {
        return visitorsCount;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }
}
