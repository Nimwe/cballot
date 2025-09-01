package fr.afpa.cballot.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleOrganisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role_organisateur")
    private Long id;

    @Column(name = "role")
    private String libelle;

    @ManyToMany(mappedBy = "roles")
    private List<Organisateur> organisateurs;
}
