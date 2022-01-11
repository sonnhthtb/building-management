package myteam.project4.controller;

import lombok.AllArgsConstructor;
import myteam.project4.model.request.CleanedRequest;
import myteam.project4.model.request.ParkingServiceRequest;
import myteam.project4.model.response.BaseResponse;
import myteam.project4.model.response.CleanedResponse;
import myteam.project4.model.response.ParkingServiceResponse;
import myteam.project4.service.ParkingServiceBusinessService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("public-api/v1.0.0/parking-service")
@AllArgsConstructor
public class ParkingServiceController {

    private final ParkingServiceBusinessService parkingService;

    @GetMapping()
    public BaseResponse<ParkingServiceResponse> getCurrentParkingService(){
        return BaseResponse.ofSuccess(parkingService.getActiveParkingService());
    }

    @PostMapping()
    public BaseResponse<ParkingServiceResponse> createNewParkingService(@RequestBody @Valid ParkingServiceRequest request){
        return BaseResponse.ofSuccess(parkingService.createNewParkingService(request));
    }

}
