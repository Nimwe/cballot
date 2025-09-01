package fr.afpa.cballot.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormationDTO {
    private Long id;
    private String nom;
    private Long organisateurId;

}
