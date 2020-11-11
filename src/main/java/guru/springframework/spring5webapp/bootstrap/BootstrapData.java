package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher1 = new Publisher("Arihant", "Sector 9", "Karnal", "Haryana", "132001");

        publisherRepository.save(publisher1);

        Author gaurav = new Author("Gaurav", "Kaushik");
        Book learningSpring = new Book("Learning Spring",1234567);

        addAuthorAndBook(gaurav, learningSpring, publisher1);


        Publisher publisher2 = new Publisher("Penguin", "Sector 6", "Karnal", "Haryana", "132001");
        publisherRepository.save(publisher2);

        Author rod = new Author("Rod", "Johnson");
        Book spring = new Book("Spring Guru", 234567);

        addAuthorAndBook(rod, spring, publisher2);

        System.out.println("Started in bootstrap");
        System.out.println("book count: " + bookRepository.count() + " publishers:: " + publisherRepository.count());
    }

    public void addAuthorAndBook(Author author, Book book, Publisher publisher){
        author.getBooks().add(book);
        book.getAuthors().add(author);
        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        authorRepository.save(author);
        bookRepository.save(book);
    }
}
