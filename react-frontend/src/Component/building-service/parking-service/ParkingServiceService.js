import axios from "axios";

const PARKING_SERVICE_API_URL = "http://localhost:8080/public-api/v1.0.0/parking-service";
class ParkingServiceService{

    getCurrentParkingService(){
        return axios.get(PARKING_SERVICE_API_URL);
    }

    createParkingService(parkingServiceService){
        return axios.post(PARKING_SERVICE_API_URL , parkingServiceService);
    }

}
export default new ParkingServiceService();
