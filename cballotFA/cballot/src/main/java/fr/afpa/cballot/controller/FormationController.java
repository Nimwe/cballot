package fr.afpa.cballot.controller;

import fr.afpa.cballot.dto.FormationDTO;
import fr.afpa.cballot.entity.Formation;
import fr.afpa.cballot.entity.Organisateur;
import fr.afpa.cballot.service.FormationService;
import fr.afpa.cballot.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController pour gérer les formations
 */
@RestController
@RequestMapping("/api/formations")
@RequiredArgsConstructor
public class FormationController {

    private final FormationService formationService;

    /**
     * Récuperer toutes les formations
     */
    @GetMapping
    public ResponseEntity<List<FormationDTO>> getAll() {
        return ResponseEntity.ok(
                formationService.findAll().stream()
                        .map(DtoMapper::toFormationDto)
                        .toList());
    }

    /**
     * Récuperer une formation par son id
     */
    @GetMapping("/{id}")
    public ResponseEntity<FormationDTO> getById(@PathVariable Long id) {
        return formationService.findById(id)
                .map(DtoMapper::toFormationDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Créer une nouvelle formation
     */
    @PostMapping
    public ResponseEntity<FormationDTO> create(@RequestBody FormationDTO dto) {
        Organisateur orga = formationService.findOrganisateurById(dto.getOrganisateurId());
        Formation formation = DtoMapper.toEntity(dto, orga);
        formation.setOrganisateur(orga);
        Formation created = formationService.save(formation);
        return ResponseEntity.ok(DtoMapper.toFormationDto(created));
    }

    /**
     * Mettre à jour une formation existante
     */
    @PutMapping("/{id}")
    public ResponseEntity<FormationDTO> update(@PathVariable Long id, @RequestBody FormationDTO dto) {
        // Récupérer l'organisateur depuis l'ID fourni dans le DTO
        Organisateur orga = formationService.findOrganisateurById(dto.getOrganisateurId());

        // Transformer le DTO en entité en gardant l'organisateur associé
        Formation formation = DtoMapper.toEntity(dto, orga);
        formation.setOrganisateur(orga);

        // Mettre à jour la formation existante
        Formation updated = formationService.update(id, formation);

        return ResponseEntity.ok(DtoMapper.toFormationDto(updated));
    }

    /**
     * Supprimer une formation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        formationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
