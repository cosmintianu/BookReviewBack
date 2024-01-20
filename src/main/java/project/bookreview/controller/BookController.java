package project.bookreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bookreview.domain.Author;
import project.bookreview.domain.Book;
import project.bookreview.domain.Publisher;
import project.bookreview.requests.BookRequest;
import project.bookreview.service.AuthorService;
import project.bookreview.service.BookService;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/book", method = RequestMethod.GET)
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;


    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;

    }

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody BookRequest bookRequest) {
        // Convert BookDto to Book entity
        Book book = convertDtoToEntity(bookRequest);

        // Add logic to save the book in the database
        bookService.saveBook(book);

        return ResponseEntity.ok("Book added successfully");
    }

    // Add a method to convert BookDto to Book entity
    private Book convertDtoToEntity(BookRequest bookRequest) {
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthors(bookRequest.getAuthors());
        book.setGenres(bookRequest.getGenres());

        Publisher publisher = new Publisher();
        publisher.setName(bookRequest.getPublisher());
        book.setPublisher(publisher);
        return book;
    }

    @PostMapping("/addBookAndAuthors")
    public ResponseEntity<String> addBookWithAuthors(@RequestBody BookRequest bookRequest) {

        try {
            Book book = Book.builder().title(bookRequest
                            .getTitle())
                    .build();
            List<Author> authors = bookRequest.getAuthors().stream().map(authorRequest -> {
                        Author existingAuthor = authorService.findAuthorByName(authorRequest.getFirstName(), authorRequest.getLastName());

                        if (existingAuthor != null) {

                            return existingAuthor;
                        } else {
                            //Create new author
                            Author newAuthor = new Author();
                            newAuthor.setFirstName(authorRequest.getFirstName());
                            newAuthor.setLastName(authorRequest.getLastName());
                            return newAuthor;
                        }

                    })
                    .collect(Collectors.toList());

            book.setAuthors(new HashSet<>(authors));

            bookService.saveBook(book);

            return ResponseEntity.ok("Book and it's authors added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> book = bookService.getAllBooks();
        //fill the authors
        return ResponseEntity.ok(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id){

        Book book = bookService.getBook(id);
        return ResponseEntity.ok(book);

    }

    //@Transactional
    @DeleteMapping("/deleteByTitle")
    public ResponseEntity<String> deleteBookByName(@RequestBody BookRequest bookRequest) {
        try {
            bookService.deleteBook(bookRequest.getTitle());
            return ResponseEntity.ok("Book deleted successfully by name: " + bookRequest.getTitle());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
}
