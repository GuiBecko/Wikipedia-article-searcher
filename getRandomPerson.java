import java.util.Random;
public class getRandomPerson {
    public static void main(String[] args){
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
        String person = RandomPerson(persons);
        System.out.println("Pessoa escolhida: "+ person);
        System.out.println("Chamando o html");

        GetterHTML html = new GetterHTML(person);
        System.out.println(html.getHTML());
        
    };

    public static String RandomPerson(String[] persons){
        Random random = new Random();
        String str = persons[random.nextInt(12)];

        return str;
    };
}