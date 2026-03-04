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

    public static String getSummary(String url){
        String html = getHtml(url);
        if(html == null) return null;

        Document doc = Jsoup.parse(html);

        //se cair na pagina de artigo nao encontrado
        if (!doc.select(".noarticletext").isEmpty()) {
            return "Erro: O artigo solicitado nao existe na Wikipedia.";
        }

        //div mae do summary
        Element content = doc.selectFirst("div.mw-content-ltr");
        if (content != null) {
            Element firstP = content.select("> p").stream()
                                .filter(p -> !p.text().isBlank())
                                .findFirst()
                                .orElse(null);

            if (firstP != null) {
                String snippet = firstP.text().replaceAll("\\[\\d+\\]", "");
                return snippet;
            }else {
                System.out.println("Nenhum parágrafo de texto encontrado.");
            }
        }
        return null;
    }

    public static String getHtml(String url){
        try{
            HttpRequest request = HttpRequest
            .newBuilder()
            .header("User-agent", "Mozilla/5.0")
            .uri(new URI(url))
            .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            if(response.body().isBlank() || response.body().length() == 0){
                throw new Exception("Pagina nao encontrada");
            }
            return response.body();
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

}