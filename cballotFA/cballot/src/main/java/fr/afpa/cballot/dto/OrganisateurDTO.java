package fr.afpa.cballot.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganisateurDTO {
    private Long id;
    private String nom;
    private String email;
}
