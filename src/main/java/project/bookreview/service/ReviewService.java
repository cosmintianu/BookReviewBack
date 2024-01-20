package project.bookreview.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.bookreview.domain.Comment;
import project.bookreview.domain.Review;
import project.bookreview.domain.Role;
import project.bookreview.domain.User;
import project.bookreview.repository.CommentRepository;
import project.bookreview.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

import static project.bookreview.domain.RolesEnum.USER;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, CommentRepository commentRepository) {
        this.reviewRepository = reviewRepository;
        this.commentRepository = commentRepository;
    }

    public void saveReview(Review review) {

        reviewRepository.save(review);
    }
    public List<Review> getReviewsByBookId(Long id){

        return reviewRepository.findByBook_Id(id);
    }

    public Review updateReview(Long reviewId, Review updatedReview) {

            return reviewRepository.findById(reviewId)
                    .map(existingUser -> {
                        existingUser.setText(updatedReview.getText());
                        existingUser.setRating(updatedReview.getRating());

                        return reviewRepository.save(existingUser);
                    })
                    .orElse(null); // Handle not found scenario
        }

    @Transactional
    public void deleteReview(Long reviewId) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isPresent()) {

            List<Comment> comments = commentRepository.findByReview_Id(reviewId);
            for (Comment comment : comments) {
                commentRepository.deleteById(comment.getId());
            }
            // delete user
            reviewRepository.deleteById(reviewId);
        }
    }
}
