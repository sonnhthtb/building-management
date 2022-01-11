package myteam.project4.service;

import myteam.project4.model.request.ParkingServiceRequest;
import myteam.project4.model.response.ParkingServiceResponse;

public interface ParkingServiceBusinessService {
    ParkingServiceResponse getActiveParkingService();
    ParkingServiceResponse createNewParkingService(ParkingServiceRequest request);
}
