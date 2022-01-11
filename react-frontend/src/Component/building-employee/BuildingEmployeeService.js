import axios from 'axios';

const BUILDING_EMPLOYEE_API_URL = "http://localhost:8080/public-api/v1.0.0/building_employee";

class BuildingEmployeeService{
    
    getAllBuildingEmployee(){
        return axios.get(BUILDING_EMPLOYEE_API_URL + '/list');
    }

    createBuildingEmployee(buildingEmployee){
        return axios.post(BUILDING_EMPLOYEE_API_URL + '/create', buildingEmployee);
    }

    updateBuildingEmployee(id, buildingEmployee){
        return axios.put(BUILDING_EMPLOYEE_API_URL + '/update/'+ id, buildingEmployee);
    }

    deleteBuildingEmployee(id){
        return axios.delete(BUILDING_EMPLOYEE_API_URL + '/delete/'+ id);
    }

    searchByName(name){
        return axios.get(BUILDING_EMPLOYEE_API_URL + '/search?name='+ name);
    }

}
export default new BuildingEmployeeService();