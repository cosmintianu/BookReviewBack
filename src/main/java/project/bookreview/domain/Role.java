package project.bookreview.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(columnNames = {"role_name"}))
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private RolesEnum roleName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "role_users",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id") )
    @JsonBackReference
    private Set<User> users = new HashSet<User>();

}
