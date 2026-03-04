import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PageDownloader {

    private static HttpClient client = HttpClient
            .newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();

    public static void getSummary(String url){
        String html = getHtml(url);
        if(html == "Pagina nao encontrada"){
            System.out.println("Nao foi possivel carregar a pagina");
            return;
        }
        Document doc = Jsoup.parse(html);

        if (!doc.select(".noarticletext").isEmpty()) {
            System.out.println("Erro: O artigo solicitado nao existe na Wikipedia.");
            return;
        }

        Element content = doc.selectFirst("div.mw-content-ltr");

        if (content != null) {
        // 2. Filtramos parágrafos que podem estar vazios ou ser apenas coordenadas
        Element firstP = content.select("> p").stream()
                                .filter(p -> !p.text().isBlank())
                                .findFirst()
                                .orElse(null);

        if (firstP != null) {
            String snippet = firstP.text().replaceAll("\\[\\d+\\]", "");
            System.out.println(snippet);
        } else {
            System.out.println("Nenhum parágrafo de texto encontrado.");
        }
    }
    }

    public static String getHtml(String url){
        try{
            HttpRequest request = HttpRequest
            .newBuilder()
            .header("User-agent", "Mozilla/5.0")
            .uri(new URI(url))
            .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            if(response.body().isBlank() || response.body().length() == 0) return "Pagina nao encontrada";
            
            return response.body();
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

}
//mw-content-ltr > p
