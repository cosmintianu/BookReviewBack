package project.bookreview.requests;

import lombok.Data;
import project.bookreview.domain.Book;
import project.bookreview.domain.User;

@Data
public class ReviewRequest {

    private User user;
    private Book book;
    private Double rating;
    private String text;
    private String date;
}
