package myteam.project4.model.response;
import lombok.Data;

@Data
public class CompanyEmployeeResponse {
    private Long id;
    private String code;
    private String identification;
    private String name;
    private String dateOfBirth;
    private String phone;
    private Long company_id;
}
