import axios from "axios";
const EMPLOYEESTAT_API_URL = "http://localhost:8080/public-api/v1.0.0/month-salary"

class EmployeeStatService{

    getAll(month, year){
        return axios.get(EMPLOYEESTAT_API_URL +'/'  + month + '/' + year);
    }

}

export default new EmployeeStatService();
