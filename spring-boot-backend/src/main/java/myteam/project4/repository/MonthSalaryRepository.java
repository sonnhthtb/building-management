package myteam.project4.repository;

import myteam.project4.entity.MonthSalary;
import myteam.project4.model.response.MonthSalaryResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface MonthSalaryRepository extends JpaRepository<MonthSalary, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE month_salary SET is_deleted = true WHERE is_deleted = false " +
            "AND building_employee_id = :building_employee_id", nativeQuery = true)
    void deactivateAllByBuildingEmployeeId(Long building_employee_id);

}
