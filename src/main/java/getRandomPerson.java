import java.util.Random;
import java.util.ArrayList;
import java.util.List;
public class getRandomPerson {
    public static void main(String[] args){
        System.out.println("Comeco do codigo");
        String[] persons = {
            "https://pt.wikiquote.org/wiki/Buda",
            "https://pt.wikiquote.org/wiki/Charlie_Chaplin",
            "https://pt.wikiquote.org/wiki/Cícero",
            "https://pt.wikiquote.org/wiki/Confúcio",
            "https://pt.wikiquote.org/wiki/Fernando_Pessoa",
            "https://pt.wikiquote.org/wiki/Immanuel_Kant",
            "https://pt.wikiquote.org/wiki/Karl_Marx",
            "https://pt.wikiquote.org/wiki/Mahatma_Gandhi",
            "https://pt.wikiquote.org/wiki/Nicolau_Maquiavel",
            "https://pt.wikiquote.org/wiki/Sócrates",
            "https://pt.wikiquote.org/wiki/Voltaire",
            "https://pt.wikiquote.org/wiki/William_Shakespeare",
        };
        String personUrl = persons[RandomNumber(12)];
        System.out.println("personUrl: " + personUrl);
        
        
        GetterHTML getter = new GetterHTML(personUrl);
        String rawHtml = getter.getHTML();
        
        
        List<String> todasAsUls = htmlTransformer.htmlToList(rawHtml);
        List<String> citacoes = new ArrayList<>();

        for(String ul : todasAsUls){
            if(ul.contains("\"")){
                citacoes.add(ul.replaceAll("\\[\\d+\\]", ""));
            }
        }
        System.out.println(todasAsUls.get(RandomNumber(todasAsUls.size() + 1)));
        
    };
    
    public static int RandomNumber(int bound){
        Random random = new Random();
        return random.nextInt(bound);
    }
}