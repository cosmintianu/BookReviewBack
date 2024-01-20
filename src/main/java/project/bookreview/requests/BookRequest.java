package project.bookreview.requests;

import lombok.Data;
import project.bookreview.domain.Author;
import project.bookreview.domain.Genre;

import java.util.Set;

@Data
public class BookRequest {

    private String title;
    private Set<Author> authors;
    private String publisher;
    private Set<Genre> genres;


}
