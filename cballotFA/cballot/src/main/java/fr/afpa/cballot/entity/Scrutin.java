package fr.afpa.cballot.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "scrutin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scrutin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scrutin")
    private Long id;

    /**
     * Équipe gagnante du scrutin.
     */
    @Column(name = "equipe_gagnante")
    private String equipeGagnante;

    /**
     * Session de formation associée au scrutin.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sessionformation_formation", nullable = false)
    private SessionFormation sessionformation;

    /**
     * Organisateurs supervisant le scrutin.
     */
    @Builder.Default
    @ManyToMany
    @JoinTable(name = "organisateur_scrutin", joinColumns = @JoinColumn(name = "scrutin_id"), inverseJoinColumns = @JoinColumn(name = "organisateur_id"))
    private Set<Organisateur> organisateurs = new HashSet<>();

    /**
     * Tours composant le scrutin.
     */
    @Builder.Default
    @OneToMany(mappedBy = "scrutin", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Tour> tours = new HashSet<>();
}
