package fr.afpa.cballot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "sessionFormation_formation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionFormation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sessionFormation_formation")
    private Long id;

    @Column(name = "debut")
    private LocalDate dateDebut;

    @Column(name = "fin")
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "id_formation")
    private Formation formation;

    @OneToMany(mappedBy = "sessionFormation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stagiaire> stagiaires;

    @OneToMany(mappedBy = "sessionFormation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Scrutin> scrutins;
}
