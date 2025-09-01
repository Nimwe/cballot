package fr.afpa.cballot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.afpa.cballot.entity.Tour;

public interface TourRepository extends JpaRepository<Tour, Long> {

}
