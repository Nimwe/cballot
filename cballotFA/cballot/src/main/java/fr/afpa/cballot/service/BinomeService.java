package fr.afpa.cballot.service;

import fr.afpa.cballot.dto.BinomeDTO;
import fr.afpa.cballot.entity.Binome;
import fr.afpa.cballot.entity.Stagiaire;
import fr.afpa.cballot.entity.Tour;
import fr.afpa.cballot.repository.BinomeRepository;
import fr.afpa.cballot.repository.StagiaireRepository;
import fr.afpa.cballot.repository.TourRepository;
import fr.afpa.cballot.utils.DtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour gérer les opérations CRUD sur les Binomes.
 */
@Service
public class BinomeService {

    private final BinomeRepository repo;
    private final StagiaireRepository stagiaireRepo;
    private final TourRepository tourRepo;

    /**
     * Constructeur pour l'injection des repositories.
     */
    public BinomeService(BinomeRepository repo, StagiaireRepository stagiaireRepo, TourRepository tourRepo) {
        this.repo = repo;
        this.stagiaireRepo = stagiaireRepo;
        this.tourRepo = tourRepo;
    }

    /**
     * Créer un nouveau binôme à partir d'un DTO.
     *
     * @param dto données du binôme à créer
     * @return le DTO du binôme créé
     */
    public BinomeDTO create(BinomeDTO dto) {
        Stagiaire principal = stagiaireRepo.findById(dto.getPrincipalId()).orElseThrow();
        Stagiaire suppleant = stagiaireRepo.findById(dto.getSuppleantId()).orElseThrow();
        Tour tour = tourRepo.findById(dto.getTourId()).orElseThrow();

        Binome binome = DtoMapper.toEntity(dto, principal, suppleant, tour);
        return DtoMapper.toDto(repo.save(binome));
    }

    /**
     * Récuperer tous les binômes.
     *
     * @return liste de DTO de tous les binômes
     */
    public List<BinomeDTO> findAll() {
        return repo.findAll().stream().map(DtoMapper::toDto).toList();
    }

    /**
     * Récuperer un binôme par son ID.
     *
     * @param id identifiant du binôme
     * @return Optional contenant le DTO du binôme si trouvé
     */
    public Optional<BinomeDTO> findById(Long id) {
        return repo.findById(id).map(DtoMapper::toDto);
    }

    /**
     * Mettre à jour un binôme existant.
     *
     * @param id  identifiant du binôme à mettre à jour
     * @param dto données mises à jour
     * @return DTO du binôme mis à jour
     */
    public BinomeDTO update(Long id, BinomeDTO dto) {
        Binome existing = repo.findById(id).orElseThrow();

        Stagiaire principal = stagiaireRepo.findById(dto.getPrincipalId()).orElseThrow();
        Stagiaire suppleant = stagiaireRepo.findById(dto.getSuppleantId()).orElseThrow();
        Tour tour = tourRepo.findById(dto.getTourId()).orElseThrow();

        existing.setPrincipal(principal);
        existing.setSuppleant(suppleant);
        existing.setTour(tour);

        return DtoMapper.toDto(repo.save(existing));
    }

    /**
     * Supprimer un binôme par son ID.
     *
     * @param id identifiant du binôme à supprimer
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
