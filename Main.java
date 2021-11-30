import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;

import java.io.*;

class Main {

    private final static String FILENAME = "out.txt";

    public static void main(String[] args) throws IOException {

        System.out.println("Start parsing, nahuj");

        var cssSelector = "div.topicbox > div:nth-child(1)";
        var doc = Jsoup.connect("https://www.anekdot.ru").get();

        var outputSettings = new Document.OutputSettings();
        outputSettings.prettyPrint(false);
        doc.outputSettings(outputSettings);
        doc.select("br").before("\\n");
        doc.select("p").before("\\n");

        var elements = doc.select(cssSelector);
        var fw = new FileWriter(new File(FILENAME));
        for (var e : elements) {
            var txt = e.text().replaceAll("\\\\n", "\n") + "\n";
            fw.write(txt);
            System.out.println(txt + "\t\t***\n");
        }
        fw.close();
    }
}