package myteam.project4.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MonthSalaryResponse implements Serializable {
    private List<BuildingEmployeeResponse> buildingEmployeeResponse;
}
