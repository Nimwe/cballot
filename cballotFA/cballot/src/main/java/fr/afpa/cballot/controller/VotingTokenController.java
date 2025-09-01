package fr.afpa.cballot.controller;

import fr.afpa.cballot.dto.VotingTokenDTO;
import fr.afpa.cballot.service.VotingTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour gérer les VotingTokens.
 * 
 * Gère les opérations CRUD (Create, Read, Update, Delete) sur les tokens de
 * vote,
 * qui sont associés à un scrutin.
 */
@RestController // Indique que cette classe expose une API REST (retourne JSON)
@RequestMapping("/api/voting-tokens") // Préfixe commun à toutes les routes du controller
@RequiredArgsConstructor // Génère automatiquement un constructeur avec les dépendances "final"
public class VotingTokenController {

    private final VotingTokenService service;

    /**
     * Créer un nouveau VotingToken.
     * Exemple : POST /voting-tokens
     */
    @PostMapping
    public ResponseEntity<VotingTokenDTO> create(@RequestBody VotingTokenDTO dto) {
        // @RequestBody → désérialise le JSON reçu en objet DTO
        return ResponseEntity.ok(service.create(dto));
    }

    /**
     * Récuperer tous les VotingTokens.
     * Exemple : GET /voting-tokens
     */
    @GetMapping
    public ResponseEntity<List<VotingTokenDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    /**
     * Récuperer un VotingToken par son ID.
     * Exemple : GET /voting-tokens/5
     */
    @GetMapping("/{id}")
    public ResponseEntity<VotingTokenDTO> findById(@PathVariable Long id) {
        // @PathVariable → récupère {id} depuis l’URL
        VotingTokenDTO dto = service.findById(id);
        if (dto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    /**
     * Mettre à jour un VotingToken existant.
     * Exemple : PUT /voting-tokens/5
     */
    @PutMapping("/{id}")
    public ResponseEntity<VotingTokenDTO> update(@PathVariable Long id, @RequestBody VotingTokenDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    /**
     * Supprimer un VotingToken par son ID.
     * Exemple : DELETE /voting-tokens/5
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
