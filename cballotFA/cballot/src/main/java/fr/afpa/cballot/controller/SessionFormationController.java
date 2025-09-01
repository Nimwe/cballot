package fr.afpa.cballot.controller;

import fr.afpa.cballot.dto.SessionFormationDTO;
import fr.afpa.cballot.service.SessionFormationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController pour gérer les opérations CRUD sur les sessions de formation.
 */
@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionFormationController {

    private final SessionFormationService sessionService;

    /**
     * Récupère toutes les sessions de formation.
     *
     * @return liste de SessionFormationDTO
     */
    @GetMapping
    public ResponseEntity<List<SessionFormationDTO>> getAll() {
        return ResponseEntity.ok(sessionService.findAll());
    }

    /**
     * Récupère une session de formation par son ID.
     *
     * @param id identifiant de la session
     * @return SessionFormationDTO si trouvé, 404 sinon
     */
    @GetMapping("/{id}")
    public ResponseEntity<SessionFormationDTO> getById(@PathVariable Long id) {
        return sessionService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Crée une nouvelle session de formation.
     *
     * @param dto SessionFormationDTO à créer
     * @return SessionFormationDTO créé
     */
    @PostMapping
    public ResponseEntity<SessionFormationDTO> create(@RequestBody SessionFormationDTO dto) {
        SessionFormationDTO created = sessionService.create(dto);
        return ResponseEntity.ok(created);
    }

    /**
     * Met à jour une session de formation existante.
     *
     * @param id  identifiant de la session à mettre à jour
     * @param dto DTO contenant les nouvelles informations
     * @return SessionFormationDTO mis à jour
     */
    @PutMapping("/{id}")
    public ResponseEntity<SessionFormationDTO> update(@PathVariable Long id, @RequestBody SessionFormationDTO dto) {
        SessionFormationDTO updated = sessionService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Supprime une session de formation par son ID.
     *
     * @param id identifiant de la session à supprimer
     * @return HTTP 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        sessionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
