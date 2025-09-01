package fr.afpa.cballot.controller;

import fr.afpa.cballot.dto.RoleOrganisateurDTO;
import fr.afpa.cballot.service.RoleOrganisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour gérer les rôles d’organisateur.
 */
@RestController
@RequestMapping("/api/roles-organisateur")
@RequiredArgsConstructor
public class RoleOrganisateurController {

    private final RoleOrganisateurService service;

    /**
     * Récupérer tous les rôles.
     */
    @GetMapping
    public ResponseEntity<List<RoleOrganisateurDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * Récupérer un rôle par son ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<RoleOrganisateurDTO> getById(@PathVariable Long id) {
        RoleOrganisateurDTO dto = service.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    /**
     * Créer un nouveau rôle.
     */
    @PostMapping
    public ResponseEntity<RoleOrganisateurDTO> create(@RequestBody RoleOrganisateurDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    /**
     * Mettre à jour un rôle existant.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RoleOrganisateurDTO> update(@PathVariable Long id, @RequestBody RoleOrganisateurDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    /**
     * Supprimer un rôle.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
