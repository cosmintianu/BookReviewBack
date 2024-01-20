package project.bookreview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookreview.domain.Author;
import project.bookreview.repository.AuthorRepository;
import project.bookreview.requests.AuthorRequest;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;


    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;

    }
    public void saveAuthor(String firstName, String lastName) {
        Author newAuthor = Author.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        authorRepository.save(newAuthor);
    }
    public List<AuthorRequest> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();

        return authors.stream()
                .map(this::convertToAuthorRequest)
                .collect(Collectors.toList());
    }
    private AuthorRequest convertToAuthorRequest(Author author){
        AuthorRequest authorRequest = new AuthorRequest();
        authorRequest.setFirstName(author.getFirstName());
        authorRequest.setLastName(author.getLastName());
        return authorRequest;
    }
    public void deleteAuthor(String firstName,String lastName) {
        authorRepository.deleteByFirstNameAndLastName(firstName,lastName);
    }
    public Author findAuthorByName(String firstName, String lastName){
        return authorRepository.findAuthorByFirstNameAndLastName(firstName,lastName);
    }
//    public void deleteBookFromAuthor(Long authorId, Long bookId) {
//        Author author = authorRepository.findById(authorId).orElse(null);
//        Book book = bookRepository.findById(bookId).orElse(null);
//
//        if (author != null && book != null) {
//            author.getBooks().remove(book);
//            authorRepository.save(author);
//        }
//    }

}
