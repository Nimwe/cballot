package fr.afpa.cballot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Représente un tour dans un scrutin.
 * Chaque scrutin peut avoir plusieurs tours (1er tour, 2e tour, etc.).
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tour {

    /**
     * Identifiant unique du tour.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Numéro du tour dans le scrutin.
     * Exemple : 1 pour le 1er tour, 2 pour le 2e tour.
     */
    private Integer numero;

    /**
     * Scrutin auquel ce tour appartient.
     * Relation ManyToOne car plusieurs tours peuvent appartenir au même scrutin.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scrutin_id", nullable = false)
    private Scrutin scrutin;

    /**
     * Liste des binômes participant à ce tour.
     * Relation OneToMany avec Binome.
     */
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Binome> binomes;

    /**
     * Liste des votes effectués lors de ce tour.
     * Relation OneToMany avec Vote.
     */
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes;
}
