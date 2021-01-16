package com.company;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public interface PlotBuilder {

    //метод принимает путь до файла
    //создает новый файл и записывает или записывает в существующий файл, если имеется, координаты в виде X	Y (два столбика. XтабуляцияY)
    void functionGenerator(String file);
    //метод принимает на вход путь до файла и возвращает ArrayList, который хранит в себе классы Pair (этот класс существет в Java)
    //ArrayList хранит Pair; Pair хранит координаты
    ArrayList<Pair<Integer, Integer>> functionLoader(String file);
    //если panel == Null, то создать свой и вывести в него, если нет, то вывести в переданном
    void plotPainter(ArrayList<Pair<Integer, Integer>> function, JPanel panel);
}
class MyBuilder implements PlotBuilder{

    @Override
    public void functionGenerator(String file) {
        try {
            File o = new File(file);
            Formatter f = new Formatter(file);
            for (int i = 0; i < 50; i++) {
                int x = i;
                int y = (int)(Math.random() * 300);
                f.format("%s    %s%s",x,y,"\n");

            }
            f.close();

        } catch (Exception e){
            System.out.println("Error");
        }

    }

    @Override
    public ArrayList<Pair<Integer, Integer>> functionLoader(String file) {

        File j = new File(file);

        try {
            int k=0;
            Scanner sc = new Scanner(j);
            ArrayList<Pair<Integer,Integer>> array = new ArrayList<Pair<Integer,Integer>>();
            while (sc.hasNextInt()){
                try {
                    Pair <Integer,Integer> a = new Pair (sc.nextInt(),sc.nextInt());
                    array.add(a);
                }catch (Exception e){
                    System.out.println("Error int");
                }
          }

            sc.close();
            return array;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public void plotPainter(ArrayList<Pair<Integer, Integer>> function, JPanel panel) {
        if (panel == null) {
            panel = new JPanel(new BorderLayout());
        }
        else {
            panel.setLayout(new BorderLayout());
        }
        class DrawingComponent extends JPanel {
            @Override
            protected void paintComponent(Graphics gh) {
                Graphics2D drp = (Graphics2D)gh;
                drp.drawLine(40, 340, 40, 40);
                drp.drawLine(40, 340, 540, 340);
                for (int i = 0; i < function.size() - 1; ++i) {
                    drp.drawLine(40 + (int)function.get(i).getKey()*10, 340 - (int)function.get(i).getValue(), 40 + (int)function.get(i + 1).getKey()*10, 340 - (int)function.get(i + 1).getValue());
                }
            }
        }

        JPanel check = panel;
        class Up7 extends JFrame {
            public Up7() {
                super("График");
                setContentPane(check);
                check.add(new DrawingComponent(), BorderLayout.CENTER);
                super.setBackground(Color.CYAN);
                setSize(580, 420);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        }
        new Up7().setVisible(true);
    }
    }

