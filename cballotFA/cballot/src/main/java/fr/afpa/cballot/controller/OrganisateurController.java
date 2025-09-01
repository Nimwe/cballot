package fr.afpa.cballot.controller;

import fr.afpa.cballot.dto.OrganisateurDTO;
import fr.afpa.cballot.service.OrganisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController pour gérer les opérations CRUD sur les Organisateurs.
 */
@RestController
@RequestMapping("/api/organisateurs")
@RequiredArgsConstructor
public class OrganisateurController {

    private final OrganisateurService organisateurService;

    /**
     * Récupère tous les organisateurs.
     *
     * @return liste de OrganisateurDTO
     */
    @GetMapping
    public ResponseEntity<List<OrganisateurDTO>> getAll() {
        List<OrganisateurDTO> organisateurs = organisateurService.findAll();
        return ResponseEntity.ok(organisateurs);
    }

    /**
     * Récupère un organisateur par son ID.
     *
     * @param id identifiant de l'organisateur
     * @return OrganisateurDTO si trouvé, 404 sinon
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrganisateurDTO> getById(@PathVariable Long id) {
        return organisateurService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crée un nouvel organisateur.
     *
     * @param dto OrganisateurDTO à créer
     * @return OrganisateurDTO créé
     */
    @PostMapping
    public ResponseEntity<OrganisateurDTO> create(@RequestBody OrganisateurDTO dto) {
        OrganisateurDTO created = organisateurService.create(dto);
        return ResponseEntity.ok(created);
    }

    /**
     * Met à jour un organisateur existant.
     *
     * @param id  identifiant de l'organisateur à mettre à jour
     * @param dto OrganisateurDTO contenant les données mises à jour
     * @return OrganisateurDTO mis à jour
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrganisateurDTO> update(@PathVariable Long id, @RequestBody OrganisateurDTO dto) {
        OrganisateurDTO updated = organisateurService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Supprime un organisateur par son ID.
     *
     * @param id identifiant de l'organisateur à supprimer
     * @return HTTP 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        organisateurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
