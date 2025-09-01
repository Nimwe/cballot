package fr.afpa.cballot.service;

import fr.afpa.cballot.dto.VoteDTO;
import fr.afpa.cballot.entity.*;
import fr.afpa.cballot.repository.*;
import fr.afpa.cballot.utils.DtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service pour gérer les opérations CRUD sur les Votes.
 * Assure la conversion entre les entités et les DTO et gère l'anonymat.
 */
@Service
public class VoteService {

    private final VoteRepository repo;
    private final BinomeRepository binomeRepo;
    private final TourRepository tourRepo;
    private final StagiaireRepository stagiaireRepo;

    public VoteService(VoteRepository repo, BinomeRepository binomeRepo, TourRepository tourRepo,
            StagiaireRepository stagiaireRepo) {
        this.repo = repo;
        this.binomeRepo = binomeRepo;
        this.tourRepo = tourRepo;
        this.stagiaireRepo = stagiaireRepo;
    }

    /**
     * Créer un vote.
     * Génère un UUID unique pour l’anonymat.
     */
    public VoteDTO create(VoteDTO dto) {
        Binome binome = binomeRepo.findById(dto.getBinomeId())
                .orElseThrow(() -> new IllegalArgumentException("Binome non trouvé"));
        Tour tour = tourRepo.findById(dto.getTourId())
                .orElseThrow(() -> new IllegalArgumentException("Tour non trouvé"));
        Stagiaire stagiaire = stagiaireRepo.findById(dto.getStagiaireId())
                .orElseThrow(() -> new IllegalArgumentException("Stagiaire non trouvé"));

        dto.setUuid(UUID.randomUUID().toString());

        Vote vote = DtoMapper.toEntity(dto, binome, tour, stagiaire);
        Vote saved = repo.save(vote);

        // Supprime l’email du stagiaire pour anonymat
        stagiaire.setEmail(null);
        stagiaireRepo.save(stagiaire);

        return DtoMapper.toDto(saved);
    }

    /**
     * Récuperer tous les votes.
     */
    public List<VoteDTO> findAll() {
        return repo.findAll().stream().map(DtoMapper::toDto).toList();
    }

    /**
     * Récuperer un vote par ID.
     */
    public VoteDTO findById(Long id) {
        return repo.findById(id)
                .map(DtoMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Vote non trouvé avec l'id : " + id));
    }

    /**
     * Mettre à jour un vote existant.
     */
    public VoteDTO update(Long id, VoteDTO dto) {
        Vote existing = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vote non trouvé avec l'id : " + id));

        Binome binome = binomeRepo.findById(dto.getBinomeId())
                .orElseThrow(() -> new IllegalArgumentException("Binome non trouvé"));
        Tour tour = tourRepo.findById(dto.getTourId())
                .orElseThrow(() -> new IllegalArgumentException("Tour non trouvé"));
        Stagiaire stagiaire = stagiaireRepo.findById(dto.getStagiaireId())
                .orElseThrow(() -> new IllegalArgumentException("Stagiaire non trouvé"));

        existing.setBinome(binome);
        existing.setTour(tour);
        existing.setStagiaire(stagiaire);
        existing.setUuid(UUID.randomUUID().toString()); // Nouveau UUID pour mise à jour

        return DtoMapper.toDto(repo.save(existing));
    }

    /**
     * Supprimer un vote par ID.
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
