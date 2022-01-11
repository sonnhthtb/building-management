package myteam.project4.controller;

import lombok.AllArgsConstructor;
import myteam.project4.model.request.ProtectedServiceRequest;
import myteam.project4.model.response.BaseResponse;
import myteam.project4.model.response.ProtectedServiceResponse;
import myteam.project4.service.ProtectedServiceBusinessService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("public-api/v1.0.0/protect-service")
@AllArgsConstructor
public class ProtectedServiceController {
    private final ProtectedServiceBusinessService protectedService;

    @GetMapping()
    public BaseResponse<ProtectedServiceResponse> getProtectedServiceCurrent() {
        return BaseResponse.ofSuccess(protectedService.getActiveProtectedService());
    }

    @PostMapping()
    public BaseResponse<ProtectedServiceResponse> createNewProtectedService(@RequestBody @Valid ProtectedServiceRequest request) {
        return BaseResponse.ofSuccess((protectedService.createNewProtectedService(request)));
    }
}
