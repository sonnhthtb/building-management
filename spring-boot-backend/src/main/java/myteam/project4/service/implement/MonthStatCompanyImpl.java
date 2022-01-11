package myteam.project4.service.implement;

import lombok.AllArgsConstructor;
import myteam.project4.constant.SystemConstant;
import myteam.project4.entity.Company;
import myteam.project4.entity.MonthUsedService;
import myteam.project4.entity.Service;
import myteam.project4.entity.UsedService;
import myteam.project4.mapper.CompanyMapper;
import myteam.project4.model.request.MonthRequest;
import myteam.project4.model.response.MonthStatCompanyResponse;
import myteam.project4.repository.CompanyRepository;
import myteam.project4.repository.MonthUsedServiceRepository;
import myteam.project4.service.MonthStatCompany;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class MonthStatCompanyImpl implements MonthStatCompany {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final MonthUsedServiceRepository monthUsedServiceRepository;

    @Override
    public List<MonthStatCompanyResponse> viewStatistic(int month, int year) {
        List<MonthStatCompanyResponse> monthStatCompanyResponses = new ArrayList<>();
        List<Company> companyList = companyRepository.findAllByIsDeleted(false);
        for (Company company: companyList) {
            MonthStatCompanyResponse response = new MonthStatCompanyResponse();
            response.setCompanyResponse(companyMapper.to(company));
            Long companyId = company.getId();
            List<MonthUsedService> monthUsedServiceList = monthUsedServiceRepository.findByCompanyIdAndDate(companyId, month, year);
            if(!monthUsedServiceList.isEmpty()) {
                float servicePrice = 0;
                int date = SystemConstant.DATE_PER_MONTH;
                for (MonthUsedService monthUsedService : monthUsedServiceList) {
                    UsedService usedService = monthUsedService.getUsedService();
                    Service service = usedService.getService();
                    servicePrice += service.getPrice();
                }
                Long numberOfEmployee = response.getCompanyResponse().getNumberOfEmployee();
                servicePrice = servicePrice + servicePrice * (numberOfEmployee / 5 + (int) (company.getArea() / 10)) * 5 / 100;
                response.setServicePrice(servicePrice);
                response.setRentalPrice(SystemConstant.RENTAL_PRICE * company.getArea());
                response.setTotalPrice(servicePrice + response.getRentalPrice());
                monthStatCompanyResponses.add(response);
            }
        }
        monthStatCompanyResponses = monthStatCompanyResponses.stream().sorted(Comparator.comparing(MonthStatCompanyResponse::getTotalPrice).reversed())
                .collect(Collectors.toList());
        return monthStatCompanyResponses;
    }

    private Timestamp convertStringToTimestamp(String dateOfBirth) {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.from(formatDateTime.parse(dateOfBirth + " 00:00:00"));
        return Timestamp.valueOf(localDateTime);
    }

    private String convertTimestampToString(Timestamp dateOfBirth) {
        return dateOfBirth.toString().split(" ")[0];
    }
}
