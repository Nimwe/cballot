package fr.afpa.cballot.controller;

import fr.afpa.cballot.dto.TourDTO;
import fr.afpa.cballot.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController pour gérer les Tours.
 * Fournit les endpoints CRUD.
 */
@RestController
@RequestMapping("/api/tours")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;

    @GetMapping
    public ResponseEntity<List<TourDTO>> getAll() {
        return ResponseEntity.ok(tourService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tourService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TourDTO> create(@RequestBody TourDTO dto) {
        return ResponseEntity.ok(tourService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourDTO> update(@PathVariable Long id, @RequestBody TourDTO dto) {
        return ResponseEntity.ok(tourService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tourService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
