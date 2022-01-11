package myteam.project4.service;

import myteam.project4.model.request.CleanedRequest;
import myteam.project4.model.request.MaintenanceRequest;
import myteam.project4.model.response.CleanedResponse;
import myteam.project4.model.response.MaintenanceResponse;

public interface MaintenanceBusinessService {
    MaintenanceResponse getActiveMaintenanceService();
    MaintenanceResponse createNewMaintenanceService(MaintenanceRequest maintenanceRequest);
}
