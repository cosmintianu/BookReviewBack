package project.bookreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.bookreview.domain.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher,Long> {
}
