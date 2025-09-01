package fr.afpa.cballot.controller;

import fr.afpa.cballot.dto.ScrutinDTO;
import fr.afpa.cballot.service.ScrutinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour gérer les opérations sur les Scrutins.
 * Fournit les endpoints pour créer, récupérer, mettre à jour et supprimer des
 * scrutins.
 */
@RestController
@RequestMapping("/api/scrutins")
@RequiredArgsConstructor
public class ScrutinController {

    private final ScrutinService service;

    /**
     * Crée un nouveau scrutin.
     *
     * @param dto DTO contenant les informations du scrutin à créer
     * @return ResponseEntity contenant le DTO du scrutin créé
     */
    @PostMapping
    public ResponseEntity<ScrutinDTO> create(@RequestBody ScrutinDTO dto) {
        ScrutinDTO created = service.create(dto);
        return ResponseEntity.ok(created);
    }

    /**
     * Récupère un scrutin par son ID.
     *
     * @param id ID du scrutin
     * @return ResponseEntity contenant le DTO du scrutin trouvé
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScrutinDTO> findById(@PathVariable Long id) {
        ScrutinDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    /**
     * Récupère tous les scrutins.
     *
     * @return ResponseEntity contenant une liste de DTO représentant tous les
     *         scrutins
     */
    @GetMapping
    public ResponseEntity<List<ScrutinDTO>> findAll() {
        List<ScrutinDTO> scrutins = service.findAll();
        return ResponseEntity.ok(scrutins);
    }

    /**
     * Met à jour un scrutin existant.
     *
     * @param id  ID du scrutin à mettre à jour
     * @param dto DTO contenant les nouvelles informations du scrutin
     * @return ResponseEntity contenant le DTO du scrutin mis à jour
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScrutinDTO> update(@PathVariable Long id, @RequestBody ScrutinDTO dto) {
        ScrutinDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Supprime un scrutin par son ID.
     *
     * @param id ID du scrutin à supprimer
     * @return ResponseEntity sans contenu
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
