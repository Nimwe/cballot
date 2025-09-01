package fr.afpa.cballot.service;

import fr.afpa.cballot.dto.StagiaireDTO;
import fr.afpa.cballot.entity.SessionFormation;
import fr.afpa.cballot.entity.Stagiaire;
import fr.afpa.cballot.repository.SessionFormationRepository;
import fr.afpa.cballot.repository.StagiaireRepository;
import fr.afpa.cballot.utils.DtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour gérer les opérations CRUD sur les Stagiaires.
 * Fournit la conversion vers et depuis les DTO.
 */
@Service
public class StagiaireService {

    private final StagiaireRepository repo;
    private final SessionFormationRepository sessionFormationRepo;

    public StagiaireService(StagiaireRepository repo, SessionFormationRepository sessionFormationRepo) {
        this.repo = repo;
        this.sessionFormationRepo = sessionFormationRepo;
    }

    /**
     * Crée un nouveau stagiaire.
     */
    public StagiaireDTO create(StagiaireDTO dto) {
        SessionFormation sessionFormation = sessionFormationRepo.findById(dto.getSessionFormationId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "SessionFormation non trouvée avec l'id : " + dto.getSessionFormationId()));
        Stagiaire stagiaire = DtoMapper.toEntity(dto, sessionFormation);
        return DtoMapper.toDto(repo.save(stagiaire));
    }

    /**
     * Récupère tous les stagiaires.
     */
    public List<StagiaireDTO> findAll() {
        return repo.findAll().stream().map(DtoMapper::toDto).toList();
    }

    /**
     * Récupère un stagiaire par son ID.
     */
    public StagiaireDTO findById(Long id) {
        return repo.findById(id)
                .map(DtoMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Stagiaire non trouvé avec l'id : " + id));
    }

    /**
     * Met à jour un stagiaire existant.
     */
    public StagiaireDTO update(Long id, StagiaireDTO dto) {
        Stagiaire existing = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Stagiaire non trouvé avec l'id : " + id));

        SessionFormation sessionFormation = sessionFormationRepo.findById(dto.getSessionFormationId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "SessionFormation non trouvée avec l'id : " + dto.getSessionFormationId()));

        existing.setEmail(dto.getEmail());
        existing.setSessionFormation(sessionFormation);

        return DtoMapper.toDto(repo.save(existing));
    }

    /**
     * Supprime un stagiaire par son ID.
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
