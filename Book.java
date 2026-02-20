public class Book {
    public int id;
    public String name;
    public String autor;
    public int year;

    @Override
    public String toString(){
        return String.format("Title: %-20s | Autor: %-20s | Year: %d", name, autor, year);
    }
}
