package myteam.project4.mapper;

import lombok.AllArgsConstructor;
import myteam.project4.entity.Company;
import myteam.project4.entity.Service;
import myteam.project4.entity.UsedService;
import myteam.project4.model.request.UsedServiceRequest;
import myteam.project4.model.response.UsedServiceResponse;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


@Component
@AllArgsConstructor
public class UsedServiceMapper implements Mapper<UsedService> {

    public UsedService to(UsedServiceRequest request){
        UsedService usedService = new UsedService();
        Service service = new Service();
        service.setId(request.getServiceId());
        Company company = new Company();
        company.setId(request.getCompanyId());
        usedService.setService(service);
        usedService.setCompany(company);
        usedService.setStartDate(new Timestamp(System.currentTimeMillis()));
        return  usedService;
    }

    public UsedServiceResponse to(UsedService usedService){
        UsedServiceResponse usedServiceResponse = new UsedServiceResponse();
        usedServiceResponse.setServiceId(usedService.getService().getId());
        usedServiceResponse.setCompanyId(usedService.getCompany().getId());
        usedServiceResponse.setId(usedService.getId());
        usedServiceResponse.setStartDate(usedService.getStartDate().toString());
        return usedServiceResponse;
    }
}
