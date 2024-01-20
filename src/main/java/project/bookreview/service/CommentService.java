package project.bookreview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookreview.domain.Comment;
import project.bookreview.repository.CommentRepository;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsByReviewId(Long reviewId) {
        return commentRepository.findByReview_Id(reviewId);
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

}
