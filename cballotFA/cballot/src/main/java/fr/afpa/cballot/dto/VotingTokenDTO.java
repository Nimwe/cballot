package fr.afpa.cballot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour représenter un VotingToken.
 * Permet de transférer les données sans exposer directement l'entité.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VotingTokenDTO {

    /**
     * Identifiant unique du token
     */
    private Long id;

    /**
     * Valeur aléatoire du token (UUID)
     */
    private String token;

    /**
     * ID du scrutin associé à ce token
     */
    private Long scrutinId;
}
