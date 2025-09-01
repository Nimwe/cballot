package fr.afpa.cballot.controller;

import fr.afpa.cballot.dto.BinomeDTO;
import fr.afpa.cballot.service.BinomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController pour gérer les opérations CRUD sur les Binomes.
 */
@RestController
@RequestMapping("/api/binomes")
@RequiredArgsConstructor
public class BinomeController {

    private final BinomeService binomeService;

    /**
     * Récupère tous les binômes.
     *
     * @return liste de BinomeDTO
     */
    @GetMapping
    public ResponseEntity<List<BinomeDTO>> getAll() {
        List<BinomeDTO> binomes = binomeService.findAll();
        return ResponseEntity.ok(binomes);
    }

    /**
     * Récupère un binôme par son ID.
     *
     * @param id identifiant du binôme
     * @return BinomeDTO si trouvé, 404 sinon
     */
    @GetMapping("/{id}")
    public ResponseEntity<BinomeDTO> getById(@PathVariable Long id) {
        return binomeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Créer un nouveau binôme.
     *
     * @param dto données du binôme
     * @return le BinomeDTO créé
     */
    @PostMapping
    public ResponseEntity<BinomeDTO> create(@RequestBody BinomeDTO dto) {
        BinomeDTO created = binomeService.create(dto);
        return ResponseEntity.ok(created);
    }

    /**
     * Mettre à jour un binôme existant.
     *
     * @param id  identifiant du binôme à mettre à jour
     * @param dto données mises à jour
     * @return BinomeDTO mis à jour
     */
    @PutMapping("/{id}")
    public ResponseEntity<BinomeDTO> update(@PathVariable Long id, @RequestBody BinomeDTO dto) {
        BinomeDTO updated = binomeService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    /**
     * Supprime un binôme par son ID.
     *
     * @param id identifiant du binôme à supprimer
     * @return réponse HTTP 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        binomeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
