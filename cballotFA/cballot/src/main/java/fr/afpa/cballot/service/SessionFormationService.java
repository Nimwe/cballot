package fr.afpa.cballot.service;

import fr.afpa.cballot.dto.SessionFormationDTO;
import fr.afpa.cballot.entity.Formation;
import fr.afpa.cballot.entity.SessionFormation;
import fr.afpa.cballot.repository.FormationRepository;
import fr.afpa.cballot.repository.SessionFormationRepository;
import fr.afpa.cballot.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour gérer les opérations CRUD sur les sessions de formation.
 */
@Service
@RequiredArgsConstructor
public class SessionFormationService {

    private final SessionFormationRepository repo;
    private final FormationRepository formationRepo;

    /**
     * Crée une nouvelle session de formation.
     */
    public SessionFormationDTO create(SessionFormationDTO dto) {
        Formation formation = formationRepo.findById(dto.getFormationId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Formation non trouvée avec l'id : " + dto.getFormationId()));
        SessionFormation sessionFormation = DtoMapper.toEntity(dto, formation);
        return DtoMapper.toDto(repo.save(sessionFormation));
    }

    /**
     * Récupère toutes les sessions de formation.
     */
    public List<SessionFormationDTO> findAll() {
        return repo.findAll().stream()
                .map(DtoMapper::toDto)
                .toList();
    }

    /**
     * Récupère une session de formation par son ID.
     */
    public Optional<SessionFormationDTO> findById(Long id) {
        return repo.findById(id).map(DtoMapper::toDto);
    }

    /**
     * Met à jour une session de formation existante.
     */
    public SessionFormationDTO update(Long id, SessionFormationDTO dto) {
        SessionFormation existing = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "SessionFormation non trouvée avec l'id : " + id));

        Formation formation = formationRepo.findById(dto.getFormationId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Formation non trouvée avec l'id : " + dto.getFormationId()));

        // ✅ mise à jour de tous les champs pertinents
        existing.setDateDebut(dto.getDateDebut());
        existing.setDateFin(dto.getDateFin());
        existing.setFormation(formation);

        return DtoMapper.toDto(repo.save(existing));
    }

    /**
     * Supprime une session de formation par son ID.
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
