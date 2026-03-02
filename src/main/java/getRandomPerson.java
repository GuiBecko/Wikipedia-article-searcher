import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
public class getRandomPerson {
    public static void main(String[] args){
        String[] persons = {
            "https://pt.wikiquote.org/wiki/C%C3%ADcero",
            "https://pt.wikiquote.org/wiki/Nicolau_Maquiavel",
            "https://pt.wikiquote.org/wiki/Voltaire",
            "https://pt.wikiquote.org/wiki/William_Shakespeare"
        };
        String personUrl = persons[RandomNumber(3)];
        String personSujo = personUrl.substring(personUrl.lastIndexOf("/") + 1).replaceAll("_", " ");
        String person = URLDecoder.decode(personSujo, StandardCharsets.UTF_8);

        GetterHTML getter = new GetterHTML(personUrl);
        String rawHtml = getter.getHTML();
        
        
        List<String> todasAsUls = htmlTransformer.htmlToList(rawHtml);
        
        List<String> citacoes = new ArrayList<>();
        
        
        for(String ul : todasAsUls){
            String texto = ul.trim();
            if(!texto.isEmpty() && texto != null){
                if(texto.startsWith("\"") || texto.startsWith("“") || texto.startsWith("«")){
                    citacoes.add(texto.replaceAll("\\[\\d+\\]", ""));
                }
            }
        }
                    
        
        System.out.println(todasAsUls.get(RandomNumber(todasAsUls.size() + 1)) + " " + person);
        
    };
    
    public static int RandomNumber(int bound){
        Random random = new Random();
        return random.nextInt(bound);
    }
}