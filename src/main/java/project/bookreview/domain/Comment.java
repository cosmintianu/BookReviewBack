package project.bookreview.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment extends UserTextInput{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne//no more cascade here => error
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne//neither here
    @JoinColumn(name = "review_id")

    private Review review;

    private String text;

    private String date;


}
