package fr.afpa.cballot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.cballot.entity.Organisateur;

public interface OrganisateurRepository extends JpaRepository<Organisateur, Long> {
}
