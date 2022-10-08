package tn.sharing.asserterproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.sharing.asserterproject.entities.AppUser;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);


    Optional<AppUser> findById(Long id);
}