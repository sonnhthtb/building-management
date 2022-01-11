import axios from "axios";
const COMPANY_API_URL = "http://localhost:8080/public-api/v1.0.0/company"

class CompanyService{

    getAllCompany(){
        return axios.get(COMPANY_API_URL + '/list');
    }

    createCompany(company){
        return axios.post(COMPANY_API_URL + '/create', company)
    }

    updateCompany(id, company){
        return axios.put(COMPANY_API_URL + '/update/' + id, company)
    }

    deleteCompany(id){
        return axios.delete(COMPANY_API_URL + '/delete/' + id)
    }

    searchByName(name){
        return axios.get(COMPANY_API_URL + '/search?name='+ name);
    }

}

export default new CompanyService();