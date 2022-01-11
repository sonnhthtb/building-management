package myteam.project4.mapper;

import myteam.project4.entity.Salary;
import myteam.project4.entity.Service;
import myteam.project4.model.request.SalaryRequest;
import myteam.project4.model.request.ServiceRequest;
import myteam.project4.model.response.SalaryResponse;
import myteam.project4.model.response.ServiceResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper implements Mapper<Service> {
    public Service to(ServiceRequest request) {
        Service service = new Service();
        BeanUtils.copyProperties(request, service);
        return service;
    }

    public ServiceResponse to(Service service) {
        ServiceResponse response = new ServiceResponse();
        BeanUtils.copyProperties(service, response);
        return response;
    }
}
