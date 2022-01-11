package myteam.project4.controller;

import lombok.AllArgsConstructor;
import myteam.project4.model.request.CleanedRequest;
import myteam.project4.model.response.BaseResponse;
import myteam.project4.model.response.CleanedResponse;
import myteam.project4.service.CleanedServiceBusinessService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("public-api/v1.0.0/cleaned-service")
@AllArgsConstructor
public class CleanedServiceController {

    private final CleanedServiceBusinessService serviceBusinessService;

    @GetMapping()
    public BaseResponse<CleanedResponse> getCurrentCleanedService(){
        return BaseResponse.ofSuccess(serviceBusinessService.getActiveCleanedService());
    }

    @PostMapping()
    public BaseResponse<CleanedResponse> createNewCleanedService(@RequestBody @Valid CleanedRequest cleanedRequest){
        return BaseResponse.ofSuccess(serviceBusinessService.createNewCleanedService(cleanedRequest));
    }
}
