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

public class PopularWords {
    public static void main(String[] args) {
        String[] excluded;
        ArrayList<String> popularWords = new ArrayList<>();
        String word;

        StringBuilder sb = new StringBuilder();
        File file = new File("excluded.txt");

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine()+"|");
            }
        } catch(FileNotFoundException ex) {
            System.err.println("Nie mozna otworzyc pliku");
        }

        excluded = sb.toString().split("\\|");

        file = new File("popularWords.txt");

        Connection connect = Jsoup.connect("http://www.onet.pl/");
        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            PrintWriter pw = new PrintWriter(file);
            for (Element elem : links) {
                StringTokenizer st = new StringTokenizer(elem.text(), " ,:,\\,,',?,!,\",\\|");

                while (st.hasMoreTokens()) {
                    word = st.nextToken();
                    if ((word.length() > 3) && !(Arrays.asList(excluded).contains(word)) && !popularWords.contains(word)) {
                        popularWords.add(word);
                        pw.println(word);
                    }
                }
            }
            pw.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }



}
