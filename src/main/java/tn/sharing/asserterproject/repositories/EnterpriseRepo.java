package tn.sharing.asserterproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.sharing.asserterproject.entities.Enterprise;


import java.util.Optional;

@Repository
public interface EnterpriseRepo extends JpaRepository<Enterprise, Long> {

    Optional<Enterprise> findById(Long id);
}
