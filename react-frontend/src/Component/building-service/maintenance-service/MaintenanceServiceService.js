import axios from "axios";

const MAINTENANCE_SERVICE_API_URL = "http://localhost:8080/public-api/v1.0.0/maintenance-service";
class MaintenanceServiceService{

    getCurrentMaintenanceService(){
        return axios.get(MAINTENANCE_SERVICE_API_URL);
    }

    createMaintenanceService(maintenanceServiceService){
        return axios.post(MAINTENANCE_SERVICE_API_URL , maintenanceServiceService);
    }

}
export default new MaintenanceServiceService();
