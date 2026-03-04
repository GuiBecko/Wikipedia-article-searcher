import java.util.Scanner;
public class Main {
    SummarySearcher summarySearcher = new SummarySearcher();

    static Scanner scanner = new Scanner(System.in);
    public void main(String[] args){
        String opcao = "Yes";
        while(opcao.equalsIgnoreCase("Yes")){
            System.out.println("=====Buscar resumo de artigo na wikipedia=====");
            System.out.println("Digite um artigo wikipedia para ver o resumo");
            String artigo = scanner.nextLine();
            
            int codigo = summarySearcher.search(artigo);
            switch(codigo){
                case 0:
                    System.out.println("Summary achado no LRU");
                    break;
                case 1:
                    System.out.println("Summary achado com Download da pagina");
                break;
            }
            System.out.println("Continuar buscando? ");
            System.out.println("[Yes] [No]");
            opcao = scanner.nextLine();
        }
        scanner.close();
    }
}
