package tn.sharing.asserterproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.sharing.asserterproject.entities.ERole;
import tn.sharing.asserterproject.entities.Role;


@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByRoleName(ERole role);
}
