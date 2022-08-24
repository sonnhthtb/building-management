package myteam.project4.repository;

import myteam.project4.entity.CompanyEmployee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface CompanyEmployeeRepository extends JpaRepository<CompanyEmployee, Long> {

    List<CompanyEmployee> findCompanyEmployeeByIsDeletedAndCompanyId(boolean isDeleted, Long company_id);

    Page<CompanyEmployee> findCompanyEmployeeByIsDeletedAndCompanyId(boolean isDeleted, Long company_id, Pageable page);

    List<CompanyEmployee> findAllByIsDeleted(boolean isDeleted);

    List<CompanyEmployee> findCompanyEmployeeByCompanyIdAndIsDeletedAndNameLike(Long company_id, boolean isDeleted, String name);

    @Query(value = "SELECT Max(b.id) FROM CompanyEmployee b")
    Optional<Integer> findLastId();
}
