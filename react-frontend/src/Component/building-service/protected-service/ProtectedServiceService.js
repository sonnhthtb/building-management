import axios from "axios";

const PROTECTED_SERVICE_API_URL = "http://localhost:8080/public-api/v1.0.0/protect-service";
class ProtectedServiceService{

    getCurrentProtectedService(){
        return axios.get(PROTECTED_SERVICE_API_URL);
    }

    createProtectedService(protectedServiceService){
        return axios.post(PROTECTED_SERVICE_API_URL , protectedServiceService);
    }

}
export default new ProtectedServiceService();
