package fr.afpa.cballot.service;

import fr.afpa.cballot.dto.RoleOrganisateurDTO;
import fr.afpa.cballot.entity.RoleOrganisateur;
import fr.afpa.cballot.repository.RoleOrganisateurRepository;
import fr.afpa.cballot.utils.DtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service pour gérer les rôles des organisateurs.
 * Fournit les opérations CRUD et la conversion vers les DTO.
 */
@Service
public class RoleOrganisateurService {

    private final RoleOrganisateurRepository repo;

    public RoleOrganisateurService(RoleOrganisateurRepository repo) {
        this.repo = repo;
    }

    /**
     * Créer un nouveau rôle d’organisateur.
     *
     * @param dto DTO contenant les informations du rôle
     * @return DTO du rôle créé
     */
    public RoleOrganisateurDTO create(RoleOrganisateurDTO dto) {
        RoleOrganisateur role = DtoMapper.toEntity(dto);
        return DtoMapper.toDto(repo.save(role));
    }

    /**
     * Récupérer tous les rôles d’organisateur.
     *
     * @return Liste de DTO
     */
    public List<RoleOrganisateurDTO> findAll() {
        return repo.findAll().stream().map(DtoMapper::toDto).toList();
    }

    /**
     * Récupérer un rôle d’organisateur par son ID.
     *
     * @param id ID du rôle
     * @return DTO du rôle ou null si non trouvé
     */
    public RoleOrganisateurDTO findById(Long id) {
        return repo.findById(id).map(DtoMapper::toDto).orElse(null);
    }

    /**
     * Mettre à jour un rôle existant.
     *
     * @param id  ID du rôle à mettre à jour
     * @param dto DTO contenant les nouvelles informations
     * @return DTO du rôle mis à jour
     */
    public RoleOrganisateurDTO update(Long id, RoleOrganisateurDTO dto) {
        RoleOrganisateur existing = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("RoleOrganisateur non trouvé avec l'id : " + id));

        existing.setLibelle(dto.getLibelle());

        return DtoMapper.toDto(repo.save(existing));
    }

    /**
     * Supprimer un rôle par son ID.
     *
     * @param id ID du rôle à supprimer
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
