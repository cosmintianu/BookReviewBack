package project.bookreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bookreview.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre,Long> {
}
