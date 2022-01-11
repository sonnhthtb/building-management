package myteam.project4.repository;

import myteam.project4.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    Optional<CleanedService> findCleanedServiceByActiveIs(boolean active);

    Optional<ParkingService> findParkingServiceByActiveIs(boolean active);

    Optional<FoodService> findFoodServiceByActiveIs(boolean active);

    Optional<ProtectedService> findProtectedServiceByActiveIs(boolean active);

    Optional<MaintenanceService> findMaintenanceServiceByActiveIs(boolean active);

    @Transactional
    @Modifying
    @Query("UPDATE Service service SET service.active = false WHERE service.id IN (SELECT c.id FROM CleanedService c)")
    void deactivateAllCleanedService();

    @Transactional
    @Modifying
    @Query("UPDATE Service service SET service.active = false WHERE service.id IN (SELECT m.id FROM MaintenanceService m)")
    void deactivateAllMaintenanceService();

    @Transactional
    @Modifying
    @Query("UPDATE Service service SET service.active = false WHERE service.id IN (SELECT f.id FROM FoodService f)")
    void deactivateAllFoodService();

    @Transactional
    @Modifying
    @Query("UPDATE Service service SET service.active = false WHERE service.id IN (SELECT p.id FROM ProtectedService p)")
    void deactivateAllProtectedService();

    @Transactional
    @Modifying
    @Query("UPDATE Service service SET service.active = false WHERE service.id IN (SELECT m.id FROM ParkingService m)")
    void deactivateAllParkingService();

    List<Service> findAllByActive(boolean active);

}
