package fr.afpa.cballot.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO {
    private Long id;
    private String uuid; // ⚠️ UUID temporaire (jamais stocké après coup)
    private Long binomeId; // Binôme choisi
    private Long tourId; // Tour du scrutin
    private Long stagiaireId; // Stagiaire votant
}
