package fr.afpa.cballot.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionFormationDTO {
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Long formationId; // référence à la formation
}
