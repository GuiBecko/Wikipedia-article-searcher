import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.net.URI;

public class GetterHTML {
    String url;

    public String getHTML(){
        System.out.println("Entrei no main do getHTML");
        try {
            HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

            HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            System.err.println(e);
            return "URL Connection Error";
        }
    }

    public GetterHTML(String url){
        this.url = url;
        System.out.println("Entrei no construtor do getHTML");
        System.out.println("URL: " + this.url);
    }
    
}
