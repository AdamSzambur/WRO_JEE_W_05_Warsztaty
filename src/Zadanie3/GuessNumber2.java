package Zadanie3;

import java.util.Scanner;

public class GuessNumber2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Zgadywanie liczb vol. 2");

        System.out.println("Teraz komuter zgaduję liczbę. Ty pomysl sobie liczbę z zakresu 1-1000");


        int num = 500;

        String answer= "";
        int counter =1;
        int dif = 500;



        do {

            if (dif == 0) System.err.println("Proszę nie oszukiwać :)");

            System.out.print("\nCzy liczba to : ");
            System.err.println(num);
            System.out.print("Podaj odpowiedz : [więcej], [mniej], [trafiłeś] : ");
            do {
                answer = sc.nextLine();
                if (!(answer.equals("więcej") || answer.equals("mniej") || answer.equals("trafiłeś"))) {
                    System.err.println("Dozowlona odpowiedz : [więcej], [mniej], [trafiłeś] : ");
                    System.out.print("Podaj odpowiedz : [więcej], [mniej], [trafiłeś] : ");
                }
            } while (!(answer.equals("więcej") || answer.equals("mniej") || answer.equals("trafiłeś")));
            ++counter;

            dif = (int)Math.round(dif/2.0);



            if (answer.equals("więcej")) {
                num += dif;
            }
            if (answer.equals("mniej")) {
                num -= dif;
            }

        } while (!answer.equals("trafiłeś") && counter < 11);


        if (counter == 11) {
            System.out.println("Chyba ktoś tutaj oszukuje");
        }



    }



}
