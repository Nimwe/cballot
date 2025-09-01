package fr.afpa.cballot.service;

import fr.afpa.cballot.dto.VotingTokenDTO;
import fr.afpa.cballot.entity.Scrutin;
import fr.afpa.cballot.entity.VotingToken;
import fr.afpa.cballot.repository.ScrutinRepository;
import fr.afpa.cballot.repository.VotingTokenRepository;
import fr.afpa.cballot.utils.DtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service métier pour la gestion des VotingTokens.
 * 
 * Fournit les méthodes CRUD pour créer, lire, mettre à jour et supprimer
 * les tokens utilisés dans les scrutins.
 */
@Service // Indique que cette classe contient la logique métier
@RequiredArgsConstructor // Génère automatiquement un constructeur avec les dépendances "final"
public class VotingTokenService {

    private final VotingTokenRepository repo;
    private final ScrutinRepository scrutinRepo;

    /**
     * Créer un nouveau VotingToken.
     *
     * @param dto DTO contenant les infos du token à créer
     * @return DTO du VotingToken créé
     */
    public VotingTokenDTO create(VotingTokenDTO dto) {
        // On récupère le scrutin associé (vérification de l’existence en BDD)
        Scrutin scrutin = scrutinRepo.findById(dto.getScrutinId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Scrutin non trouvé avec l'id : " + dto.getScrutinId()));

        // Génération d’un token unique (UUID)
        String token = UUID.randomUUID().toString();

        // Mapping DTO → Entity
        VotingToken entity = DtoMapper.toEntity(dto, scrutin, token);

        // Sauvegarde en BDD
        VotingToken saved = repo.save(entity);

        // Retour en DTO
        return DtoMapper.toDto(saved);
    }

    /**
     * Récupérer tous les VotingTokens.
     *
     * @return liste de DTO de tous les tokens
     */
    public List<VotingTokenDTO> findAll() {
        return repo.findAll()
                .stream()
                .map(DtoMapper::toDto)
                .toList();
    }

    /**
     * Récupérer un VotingToken par son ID.
     *
     * @param id ID du VotingToken
     * @return DTO du VotingToken ou null si non trouvé
     */
    public VotingTokenDTO findById(Long id) {
        return repo.findById(id)
                .map(DtoMapper::toDto)
                .orElse(null);
    }

    /**
     * Mettre à jour un VotingToken existant.
     *
     * @param id  ID du VotingToken à modifier
     * @param dto DTO contenant les nouvelles informations
     * @return DTO du VotingToken mis à jour
     */
    public VotingTokenDTO update(Long id, VotingTokenDTO dto) {
        VotingToken existing = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("VotingToken non trouvé avec l'id : " + id));

        // Récupérer le scrutin associé au token
        Scrutin scrutin = scrutinRepo.findById(dto.getScrutinId())
                .orElseThrow(
                        () -> new IllegalArgumentException("Scrutin non trouvé avec l'id : " + dto.getScrutinId()));

        // Mettre à jour les champs
        existing.setScrutin(scrutin);
        existing.setToken(dto.getToken());

        // Sauvegarde et retour DTO
        return DtoMapper.toDto(repo.save(existing));
    }

    /**
     * Supprimer un VotingToken par son ID.
     *
     * @param id ID du VotingToken à supprimer
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
