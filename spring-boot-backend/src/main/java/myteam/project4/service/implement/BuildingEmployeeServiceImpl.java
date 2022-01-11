package myteam.project4.service.implement;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import myteam.project4.entity.BuildingEmployee;
import myteam.project4.entity.MonthSalary;
import myteam.project4.entity.Salary;
import myteam.project4.exception.BusinessCode;
import myteam.project4.exception.BusinessException;
import myteam.project4.mapper.BuildingEmployeeMapper;
import myteam.project4.model.request.BuildingEmployeeRequest;
import myteam.project4.model.response.BuildingEmployeeResponse;
import myteam.project4.repository.BuildingEmployeeRepository;
import myteam.project4.repository.MonthSalaryRepository;
import myteam.project4.repository.SalaryRepository;
import myteam.project4.service.BuildingEmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor
public class BuildingEmployeeServiceImpl implements BuildingEmployeeService {

    private final BuildingEmployeeMapper mapper;
    private final BuildingEmployeeRepository repository;
    private final SalaryRepository salaryRepository;
    private final MonthSalaryRepository monthSalaryRepository;


    @Override
    @Transactional
    public BuildingEmployeeResponse save(BuildingEmployeeRequest request) {
        BuildingEmployee buildingEmployee = mapper.to(new BuildingEmployee(), request);

        buildingEmployee.setCode("BE-" + (repository.findLastId().orElse(0)+1));
        Salary salary = salaryRepository.
                findByPositionAndLevelAndIsDeleted(request.getPosition(), request.getLevel(), false).orElseThrow(
                        () -> new BusinessException(BusinessCode.NOT_FOUND_SALARY)
                );
        MonthSalary monthSalary = new MonthSalary();
        monthSalary.setSalary(salary);
        buildingEmployee.setMonthSalaryList(Arrays.asList(monthSalary));
        buildingEmployee = repository.save(buildingEmployee);
        return mapper.to(buildingEmployee);
    }

    @Override
    @Transactional
    public BuildingEmployeeResponse save(Long id, BuildingEmployeeRequest request) {

        BuildingEmployee buildingEmployee = repository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_BUILDING_EMPLOYEE)
        );
        buildingEmployee = mapper.to(buildingEmployee, request);
        Salary salary = salaryRepository.
                findByPositionAndLevelAndIsDeleted(request.getPosition(), request.getLevel(), false).orElseThrow(
                        () -> new BusinessException(BusinessCode.NOT_FOUND_SALARY)
                );
        MonthSalary monthSalary = new MonthSalary();
        monthSalary.setSalary(salary);
        monthSalaryRepository.deactivateAllByBuildingEmployeeId(id);
        List<MonthSalary> monthSalaryList = buildingEmployee.getMonthSalaryList();
        monthSalaryList.add(monthSalary);
        buildingEmployee.setMonthSalaryList(monthSalaryList);
        BuildingEmployee response = repository.save(buildingEmployee);
        return mapper.to(response);
    }

    @Override
    public BuildingEmployeeResponse findById(Long id) {
        BuildingEmployee buildingEmployee = repository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_BUILDING_EMPLOYEE)
        );
        return mapper.to(buildingEmployee);
    }

    @Override
    @Transactional
    public String deleteById(Long id) {
        BuildingEmployee buildingEmployee = repository.findById(id).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_BUILDING_EMPLOYEE)
        );
        buildingEmployee.setIsDeleted(true);
        repository.save(buildingEmployee);
        return "Deleted";
    }

    @Override
    public List<BuildingEmployeeResponse> getAllBuildingEmployee() {
        List<BuildingEmployee> list = repository.findAllByIsDeleted(false);
        return list.stream().map(mapper::to).collect(Collectors.toList());
    }

    @Override
    public List<BuildingEmployeeResponse> getBuildingEmployeeByName(String name) {
        name = "%"+name+"%";
        List<BuildingEmployee> listBuildingEmployee = repository.findByIsDeletedAndNameLike(false, name);
        return listBuildingEmployee.stream().map(mapper::to).collect(Collectors.toList());
    }
}
