import axios from "axios";

const FOOD_SERVICE_API_URL = "http://localhost:8080/public-api/v1.0.0/food-service";
class FoodServiceService{

    getCurrentFoodService(){
        return axios.get(FOOD_SERVICE_API_URL);
    }

    createFoodService(foodServiceService){
        return axios.post(FOOD_SERVICE_API_URL , foodServiceService);
    }

}
export default new FoodServiceService();
