package myteam.project4.mapper;

import myteam.project4.entity.CleanedService;
import myteam.project4.entity.MaintenanceService;
import myteam.project4.model.request.CleanedRequest;
import myteam.project4.model.request.MaintenanceRequest;
import myteam.project4.model.response.CleanedResponse;
import myteam.project4.model.response.MaintenanceResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MaintenanceMapper implements Mapper<MaintenanceMapper>{

    public MaintenanceService to(MaintenanceRequest request) {
        MaintenanceService maintenanceService = new MaintenanceService();
        BeanUtils.copyProperties(request, maintenanceService);
        return maintenanceService;
    }

    public MaintenanceResponse to(MaintenanceService maintenanceService) {
        MaintenanceResponse response = new MaintenanceResponse();
        BeanUtils.copyProperties(maintenanceService, response);
        return response;
    }
}
