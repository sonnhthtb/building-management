package myteam.project4.repository;

import myteam.project4.entity.Service;
import myteam.project4.entity.UsedService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsedServiceRepository extends JpaRepository<UsedService, Long> {

    List<UsedService> findByIsDeletedAndCompanyId(boolean isDeleted, Long companyId);

    @Query(value = "SELECT * FROM used_service " +
            "WHERE 1 = 1 " +
            "AND MONTH(start_date) = :month " +
            "AND is_deleted = false " +
            "AND company_id = :companyId",nativeQuery = true)
    List<UsedService> findUsedServiceMonthByCompanyId(Long companyId, Long month);

    List<UsedService> findUsedServiceByIsDeleted(boolean isDeleted);

    List<UsedService> findByService(Service service);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UsedService u SET u.isDeleted = true WHERE u.service = :service")
    void deleteUsedServiceByService(Service service);

    UsedService findByIsDeletedAndCompanyIdAndServiceId(Boolean isDeleted, Long companyId, Long serviceId);
}
