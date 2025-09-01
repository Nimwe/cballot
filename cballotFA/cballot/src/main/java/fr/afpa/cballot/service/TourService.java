package fr.afpa.cballot.service;

import fr.afpa.cballot.dto.TourDTO;
import fr.afpa.cballot.entity.Scrutin;
import fr.afpa.cballot.entity.Tour;
import fr.afpa.cballot.repository.ScrutinRepository;
import fr.afpa.cballot.repository.TourRepository;
import fr.afpa.cballot.utils.DtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour gérer les opérations CRUD sur les Tours.
 * Fournit la conversion vers et depuis les DTO.
 */
@Service
public class TourService {

    private final TourRepository repo;
    private final ScrutinRepository scrutinRepo;

    public TourService(TourRepository repo, ScrutinRepository scrutinRepo) {
        this.repo = repo;
        this.scrutinRepo = scrutinRepo;
    }

    /**
     * Créer un nouveau tour.
     */
    public TourDTO create(TourDTO dto) {
        Scrutin scrutin = scrutinRepo.findById(dto.getScrutinId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Scrutin non trouvé avec l'id : " + dto.getScrutinId()));
        Tour tour = DtoMapper.toEntity(dto, scrutin);
        return DtoMapper.toDto(repo.save(tour));
    }

    /**
     * Récuperer tous les tours.
     */
    public List<TourDTO> findAll() {
        return repo.findAll().stream().map(DtoMapper::toDto).toList();
    }

    /**
     * Récuperer un tour par son ID.
     */
    public TourDTO findById(Long id) {
        return repo.findById(id)
                .map(DtoMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Tour non trouvé avec l'id : " + id));
    }

    /**
     * Mettre à jour un tour existant.
     */
    public TourDTO update(Long id, TourDTO dto) {
        Tour existing = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tour non trouvé avec l'id : " + id));
        Scrutin scrutin = scrutinRepo.findById(dto.getScrutinId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Scrutin non trouvé avec l'id : " + dto.getScrutinId()));

        existing.setNumero(dto.getNumero());
        existing.setScrutin(scrutin);

        return DtoMapper.toDto(repo.save(existing));
    }

    /**
     * Supprimer un tour par son ID.
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
