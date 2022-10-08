package tn.sharing.asserterproject.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.sharing.asserterproject.entities.Reclamation;

import java.util.Optional;

@Repository
public interface ReclamationRepo extends JpaRepository<Reclamation, Long> {

    Optional<Reclamation> findFirstByEmployeeId(Long employeeId);
    Page<Reclamation> findByEmployeeId(Long employeeId, Pageable pageable);

    Optional<Object> findFirstByEnterpriseId(Long enterpriseId);

    Page<Reclamation> findByEnterpriseId(Long enterpriseId, Pageable page);
}
