package fr.afpa.cballot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "formation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formation")
    private Long id;

    @Column(name = "nom_")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "id_organisateur")
    private Organisateur organisateur;

    @OneToMany(mappedBy = "formation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionFormation> sessionFormations;

}
