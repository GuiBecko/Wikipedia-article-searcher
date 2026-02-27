import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

public class htmlTransformer {

    public static List<String> htmlToList(String htmlContent) {
        List<String> divContents = new ArrayList<>();

        try {
            // Transforma a String em um documento navegável
            Document doc = Jsoup.parse(htmlContent);

            // Seleciona todas as tags <div>
            Elements uls = doc.select("ul");

            for (Element ul : uls) {
                // .html() retorna o conteúdo interno (incluindo outras tags)
                // .text() retornaria apenas o texto limpo
                divContents.add(ul.text());
            }

        } catch (Exception e) {
            System.err.println("Erro ao transformar HTML: " + e.getMessage());
        }

        return divContents;
    }
}