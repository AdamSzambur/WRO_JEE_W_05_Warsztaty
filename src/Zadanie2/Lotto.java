package Zadanie2;

import org.apache.commons.lang3.ArrayUtils;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lotto {
    public static void main(String[] args) {

        System.out.println("Lotto");

        Random rnd = new Random();

        int[] lottoNum = new int[6];
        int[] yourNum = new int[6];

        int num =0;

        for (int i=0; i<lottoNum.length; i++) {
            num = rnd.nextInt(49)+1;

            if (!ArrayUtils.contains(lottoNum,num)) {
                lottoNum[i] = num;
            } else {
                --i;
            }
        }

        System.out.println(Arrays.toString(lottoNum));

        for (int i=0; i<yourNum.length; i++) {
            System.out.println("Podaj "+(i+1)+" liczbę z 6");
            num = getNumber();

            if (!ArrayUtils.contains(yourNum,num)) {
                yourNum[i] = num;
            } else {
                System.out.println("Podany numer juz wybrałeś");
                --i;
            }
        }

        System.out.println(Arrays.toString(yourNum));

        int counter = 0;

        for (int i =0; i<lottoNum.length;i++) {
            if (ArrayUtils.contains(lottoNum, yourNum[i])) {
                ++counter;
            }
        }

        if (counter ==0) {
            System.out.println("Niestety nic nie trafiłeś");
        } else if (counter >0 && counter < 3) {
            System.out.println("Trafiłeś " + counter + " liczb.");
        } else {
            System.out.println("Hura trafiłeś " + counter + " liczb. Jesteś teraz bogatym czlowiekiem.");
        }

    }


    static int getNumber() {
        boolean torf = false;
        int result = 0;
        Scanner sc = new Scanner(System.in);
        while (!torf) {

            while (!sc.hasNextInt()) {
                System.out.println("Podany ciąg znaków nie jest liczbą ");
                sc.next();
                System.out.println("Podaj liczbę z zakresu 1-49 :");
            }

            result = sc.nextInt();

            if (result > 0 && result < 49) {
                torf = true;
            } else {
                System.err.println("Podana liczba nie mieści się w zakresie");
                System.out.println("Podaj liczbę z zakresu 1-49 :");
            }
            sc.nextLine();
        }
        return result;
    }


}
