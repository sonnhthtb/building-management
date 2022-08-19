package myteam.project4.model.response;
import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyEmployeeResponse implements Serializable {
    private Long id;
    private String code;
    private String identification;
    private String name;
    private String dateOfBirth;
    private String phone;
    private Long company_id;
}
