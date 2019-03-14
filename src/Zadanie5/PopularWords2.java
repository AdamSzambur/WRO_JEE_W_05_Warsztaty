package Zadanie5;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PopularWords2 {
    public static void main(String[] args) {

        // Zapiszemy również jak często bedą się pojawiać słowa i posortujemy je pod względem częstotliwości wystepownaia
        ArrayList<String> excluded = new ArrayList<>();
        ArrayList<Object[]> popularWords = new ArrayList<>();

        String word;
        int num;

        File file = new File("excluded.txt");

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                excluded.add(sc.nextLine());
            }
        } catch(FileNotFoundException ex) {
            System.err.println("Nie mozna otworzyc pliku");
        }

        file = new File("popularWords2.txt");

        Connection connect = Jsoup.connect("http://www.onet.pl/");
        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            PrintWriter pw = new PrintWriter(file);
            for (Element elem : links) {
                StringTokenizer st = new StringTokenizer(elem.text(), " ,:,\\,,',?,!,\",\\|");
                while (st.hasMoreTokens()) {
                    word = st.nextToken().toLowerCase();
                    if ((word.length() > 3) && !(Arrays.asList(excluded).contains(word))) {
                       if (containsArrayListTwo(popularWords,0,word) >=0 ) {
                           num = containsArrayListTwo(popularWords,0,word);
                           Object[] temp = popularWords.get(num);
                           temp[1] = (int)temp[1] +1;
                           popularWords.set(num, temp);
                       } else {
                           Object[] temp = new Object[]{word,1};
                           popularWords.add(temp);
                       }
                    }
                }
            }

            // sortowanie tablicy

            popularWords = sortListTwo(popularWords,1);

            for (int i=0; i<popularWords.size(); i++) {
                Object[] temp = popularWords.get(i);

                pw.println(temp[1]+"\t"+temp[0]);
            }

            pw.flush();
        } catch (IOException ex) {
            System.err.println("Brak polączenia z internetem lub brak mozliwosci otworzenia strony");;
        }

    }

    static int containsArrayListTwo(ArrayList<Object[]> tab, int row, Object find) {
        Object[] objekt = new Object[2];
        for (int i=0; i<tab.size();i++) {
            objekt = tab.get(i);
            if (objekt[row].equals(find)) {
                return i;
            }
        }
        return -1;
    }

    static ArrayList<Object[]> sortListTwo(ArrayList<Object[]> tab, int row) {
        int n = tab.size();
        for (int i=0; i<n-1; i++) {
            for (int j=0; j<n-i-1; j++) {
                Object[] object1 = tab.get(j);
                Object[] object2 = tab.get(j+1);
                // do poprawnego działania powinienem zaimplementowac compareTo dlatego row tylko 1 bo to integer :)
                if ((int)object1[row] < (int)object2[row]) {
                    Object[] temp = tab.get(j);
                    tab.set(j,object2);
                    tab.set(j+1,temp);
                }
            }
        }
        return tab;
    }
}