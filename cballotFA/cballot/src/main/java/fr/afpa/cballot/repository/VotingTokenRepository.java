package fr.afpa.cballot.repository;

import fr.afpa.cballot.entity.VotingToken;
import fr.afpa.cballot.entity.Scrutin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotingTokenRepository extends JpaRepository<VotingToken, Long> {

    /**
     * Rechercher un token unique par sa valeur.
     *
     * @param token valeur du token
     * @return le VotingToken correspondant, ou null si inexistant
     */
    VotingToken findByToken(String token);

    /**
     * Lister tous les tokens associés à un scrutin spécifique.
     *
     * @param scrutin Scrutin dont on veut les tokens
     * @return liste des VotingToken associés
     */
    List<VotingToken> findByScrutin(Scrutin scrutin);

    /**
     * Vérifier si un token existe pour un scrutin donné.
     *
     * @param token   valeur du token
     * @param scrutin Scrutin associé
     * @return true si le token existe, false sinon
     */
    boolean existsByTokenAndScrutin(String token, Scrutin scrutin);

    /**
     * Supprimer tous les tokens associés à un scrutin donné.
     *
     * @param scrutin Scrutin dont on veut supprimer les tokens
     */
    void deleteByScrutin(Scrutin scrutin);
}
