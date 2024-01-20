package project.bookreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bookreview.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    void deleteBookByTitle(String title);

}
