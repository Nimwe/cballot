package fr.afpa.cballot.service;

import fr.afpa.cballot.dto.OrganisateurDTO;
import fr.afpa.cballot.entity.Organisateur;
import fr.afpa.cballot.repository.OrganisateurRepository;
import fr.afpa.cballot.utils.DtoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service pour gérer les opérations CRUD sur les Organisateurs.
 */
@Service
public class OrganisateurService {

    private final OrganisateurRepository repo;

    public OrganisateurService(OrganisateurRepository repo) {
        this.repo = repo;
    }

    /**
     * Crée un nouvel organisateur.
     *
     * @param dto OrganisateurDTO contenant les données
     * @return OrganisateurDTO créé
     */
    public OrganisateurDTO create(OrganisateurDTO dto) {
        Organisateur organisateur = DtoMapper.toEntity(dto);
        return DtoMapper.toDto(repo.save(organisateur));
    }

    /**
     * Récupère tous les organisateurs.
     *
     * @return liste de OrganisateurDTO
     */
    public List<OrganisateurDTO> findAll() {
        return repo.findAll().stream().map(DtoMapper::toDto).toList();
    }

    /**
     * Récupère un organisateur par son ID.
     *
     * @param id identifiant de l'organisateur
     * @return Optional contenant OrganisateurDTO si trouvé, vide sinon
     */
    public Optional<OrganisateurDTO> findById(Long id) {
        return repo.findById(id).map(DtoMapper::toDto);
    }

    /**
     * Met à jour un organisateur existant.
     *
     * @param id  identifiant de l'organisateur à mettre à jour
     * @param dto données mises à jour
     * @return OrganisateurDTO mis à jour
     */
    public OrganisateurDTO update(Long id, OrganisateurDTO dto) {
        Organisateur existing = repo.findById(id).orElseThrow();
        existing.setNom(dto.getNom());
        existing.setEmail(dto.getEmail());
        return DtoMapper.toDto(repo.save(existing));
    }

    /**
     * Supprime un organisateur par son ID.
     *
     * @param id identifiant de l'organisateur à supprimer
     */
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
