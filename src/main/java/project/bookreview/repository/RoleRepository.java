package project.bookreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.bookreview.domain.Role;
import project.bookreview.domain.RolesEnum;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

   public Role findByRoleName(RolesEnum roleName);
}
