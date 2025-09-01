package fr.afpa.cballot.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Binome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Stagiaire principal
    @ManyToOne
    @JoinColumn(name = "principal_id")
    private Stagiaire principal;

    // Suppléant
    @ManyToOne
    @JoinColumn(name = "suppleant_id")
    private Stagiaire suppleant;

    @ManyToOne
    private Tour tour;

    @OneToMany(mappedBy = "binome")
    private List<Vote> votes;

}
