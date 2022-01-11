package myteam.project4.controller;

import lombok.AllArgsConstructor;
import myteam.project4.entity.FoodService;
import myteam.project4.model.request.FoodServiceRequest;
import myteam.project4.model.response.BaseResponse;
import myteam.project4.model.response.FoodServiceResponse;
import myteam.project4.service.FoodServiceBusinessService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("public-api/v1.0.0/food-service")
@AllArgsConstructor
public class FoodServiceController {

    private final FoodServiceBusinessService foodService;

    @GetMapping()
    public BaseResponse<FoodServiceResponse> getCurrentFoodService() {
        return BaseResponse.ofSuccess(foodService.getActiveFoodService());
    }

    @PostMapping()
    public BaseResponse<FoodServiceResponse> createNewFoodService(@RequestBody @Valid FoodServiceRequest request) {
        return BaseResponse.ofSuccess(foodService.createNewFoodService(request));
    }
}
