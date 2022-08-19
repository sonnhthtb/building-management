package myteam.project4.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class BuildingEmployeeResponse implements Serializable {
    private Long id;
    private String code;
    private String name;
    private String dateOfBirth;
    private String address;
    private String phone;
    private SalaryResponse salaryResponse;
}
