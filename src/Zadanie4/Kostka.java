package Zadanie4;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Random;

public class Kostka {
    public static void main(String[] args) {
        String kostka = "2D5-1";
        System.out.println("Wynik rzutu kostką : "+ kostka+ " : " + rzut(kostka));
    }

    static int rzut(String kostka) {
        String[] tab = kostka.split("D|-|\\+");
        int numThrows =1;
        int cubeArms = 0;
        int addValue = 0;

        if (!tab[0].equals("")) {
            numThrows = Integer.parseInt(tab[0]);
        }

        cubeArms = Integer.parseInt(tab[1]);


        if (tab.length == 3) {
            addValue = Integer.parseInt(tab[2]);
        }

        if (addValue > 0 && ArrayUtils.contains(tab,'-'))    addValue *= -1;

        Random rnd = new Random();

        return (numThrows*(rnd.nextInt(cubeArms)+1))+addValue;
    }

}
