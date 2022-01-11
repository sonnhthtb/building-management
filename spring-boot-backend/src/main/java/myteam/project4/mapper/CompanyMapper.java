package myteam.project4.mapper;

import lombok.AllArgsConstructor;
import myteam.project4.entity.Company;
import myteam.project4.entity.Service;
import myteam.project4.entity.UsedService;
import myteam.project4.model.request.CompanyRequest;
import myteam.project4.model.response.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class CompanyMapper implements Mapper<Company> {
    private final CompanyEmployeeMapper companyEmployeeMapper;

    public Company to(CompanyRequest request){
        Company company = new Company();
        BeanUtils.copyProperties(request,company);
        return company;
    }

    public Company to(Company company, CompanyRequest request){
        BeanUtils.copyProperties(request, company);
        return company;
    }

    public CompanyResponse to(Company company){
        CompanyResponse companyResponse = new CompanyResponse();
        BeanUtils.copyProperties(company,companyResponse);
        if(company.getCompanyEmployeeList() != null){
            Long numberOfEmployee = Long.valueOf(company.getCompanyEmployeeList().size());
            companyResponse.setNumberOfEmployee(numberOfEmployee);
        }
        return companyResponse;
    }

    public CompanyDetailResponse toDetail(Company company){
        CompanyDetailResponse companyDetailResponse = new CompanyDetailResponse();
        BeanUtils.copyProperties(company,companyDetailResponse);
        int numOfEmp = 0;
        float servicePrice = 0;
        if(company.getCompanyEmployeeList() != null){
            List<CompanyEmployeeResponse> companyEmployeeResponseList = companyEmployeeMapper.toList(company.getCompanyEmployeeList(),companyEmployeeMapper::to);
            numOfEmp = companyEmployeeResponseList.size();
            companyDetailResponse.setCompanyEmployeeList(companyEmployeeResponseList);
        }
        if(company.getUsedServiceList() != null){
            List<ServiceResponse> serviceList = new ArrayList<>();
            for(UsedService usedService: company.getUsedServiceList()){
                ServiceResponse serviceResponse = new ServiceResponse();
                serviceResponse.setId(usedService.getService().getId());
                serviceResponse.setName(usedService.getService().getName());
                serviceResponse.setPrice(usedService.getService().getPrice());
                Timestamp currentDate = new Timestamp(System.currentTimeMillis());
                long timeUsed = TimeUnit.DAYS.convert(Math.abs(currentDate.getTime() - usedService.getStartDate().getTime()),TimeUnit.MILLISECONDS);
                servicePrice += (1.0*timeUsed/26) * usedService.getService().getPrice();
                serviceList.add(serviceResponse);
            }
            companyDetailResponse.setServiceList(serviceList);
        }
        float totalPrice = servicePrice + servicePrice * (numOfEmp/5 + (int) (company.getArea()/10)) * 5/100;
        companyDetailResponse.setTotalPrice(totalPrice);
        return companyDetailResponse;
    }
}
