package myteam.project4.mapper;

import myteam.project4.entity.ParkingService;
import myteam.project4.model.request.ParkingServiceRequest;
import myteam.project4.model.response.ParkingServiceResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ParkingServiceMapper implements Mapper<ParkingServiceMapper>{

    public ParkingService to(ParkingServiceRequest request) {
        ParkingService parkingService = new ParkingService();
        BeanUtils.copyProperties(request,parkingService);
        return parkingService;
    }

    public ParkingServiceResponse to(ParkingService parkingService) {
        ParkingServiceResponse parkingServiceResponse = new ParkingServiceResponse();
        BeanUtils.copyProperties(parkingService, parkingServiceResponse);
        return parkingServiceResponse;
    }
}
