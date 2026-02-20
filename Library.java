import java.util.Scanner;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Library {
    static Scanner scanner = new Scanner(System.in);
    static public void main(String[] args){
        HashMap<String, Book> books = new HashMap<>();
        int bookQuantity = 0;

        try{
            Path dbPath = Paths.get("db.txt");
            List<String> lines = Files.readAllLines(dbPath);
            bookQuantity = Integer.parseInt(lines.get(0));
            int temp = bookQuantity;
            int currentLine = 1;

            while(temp > 0){
                Book book = new Book();
                book.name = lines.get(currentLine);
                currentLine++;
                book.autor = lines.get(currentLine);
                currentLine++;
                book.year = Integer.parseInt(lines.get(currentLine));
                currentLine++;
                books.put(book.name, book);
                temp--;
            }

        }catch(IOException e){
            System.out.println(e);
        }

        int option = 0;

        while(option != 5){
            printMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            
            switch(option){
            case 1:
                Book book = readBook();
                addBook(book, books);
                System.out.println("Book successfully added");
                System.out.println(" ");
                break;
            case 2:
                if(books.size() == 0){
                    System.out.println("No books to list");
                }else{
                    System.out.println("------BOOKS------");
                    listBooks(books);
                    System.out.println(" ");
                }
                break;
            case 3:
                System.out.println("Type the name of the book to be withdrawn");
                String bookNametoDelete = scanner.nextLine();
                
                try{
                    Book bookfound = searchBook(bookNametoDelete, books);

                    if(bookfound != null){
                        System.out.println("Book withdrawn!");
                        removeBooks(bookNametoDelete, books);
                    }else{
                        throw new UnexistingBookException("Book not found");
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
                break;
            case 4:
                System.out.println("Type the name of the book to be searched");
                String bookNametoFind = scanner.nextLine();
                try{
                    Book bookfound = searchBook(bookNametoFind, books);

                    if(bookfound != null){
                        System.out.println("Book found!: " + bookfound);
                    }else{
                        throw new UnexistingBookException("Book not found");
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
                break;
            }
        }
        
        scanner.close();
    }

    public static void printMenu(){
        System.out.println("1. Add new book");
        System.out.println("2. List all books");
        System.out.println("3. Remove a book");
        System.out.println("4. Search a book");
        System.out.println("5. Leave Program");
        System.out.println("SELECT AN OPTION...: ");

    }

    public static Book readBook(){
        Book book = new Book();
        
        System.out.println("Book name: ");
        book.name = scanner.nextLine();
        
        System.out.println("Book autor: ");
        book.autor = scanner.nextLine();
        
        System.out.println("Book launching year: ");
        book.year = scanner.nextInt();

        return book;
    }

    public static void addBook(Book book, HashMap<String, Book> books){
        books.put(book.name, book);
        writeBooks(books);
    }

    public static void writeBooks(HashMap<String, Book> books){
        String fileName = "db.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter((fileName)))) {
            writer.write(String.valueOf(books.size()));
            writer.newLine();

            for(Book b : books.values()){
                writer.write(b.name);
                writer.newLine();
                writer.write(b.autor);
                writer.newLine();
                writer.write(String.valueOf(b.year));
                writer.newLine();
            }

        }catch(IOException e){
            System.err.println("An error ocurred" + e.getMessage());

        }

    }
    public static void listBooks(HashMap<String, Book> books){
        books.forEach((key, value) -> {
            System.out.println(key +" " + value);
        });
    }

    public static void removeBooks(String bookname, HashMap<String, Book> books){
        books.remove(bookname);
    }

    public static Book searchBook(String bookname, HashMap<String, Book> books){
        return books.get(bookname);
    }
}