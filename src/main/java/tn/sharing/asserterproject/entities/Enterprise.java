package tn.sharing.asserterproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "enterprises", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enterprise {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String phoneNumber;
}
