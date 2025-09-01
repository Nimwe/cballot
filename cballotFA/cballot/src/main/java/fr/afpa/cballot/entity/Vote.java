package fr.afpa.cballot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // UUID temporaire du votant (jamais stocké après coup)

    private String uuid;

    @ManyToOne
    private Binome binome;

    @ManyToOne
    private Tour tour;

    @ManyToOne
    private Stagiaire stagiaire;

}