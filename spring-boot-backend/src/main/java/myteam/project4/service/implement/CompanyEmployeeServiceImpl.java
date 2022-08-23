package myteam.project4.service.implement;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import myteam.project4.entity.Company;
import myteam.project4.entity.CompanyEmployee;
import myteam.project4.exception.BusinessCode;
import myteam.project4.exception.BusinessException;
import myteam.project4.mapper.CompanyEmployeeMapper;
import myteam.project4.model.request.CompanyEmployeeRequest;
import myteam.project4.model.response.CompanyEmployeeResponse;
import myteam.project4.repository.CompanyEmployeeRepository;
import myteam.project4.service.CompanyEmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor
public class CompanyEmployeeServiceImpl implements CompanyEmployeeService {

    private final CompanyEmployeeRepository repository;
    private final CompanyEmployeeMapper mapper;

    @Override
    @Transactional
    public CompanyEmployeeResponse save(CompanyEmployeeRequest request) {
        CompanyEmployee companyEmployee = mapper.to(request);
        companyEmployee.setCode("CE-" + (repository.findLastId().orElse(0)+1));
        return mapper.to(repository.saveAndFlush(companyEmployee));
    }

    @Override
    @Transactional
    public CompanyEmployeeResponse updateById(Long id, CompanyEmployeeRequest request) {
        CompanyEmployee companyEmployee = repository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY_EMPLOYEE)
        );
        return mapper.to(repository.saveAndFlush(mapper.to(companyEmployee,request)));
    }

    @Override
    public CompanyEmployeeResponse findById(Long id) {
        CompanyEmployee companyEmployee = repository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY_EMPLOYEE)
        );
        return mapper.to(companyEmployee);
    }

    @Override
    @Transactional
    public String deleteById(Long id) {
        CompanyEmployee companyEmployee = repository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_COMPANY_EMPLOYEE)
        );
        companyEmployee.setIsDeleted(true);
        repository.saveAndFlush(companyEmployee);

        return "Deleted";
    }

    @Override
    public List<CompanyEmployeeResponse> getAllCompanyEmployee() {
        List<CompanyEmployee> list = repository.findAllByIsDeleted(false);
        return list.stream().map(mapper::to).collect(Collectors.toList());
    }

    @Override
    public List<CompanyEmployeeResponse> findByCompanyId(Long company_id, Pageable pageable) {
        List<CompanyEmployee> list = repository.findCompanyEmployeeByIsDeletedAndCompanyId(false,company_id, pageable);
        return list.stream().map(mapper::to).collect(Collectors.toList());
    }

    @Override
    public List<CompanyEmployeeResponse> findCompanyEmployeeByCompanyAndNameLike(Long companyId, String name) {
        String searchName = "%"+name+"%";
        List<CompanyEmployee> list = repository.findCompanyEmployeeByCompanyIdAndIsDeletedAndNameLike(companyId, false, searchName);
        return list.stream().map(mapper::to).collect(Collectors.toList());
    }
}
