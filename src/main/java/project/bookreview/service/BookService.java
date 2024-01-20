package project.bookreview.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookreview.domain.Book;
import project.bookreview.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }


    public List<Book> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        return books;
    }

//    private BookRequest convertToBookRequest(Book book) {
//        BookRequest bookRequest = new BookRequest();
//        bookRequest.setTitle(book.getTitle());
//        //bookRequest.setPublisher(book.getPublisher());
//
//        return bookRequest;
//    }
    public Book getBook(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return book;
    }

    @Transactional
    public void deleteBook(String title) {


        bookRepository.deleteBookByTitle(title);
    }

}
