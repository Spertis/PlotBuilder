package com.company;

import javafx.util.Pair;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        PlotBuilder builder = new MyBuilder();

        builder.functionGenerator("D:/test.txt");
        ArrayList<Pair<Integer, Integer>> XY = builder.functionLoader("D:/test.txt");
        builder.plotPainter(XY, null);


    }


}

