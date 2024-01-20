package project.bookreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bookreview.domain.Comment;
import project.bookreview.service.CommentService;

import java.util.List;

@RestController
@RequestMapping(value = "/comment", method = RequestMethod.GET)
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<List<Comment>> getCommentsByReviewId(@PathVariable Long reviewId) {
        List<Comment> comments = commentService.getCommentsByReviewId(reviewId);
        return ResponseEntity.ok(comments);
    }

    // Endpoint to add a new comment
    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment newComment = commentService.addComment(comment);
        return ResponseEntity.ok(newComment);
    }
}
