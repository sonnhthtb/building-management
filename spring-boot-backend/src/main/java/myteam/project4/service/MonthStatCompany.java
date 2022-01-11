package myteam.project4.service;

import myteam.project4.model.request.MonthRequest;
import myteam.project4.model.response.MonthStatCompanyResponse;

import java.util.List;

public interface MonthStatCompany {
    List<MonthStatCompanyResponse> viewStatistic(int month, int year);
}
