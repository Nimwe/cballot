package fr.afpa.cballot.repository;

import fr.afpa.cballot.entities.VotingToken;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingTokenRepository extends JpaRepository<VotingToken, Long> {
    Optional<VotingToken> findByToken(String token);
}