package encapsulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LibraryCatalogDriver {
    public static void main(String[] args) {
        LibraryCatalog lc = new LibraryCatalog();
        lc.addBook(new Book("Harry Potter", "Rowling", 2003, 5));
        lc.addBook(new Book("A", "B", 2023, 5));

       Iterator<Book> it = lc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        /*for (Book book: lc) {
            System.out.println(book);
            // book.checkoutBook();
        } */

        //System.out.println(lc.books);
        //List<Book> myBooks = lc.getBooks1();
        //myBooks.clear();

        // List<Book> lcbooks = lc.getBooks1();
        //lcbooks.get(0).changeTitle("hohoho");

        /*List<Book> books = new ArrayList<>();
        books.add(new Book("A", "B", 2023, 5 ));
        lc.setBooks(books);
        System.out.println("Before: " + lc);
        books.clear();
        System.out.println("After: " + lc);
        */


        /*List<Book> books = new ArrayList<>();
        books.add(new Book("A", "B", 2023, 5 ));
        LibraryCatalog lc2 = new LibraryCatalog(books);
        books.clear();
        System.out.println(lc2); */

    }
}
