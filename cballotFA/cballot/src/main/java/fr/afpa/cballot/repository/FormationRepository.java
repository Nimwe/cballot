package fr.afpa.cballot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.cballot.entity.Formation;

public interface FormationRepository extends JpaRepository<Formation, Long> {
}