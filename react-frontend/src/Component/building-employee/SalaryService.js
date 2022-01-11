import axios from 'axios';

const SALARY_API_URL = "http://localhost:8080/public-api/v1.0.0/salary";

class SalaryService{
    
    getAllSalary(){
        return axios.get(SALARY_API_URL + '/list');
    }

    createSalary(salary){
        return axios.post(SALARY_API_URL + '/create', salary);
    }

    updateSalary(id, salary){
        return axios.put(SALARY_API_URL + '/update/'+ id, salary);
    }

    deleteSalary(id){
        return axios.delete(SALARY_API_URL + '/delete/'+ id);
    }

    searchByPosition(position){
        return axios.get(SALARY_API_URL + '/search?position='+ position);
    }

    getAllPosition() {
        return axios.get(SALARY_API_URL + '/position')
    }

    getAllLevelByPosition(position) {
        return axios.get(SALARY_API_URL + '/level?position=' + position )
    }


}
export default new SalaryService();
