package fr.afpa.cballot.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleOrganisateurDTO {
    private Long id;
    private String libelle; // Exemple : Formateur, Responsable, etc.
}