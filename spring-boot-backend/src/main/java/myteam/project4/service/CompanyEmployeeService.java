package myteam.project4.service;

import myteam.project4.model.request.CompanyEmployeeRequest;
import myteam.project4.model.response.CompanyEmployeeResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyEmployeeService {

    CompanyEmployeeResponse save(CompanyEmployeeRequest request);

    CompanyEmployeeResponse updateById(Long id, CompanyEmployeeRequest request);

    CompanyEmployeeResponse findById(Long id);

    String deleteById(Long id);

    List<CompanyEmployeeResponse> getAllCompanyEmployee();

    List<CompanyEmployeeResponse> findByCompanyId(Long company_id, Pageable pageable);

    List<CompanyEmployeeResponse> findCompanyEmployeeByCompanyAndNameLike(Long company_id, String name);
}
