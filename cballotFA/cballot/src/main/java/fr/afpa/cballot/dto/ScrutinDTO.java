package fr.afpa.cballot.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScrutinDTO {

    private Long id;

    private LocalDateTime dateScrutin;

    private Long sessionFormationId;

}
