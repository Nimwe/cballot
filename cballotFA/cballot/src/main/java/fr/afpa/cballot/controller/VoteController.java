package fr.afpa.cballot.controller;

import fr.afpa.cballot.dto.VoteDTO;
import fr.afpa.cballot.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController pour gérer les Votes.
 * Fournit les endpoints CRUD.
 */
@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @GetMapping
    public ResponseEntity<List<VoteDTO>> getAll() {
        return ResponseEntity.ok(voteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoteDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(voteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VoteDTO> create(@RequestBody VoteDTO dto) {
        return ResponseEntity.ok(voteService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoteDTO> update(@PathVariable Long id, @RequestBody VoteDTO dto) {
        return ResponseEntity.ok(voteService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        voteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
