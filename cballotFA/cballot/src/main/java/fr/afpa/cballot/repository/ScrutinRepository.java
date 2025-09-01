package fr.afpa.cballot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.cballot.entity.Scrutin;

public interface ScrutinRepository extends JpaRepository<Scrutin, Long> {
}