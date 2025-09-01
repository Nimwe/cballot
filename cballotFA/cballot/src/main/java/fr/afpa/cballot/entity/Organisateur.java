package fr.afpa.cballot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Organisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_organisateur")
    private Long id;

    private String nom;
    private String email;

    @ManyToMany
    @JoinTable(name = "status", // nom réel de ta table
            joinColumns = @JoinColumn(name = "id_organisateur"), // clé étrangère vers Organisateur
            inverseJoinColumns = @JoinColumn(name = "id_role_organisateur") // clé étrangère vers RoleOrganisateur
    )
    private List<RoleOrganisateur> roles;

    @OneToMany(mappedBy = "organisateur")
    private List<Formation> formations;
}