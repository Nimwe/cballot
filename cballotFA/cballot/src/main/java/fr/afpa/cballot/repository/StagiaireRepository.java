package fr.afpa.cballot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.cballot.entity.Stagiaire;

public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {
}
