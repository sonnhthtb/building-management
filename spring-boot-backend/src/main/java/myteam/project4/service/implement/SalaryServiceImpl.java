package myteam.project4.service.implement;

import lombok.AllArgsConstructor;
import myteam.project4.entity.Salary;
import myteam.project4.exception.BusinessCode;
import myteam.project4.exception.BusinessException;
import myteam.project4.mapper.SalaryMapper;
import myteam.project4.model.request.SalaryRequest;
import myteam.project4.model.response.SalaryResponse;
import myteam.project4.repository.SalaryRepository;
import myteam.project4.service.SalaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final SalaryMapper mapper;
    private final SalaryRepository repositiory;

    @Override
    @Transactional
    public SalaryResponse save(SalaryRequest salaryRequest) {
        Salary salary = mapper.to(salaryRequest);
        repositiory.saveAndFlush(salary);
        return mapper.to(salary);
    }

    @Override
    @Transactional
    public SalaryResponse save(Long id, SalaryRequest salaryRequest) {
        Salary salaryCheck = repositiory.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_SALARY)
        );
        Salary salary = mapper.to(salaryRequest);
        Salary salaryNew = new Salary();
        if(salaryCheck.getLevel()!= salary.getLevel() || salaryCheck.getPosition()!=salary.getPosition() || salaryCheck.getSalary()!=salary.getSalary()){
            salaryCheck.setIsDeleted(true);
            salaryNew.setPosition(salary.getPosition());
            salaryNew.setLevel(salary.getLevel());
            salaryNew.setSalary(salary.getSalary());
            repositiory.save(salaryNew);
        }
        return mapper.to(salaryNew);
    }

    @Override
    public SalaryResponse findById(Long id) {
        Salary salary = repositiory.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_SALARY)
        );
        return mapper.to(salary);
    }

    @Override
    @Transactional
    public String deleteById(Long id) {
        Salary salary = repositiory.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_SALARY)
        );
        salary.setIsDeleted(true);
        repositiory.save(salary);
        return "Deleted";
    }

    @Override
    public List<SalaryResponse> getAllSalary() {
        List<Salary> salaryList = repositiory.findAllByIsDeleted(false);
        return salaryList.stream().map(mapper::to).collect(Collectors.toList());
    }

    @Override
    public List<SalaryResponse> findSalaryByPositionLike(String position) {
        String searchPosition = "%"+position+ "%";
        List<Salary> result = new ArrayList<>();
        List<Salary> salaryList = repositiory.findByIsDeletedAndPositionLike(false, searchPosition);
        return salaryList.stream().map(mapper::to).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllPosition() {
        return repositiory.findAllPosition();
    }

    @Override
    public List<String> findLevelByPosition(String position) {
        return repositiory.findAllLevelByPosition(position);
    }
}
