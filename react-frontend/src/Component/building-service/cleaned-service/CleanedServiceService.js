import axios from "axios";

const CLEANED_SERVICE_API_URL = "http://localhost:8080/public-api/v1.0.0/cleaned-service";
class CleanedServiceService{

    getCurrentCleanedService(){
        return axios.get(CLEANED_SERVICE_API_URL);
    }

    createCleanedService(cleanedService){
        return axios.post(CLEANED_SERVICE_API_URL , cleanedService);
    }

}
export default new CleanedServiceService();
