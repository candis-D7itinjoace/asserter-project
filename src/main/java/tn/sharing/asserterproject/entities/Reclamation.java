package tn.sharing.asserterproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "reclamations")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reclamation {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Long staffId = null;



    private String title;

    private String body;

    private boolean treated = false;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private AppUser employee = null;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

}
