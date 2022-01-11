import axios from "axios";
const EMPLOYEESTAT_API_URL = "http://localhost:8080/public-api/v1.0.0/company"

class CompanyEmployeeService{

    getAll(month, year){
        return axios.get(EMPLOYEESTAT_API_URL +'/view-statistic/'  + month + '/' + year);
    }

}

export default new CompanyEmployeeService();