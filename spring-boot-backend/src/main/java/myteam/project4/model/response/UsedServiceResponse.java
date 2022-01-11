package myteam.project4.model.response;

import lombok.Data;

@Data
public class UsedServiceResponse {

    private Long id;

    private String startDate;

    private Long companyId;

    private Long serviceId;
}
