package project.bookreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bookreview.domain.Comment;
import project.bookreview.domain.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByReview_Id(Long reviewId);

    void deleteByUser(User user);
}
