package fr.afpa.cballot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.cballot.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}