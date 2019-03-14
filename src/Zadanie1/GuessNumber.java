package Zadanie1;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
    public static void main(String[] args) {


        System.out.println("Zgadujemy liczbę z zakresu 1-100");
        System.out.println();


        int numGuess = 0;
        int number = 0;

        Random ran = new Random();
        numGuess = ran.nextInt(100)+1;


        do {
            System.out.println("Podaj liczbę z zakresu 1-100 :");
            number = getNumber();

            if (number > numGuess) {
                System.err.println("Podałeś za wysoką wartość");
            }
            if (number < numGuess) {
                System.err.println("Podałeś za niską wartość");
            }
        } while (number != numGuess);

        System.out.println("Hura. Udało ci się. Szukana liczba to "+numGuess);

    }

    static int getNumber() {
        boolean torf = false;
        int result = 0;
        Scanner sc = new Scanner(System.in);
        while (!torf) {

            while (!sc.hasNextInt()) {
                System.out.println("Podany ciąg znaków nie jest liczbą ");
                sc.next();
                System.out.println("Podaj liczbę z zakresu 1-100 :");
            }

            result = sc.nextInt();

            if (result > 0 && result < 101) {
                torf = true;
            } else {
                System.err.println("Podana liczba nie mieści się w zakresie");
                System.out.println("Podaj liczbę z zakresu 1-100 :");
            }
            sc.nextLine();
        }
        return result;
    }
}
