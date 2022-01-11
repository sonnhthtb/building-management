package myteam.project4.model.response;

import lombok.Data;
import myteam.project4.entity.CompanyEmployee;
import myteam.project4.entity.UsedService;

import java.util.List;

@Data
public class CompanyDetailResponse {
    private Long id;
    private String name;
    private String taxCode;
    private Float authorizedCapital;
    private String fieldOfActivity;
    private String floor;
    private String hotline;
    private Float area;
    private Float totalPrice;
    List<CompanyEmployeeResponse> companyEmployeeList;
    List<ServiceResponse> serviceList;
}
