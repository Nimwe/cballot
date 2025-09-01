package fr.afpa.cballot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.cballot.entity.RoleOrganisateur;

public interface RoleOrganisateurRepository extends JpaRepository<RoleOrganisateur, Long> {
}
