package myteam.project4.service;

import myteam.project4.model.request.FoodServiceRequest;
import myteam.project4.model.response.FoodServiceResponse;

public interface FoodServiceBusinessService {
    FoodServiceResponse getActiveFoodService();
    FoodServiceResponse createNewFoodService(FoodServiceRequest request);
}
