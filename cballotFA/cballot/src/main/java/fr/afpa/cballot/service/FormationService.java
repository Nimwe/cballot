package fr.afpa.cballot.service;

import fr.afpa.cballot.entity.Formation;
import fr.afpa.cballot.entity.Organisateur;
import fr.afpa.cballot.repository.FormationRepository;
import fr.afpa.cballot.repository.OrganisateurRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class FormationService {

    private final FormationRepository formationRepository;
    private final OrganisateurRepository organisateurRepo;

    public List<Formation> findAll() {
        return formationRepository.findAll();
    }

    public Optional<Formation> findById(Long id) {
        return formationRepository.findById(id);
    }

    public Organisateur findOrganisateurById(Long id) {
        return organisateurRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Organisateur non trouvé avec id : " + id));
    }

    public Formation save(Formation formation) {
        return formationRepository.save(formation);
    }

    public Formation update(Long id, Formation formation) {
        Formation existing = formationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Formation non trouvée avec l'id : " + id));

        // Mettre à jour les champs
        existing.setNom(formation.getNom());
        existing.setOrganisateur(formation.getOrganisateur());

        return formationRepository.save(existing);
    }

    @Transactional // En utilisant Transactionnel ça permet que toutes les opérations liées soient effectuée dans une seule transaction
    public void delete(Long id) {
        Formation formation = formationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Formation non trouvée avec l'id : " + id));

        // Supprimer toutes les sessions associées pour éviter les problèmes de
        // contrainte
        if (formation.getSessionFormations() != null) {
            formation.getSessionFormations().clear();
        }

        formationRepository.delete(formation);
    }
}