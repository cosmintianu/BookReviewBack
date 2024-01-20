package project.bookreview.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bookreview.requests.AuthorRequest;
import project.bookreview.service.AuthorService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/author", method = RequestMethod.GET)
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody AuthorRequest authorRequest) {

        try {

            authorService.saveAuthor(authorRequest.getFirstName(), authorRequest.getLastName());
            return ResponseEntity.ok("Author added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<AuthorRequest>> getAllAuthors() {
        List<AuthorRequest> authorRequests = authorService.getAllAuthors();
        return ResponseEntity.ok(authorRequests);
    }

    @Transactional
    @DeleteMapping("/deleteByFirstAndLastName")
    public ResponseEntity<String> deleteAuthor(@RequestBody AuthorRequest authorRequest) {
        try {
            authorService.deleteAuthor(authorRequest.getFirstName(), authorRequest.getLastName());
            return ResponseEntity.ok("Author deleted successfully by name: " + authorRequest.getFirstName() + authorRequest.getLastName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }
}

