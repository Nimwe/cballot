package fr.afpa.cballot.controller;

import fr.afpa.cballot.dto.StagiaireDTO;
import fr.afpa.cballot.service.StagiaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController pour gérer les Stagiaires.
 * Fournit les endpoints CRUD.
 */
@RestController
@RequestMapping("/api/stagiaires")
@RequiredArgsConstructor
public class StagiaireController {

    private final StagiaireService stagiaireService;

    @GetMapping
    public ResponseEntity<List<StagiaireDTO>> getAll() {
        return ResponseEntity.ok(stagiaireService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StagiaireDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(stagiaireService.findById(id));
    }

    @PostMapping
    public ResponseEntity<StagiaireDTO> create(@RequestBody StagiaireDTO dto) {
        return ResponseEntity.ok(stagiaireService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StagiaireDTO> update(@PathVariable Long id, @RequestBody StagiaireDTO dto) {
        return ResponseEntity.ok(stagiaireService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stagiaireService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
