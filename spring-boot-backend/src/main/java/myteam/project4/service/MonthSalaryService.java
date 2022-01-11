package myteam.project4.service;

import myteam.project4.model.request.MonthRequest;
import myteam.project4.model.response.BuildingEmployeeResponse;

import java.util.List;

public interface MonthSalaryService {
    List<BuildingEmployeeResponse> getAllMonthSalaryPrecent(boolean isDeleted);

    List<BuildingEmployeeResponse> getMonthSalaryByMonth(MonthRequest request);
}
