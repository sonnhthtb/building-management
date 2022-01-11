package myteam.project4.mapper;

import lombok.AllArgsConstructor;
import myteam.project4.entity.BuildingEmployee;
import myteam.project4.entity.MonthSalary;
import myteam.project4.entity.Salary;
import myteam.project4.model.request.BuildingEmployeeRequest;
import myteam.project4.model.request.MonthRequest;
import myteam.project4.model.response.BuildingEmployeeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class BuildingEmployeeMapper implements Mapper<BuildingEmployee> {

    private final SalaryMapper salaryMapper;
    private Timestamp convertStringToTimestamp(String dateOfBirth) {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.from(formatDateTime.parse(dateOfBirth + " 00:00:00"));
        return Timestamp.valueOf(localDateTime);
    }

    private String convertTimestampToString(Timestamp dateOfBirth) {
        return dateOfBirth.toString().split(" ")[0];
    }

    public BuildingEmployee to( BuildingEmployee buildingEmployee ,BuildingEmployeeRequest request) {
        BeanUtils.copyProperties(request, buildingEmployee);
        buildingEmployee.setDateOfBirth(convertStringToTimestamp(request.getDateOfBirth()));
        return buildingEmployee;
    }

    public BuildingEmployeeResponse to(BuildingEmployee buildingEmployee) {
        BuildingEmployeeResponse response = new BuildingEmployeeResponse();
        BeanUtils.copyProperties(buildingEmployee, response);
        response.setDateOfBirth(convertTimestampToString(buildingEmployee.getDateOfBirth()));
        List<MonthSalary> monthSalaryList = buildingEmployee.getMonthSalaryList();
        Salary salary = new Salary();
        for (MonthSalary m: monthSalaryList) {
            if (!m.getIsDeleted()) {
                salary = m.getSalary();
            }
        }
        response.setSalaryResponse(salaryMapper.to(salary));
        return response;
    }

    public BuildingEmployeeResponse toByMonth(BuildingEmployee buildingEmployee, MonthRequest request) {
        BuildingEmployeeResponse response = new BuildingEmployeeResponse();
        BeanUtils.copyProperties(buildingEmployee, response);
        response.setDateOfBirth(convertTimestampToString(buildingEmployee.getDateOfBirth()));
        List<MonthSalary> monthSalaryList = buildingEmployee.getMonthSalaryList();
        Salary salary = new Salary();
        String strartTime = "";
        int month = request.getMonth();
        int year = request.getYear();
        if ( month < 10 ) {
            strartTime = year+ "-0" +month + "-01";
        } else {
            strartTime = year+ "-" +month + "-01";
        }
        if ( month==12 ) {
            month = 1;
            year++;
        } else {
            month++;
        }
        String endTime = "";
        if ( month < 10 ) {
            endTime = year+ "-0" +month + "-01";
        } else {
            endTime = year + "-" +month + "-01";
        }
        Timestamp current = new Timestamp(System.currentTimeMillis());
        Timestamp startCompare = convertStringToTimestamp(strartTime);
        Timestamp endCompare = convertStringToTimestamp(endTime);
        if ( startCompare.after(current) ) {
            return null;
        }
        int check = 1;
        for (MonthSalary m: monthSalaryList) {
            if ( m.getIsDeleted()==false ) {
                if ( (m.getUpdatedAt().after(startCompare) && m.getCreatedAt().before(endCompare)) || m.getUpdatedAt().before(endCompare)  ) {
                    salary = m.getSalary();
                    check=0;
                }
            } else {
                if ( (m.getCreatedAt().before(startCompare) && m.getUpdatedAt().after(endCompare)) ) {
                    salary = m.getSalary();
                    check=0;
                }
            }
        }
        if ( check==1 )
            return null;
        response.setSalaryResponse(salaryMapper.to(salary));
        return response;
    }

}
