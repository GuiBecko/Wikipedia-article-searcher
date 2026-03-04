import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SummarySearcher {
    public SummaryCache cache = new SummaryCache(5);

    public int search(String artigo){
        artigo = sanitize(artigo);
        String url = "https://pt.wikipedia.org/wiki/" + artigo;

        /*1. Buscar na LRUCache
          2. Fazer o download do Html
         */

        try{
            String summary = cache.get(url);
            if(summary == null){
                throw new Exception("Resumo nao achado no LRU");
            }
            System.out.println(summary);
            return 0;
        }catch(Exception e){
            System.out.println(e);
        }
        
        String summary = PageDownloader.getSummary(url);
        cache.put(url, summary);

        System.out.println("===Resumo===");
        System.out.println(summary);
        return 1;
    }

    //transforma espaços em _
    //capitaliza as palavras
    //encoda
    public static String sanitize(String userInput){
        if(userInput == null || userInput.isBlank()) return "";

        String[] palavras = userInput.trim().split(" ");
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < palavras.length; i++){
            String palavra = palavras[i].toLowerCase();

            //capitaliza
            if(palavra.length() > 0){
                String capitalized = palavra.substring(0, 1).toUpperCase() + palavra.substring(1);
                sb.append(capitalized);
            }

            if(i < palavras.length -1){
                sb.append("_");
            }
        }
        //encodes
        return URLEncoder.encode(sb.toString(), StandardCharsets.UTF_8);
    }
}
//https://pt.wikipedia.org/wiki/%C3%94nibus