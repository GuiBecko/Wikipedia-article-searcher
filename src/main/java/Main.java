import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){

        System.out.println("Digite um artigo wikipedia para ver o resumo");
        String artigo = scanner.nextLine();

        SummarySearcher.search(artigo);
    }
}
