package project.bookreview.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users",uniqueConstraints = @UniqueConstraint(columnNames = {"username","email"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(unique = true)
    public String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String email;

    private int age;

    @ManyToMany(mappedBy = "users" ,cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<Role>();


}
