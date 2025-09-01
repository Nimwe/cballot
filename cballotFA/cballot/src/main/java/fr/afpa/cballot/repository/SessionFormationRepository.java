package fr.afpa.cballot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.cballot.entity.SessionFormation;

public interface SessionFormationRepository extends JpaRepository<SessionFormation, Long> {
}