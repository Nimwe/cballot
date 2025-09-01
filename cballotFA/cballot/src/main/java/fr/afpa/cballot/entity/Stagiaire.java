package fr.afpa.cballot.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stagiaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email; // Stocké temporairement, sera supprimé après le vote

    @ManyToOne
    @JoinColumn(name = "sessionFormation_formation_id")
    private SessionFormation sessionFormation;

    @OneToMany
    private List<Vote> votes;
}