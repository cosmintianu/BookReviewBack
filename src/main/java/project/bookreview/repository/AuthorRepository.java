package project.bookreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bookreview.domain.Author;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);
    void deleteByFirstNameAndLastName(String firstName,String lastName);

}
