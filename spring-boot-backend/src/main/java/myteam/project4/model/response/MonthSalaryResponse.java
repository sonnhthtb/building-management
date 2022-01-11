package myteam.project4.model.response;

import lombok.Data;

import java.util.List;

@Data
public class MonthSalaryResponse {
    private List<BuildingEmployeeResponse> buildingEmployeeResponse;
}
