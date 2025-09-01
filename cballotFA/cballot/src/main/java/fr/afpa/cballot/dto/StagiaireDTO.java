package fr.afpa.cballot.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StagiaireDTO {
    private Long id;
    private String email; // visible temporairement seulement
    private Long sessionFormationId;
}
