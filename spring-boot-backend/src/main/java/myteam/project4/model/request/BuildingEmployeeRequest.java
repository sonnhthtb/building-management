package myteam.project4.model.request;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class BuildingEmployeeRequest {

    @NotNull
    private String name;
    @NotNull
    private String dateOfBirth;
    private String address;
    private String phone;
    @NotNull
    private String level;
    @NotNull
    private String position;
}
