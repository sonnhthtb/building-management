package myteam.project4.model.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyResponse implements Serializable {
    private Long id;
    private String name;
    private String taxCode;
    private Float authorizedCapital;
    private String fieldOfActivity;
    private String floor;
    private String hotline;
    private Float area;
    private Long numberOfEmployee;
}
