package fr.afpa.cballot.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entité représentant un token de vote pour un scrutin.
 * Permet de gérer l’anonymat et le contrôle des votes.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VotingToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Jeton unique pour le vote */
    private String token;

    /** Scrutin associé à ce token */
    @ManyToOne
    @JoinColumn(name = "scrutin_id")
    private Scrutin scrutin;
}
