package project.bookreview.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

//@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "authors", uniqueConstraints = @UniqueConstraint(columnNames = {"first_name", "last_name"}))
public class Author {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "author_seq")
   // @GenericGenerator(name = "author_seq", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Book> books = new HashSet<>();

}
