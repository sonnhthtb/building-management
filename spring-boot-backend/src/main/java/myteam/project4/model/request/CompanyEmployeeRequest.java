package myteam.project4.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class CompanyEmployeeRequest {
    @NotNull
    private String identification;
    @NotNull
    private String name;
    @NotNull
    private String dateOfBirth;
    private String phone;
    @NotNull
    private Long company_id;
}
