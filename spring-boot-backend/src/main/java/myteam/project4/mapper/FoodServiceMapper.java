package myteam.project4.mapper;

import myteam.project4.entity.FoodService;
import myteam.project4.model.request.FoodServiceRequest;
import myteam.project4.model.response.FoodServiceResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class FoodServiceMapper implements Mapper<FoodServiceMapper>{

    public FoodService to(FoodServiceRequest request) {
        FoodService foodService = new FoodService();
        BeanUtils.copyProperties(request,foodService);
        return foodService;
    }

    public FoodServiceResponse to(FoodService foodService) {
        FoodServiceResponse foodServiceResponse = new FoodServiceResponse();
        BeanUtils.copyProperties(foodService, foodServiceResponse);
        return foodServiceResponse;
    }
}
