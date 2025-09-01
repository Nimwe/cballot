package fr.afpa.cballot.service;

import fr.afpa.cballot.dto.ScrutinDTO;
import fr.afpa.cballot.entity.Scrutin;
import fr.afpa.cballot.entity.SessionFormation;
import fr.afpa.cballot.repository.ScrutinRepository;
import fr.afpa.cballot.repository.SessionFormationRepository;
import fr.afpa.cballot.utils.DtoMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScrutinService {

    private final ScrutinRepository repo;
    private final SessionFormationRepository sessionFormationRepo;

    /**
     * Créer un nouveau Scrutin à partir d'un ScrutinDTO.
     *
     * @param dto DTO contenant les informations du scrutin à créer.
     * @return DTO correspondant au scrutin créé.
     */
    public ScrutinDTO create(ScrutinDTO dto) {
        // Récupère la SessionFormation associée
        SessionFormation sessionFormation = sessionFormationRepo.findById(dto.getSessionFormationId())
                .orElseThrow(() -> new RuntimeException("SessionFormation not found"));

        // Convertit le DTO en entité
        Scrutin scrutin = DtoMapper.toEntity(dto, sessionFormation);

        // Sauvegarde dans la base et retourne le DTO correspondant
        return DtoMapper.toDto(repo.save(scrutin));
    }

    /**
     * Rechercher un Scrutin par son ID.
     *
     * @param id ID du Scrutin.
     * @return DTO du Scrutin trouvé.
     */
    public ScrutinDTO findById(Long id) {
        Scrutin scrutin = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Scrutin not found"));
        return DtoMapper.toDto(scrutin);
    }

    /**
     * Récuperer de tous les scrutins.
     *
     * @return une liste de DTO représentant tous les scrutins
     */
    public List<ScrutinDTO> findAll() {
        return repo.findAll().stream()
                .map(DtoMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Mettre à jour un scrutin existant.
     *
     * @param id  ID du scrutin à mettre à jour
     * @param dto DTO contenant les nouvelles informations
     * @return le DTO du scrutin mis à jour
     */
    public ScrutinDTO update(Long id, ScrutinDTO dto) {
        Scrutin existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Scrutin not found"));

        SessionFormation sessionFormation = sessionFormationRepo.findById(dto.getSessionFormationId())
                .orElseThrow(() -> new RuntimeException("SessionFormation not found"));

        existing.setDateScrutin(dto.getDateScrutin());
        existing.setSessionFormation(sessionFormation);

        return DtoMapper.toDto(repo.save(existing));
    }

    /**
     * Supprimer un scrutin par son ID.
     *
     * @param id ID du scrutin à supprimer
     */
    public void delete(Long id) {
        Scrutin scrutin = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Scrutin not found"));
        repo.delete(scrutin);
    }
}
