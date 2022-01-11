package myteam.project4.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SalaryRequest {
    @NotNull
    private String level;
    @NotNull
    private String position;
    @NotNull
    private Float salary;
}
