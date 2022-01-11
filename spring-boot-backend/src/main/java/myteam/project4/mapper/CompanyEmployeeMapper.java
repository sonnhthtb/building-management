package myteam.project4.mapper;

import myteam.project4.entity.Company;
import myteam.project4.entity.CompanyEmployee;
import myteam.project4.model.request.CompanyEmployeeRequest;
import myteam.project4.model.response.CompanyEmployeeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CompanyEmployeeMapper implements Mapper<CompanyEmployee>{

    private Timestamp convertStringToTimestamp(String dateOfBirth){
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.from(formatDateTime.parse(dateOfBirth + " 00:00:00"));
        return Timestamp.valueOf(localDateTime);
    }

    private String convertTimestampToString(Timestamp dateOfBirth){
        return dateOfBirth.toString().split(" ")[0];
    }
    public CompanyEmployee to(CompanyEmployeeRequest request){
        CompanyEmployee companyEmployee = new CompanyEmployee();
        Company company = new Company();
        company.setId(request.getCompany_id());
        BeanUtils.copyProperties(request,companyEmployee);
        companyEmployee.setDateOfBirth(convertStringToTimestamp(request.getDateOfBirth()));
        companyEmployee.setCompany(company);
        return companyEmployee;
    }

    public CompanyEmployee to(CompanyEmployee companyEmployee, CompanyEmployeeRequest request){
        BeanUtils.copyProperties(request,companyEmployee);
        companyEmployee.setDateOfBirth(convertStringToTimestamp(request.getDateOfBirth()));
        return companyEmployee;
    }

    public CompanyEmployeeResponse to(CompanyEmployee companyEmployee){
        CompanyEmployeeResponse response = new CompanyEmployeeResponse();
        BeanUtils.copyProperties(companyEmployee, response);
        response.setDateOfBirth(convertTimestampToString(companyEmployee.getDateOfBirth()));
        response.setCompany_id(companyEmployee.getCompany().getId());
        return response;
    }
}
