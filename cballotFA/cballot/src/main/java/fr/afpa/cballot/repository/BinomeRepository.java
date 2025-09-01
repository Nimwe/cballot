package fr.afpa.cballot.repository;

import fr.afpa.cballot.entity.Binome;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BinomeRepository extends JpaRepository<Binome, Long> {

    // Accéder à l'id du scrutin via la relation Binome -> Tour -> Scrutin
    List<Binome> findByTour_Scrutin_Id(Long scrutinId);
}
