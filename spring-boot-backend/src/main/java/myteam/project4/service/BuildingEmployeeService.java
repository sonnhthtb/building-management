package myteam.project4.service;

import myteam.project4.model.request.BuildingEmployeeRequest;
import myteam.project4.model.request.SalaryRequest;
import myteam.project4.model.response.BuildingEmployeeResponse;

import java.util.List;

public interface BuildingEmployeeService {

    BuildingEmployeeResponse save(BuildingEmployeeRequest buildingEmployeeRequest);
    BuildingEmployeeResponse save(Long id, BuildingEmployeeRequest request);
    BuildingEmployeeResponse findById(Long id);
    String deleteById(Long id);
    List<BuildingEmployeeResponse> getAllBuildingEmployee();
    List<BuildingEmployeeResponse> getBuildingEmployeeByName(String name);
}
