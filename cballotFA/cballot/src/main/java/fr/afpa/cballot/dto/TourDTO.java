package fr.afpa.cballot.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TourDTO {
    private Long id;
    private Integer numero;
    private Long scrutinId;
}
