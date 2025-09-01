package fr.afpa.cballot.dto;

import lombok.*;

/**
 * DTO pour la création ou mise à jour d'un Binôme de candidats.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BinomeDTO {
    private Long id;
    private Long principalId; // stagiaire principal
    private Long suppleantId; // stagiaire suppléant
    private Long tourId;
}
