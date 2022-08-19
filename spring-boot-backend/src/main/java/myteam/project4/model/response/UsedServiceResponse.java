package myteam.project4.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsedServiceResponse implements Serializable {

    private Long id;

    private String startDate;

    private Long companyId;

    private Long serviceId;
}
