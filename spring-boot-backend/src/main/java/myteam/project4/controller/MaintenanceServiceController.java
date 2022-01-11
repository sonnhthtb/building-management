package myteam.project4.controller;

import lombok.AllArgsConstructor;
import myteam.project4.model.request.CleanedRequest;
import myteam.project4.model.request.MaintenanceRequest;
import myteam.project4.model.response.BaseResponse;
import myteam.project4.model.response.CleanedResponse;
import myteam.project4.model.response.MaintenanceResponse;
import myteam.project4.service.CleanedServiceBusinessService;
import myteam.project4.service.MaintenanceBusinessService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("public-api/v1.0.0/maintenance-service")
@AllArgsConstructor
public class MaintenanceServiceController {

    private final MaintenanceBusinessService maintenanceBusinessService;

    @GetMapping()
    public BaseResponse<MaintenanceResponse> getCurrentMaintenanceService(){
        return BaseResponse.ofSuccess(maintenanceBusinessService.getActiveMaintenanceService());
    }

    @PostMapping()
    public BaseResponse<MaintenanceResponse> createNewMaintenanceService(@RequestBody @Valid MaintenanceRequest maintenanceRequest){
        return BaseResponse.ofSuccess(maintenanceBusinessService.createNewMaintenanceService(maintenanceRequest));
    }
}
