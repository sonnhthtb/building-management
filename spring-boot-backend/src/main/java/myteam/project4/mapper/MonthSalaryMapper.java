package myteam.project4.mapper;

import myteam.project4.entity.MonthSalary;
import myteam.project4.model.request.MonthRequest;
import myteam.project4.model.response.MonthSalaryResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MonthSalaryMapper implements Mapper<MonthSalary>{

    public MonthSalary to(MonthRequest request) {
        MonthSalary monthSalary = new MonthSalary();
        BeanUtils.copyProperties(request, monthSalary);
        return monthSalary;
    }

    public MonthSalaryResponse to(MonthSalary monthSalary) {
        MonthSalaryResponse response = new MonthSalaryResponse();
        BeanUtils.copyProperties(monthSalary, response);
        return response;
    }
}
