package myteam.project4.service;

import myteam.project4.model.request.CompanyRequest;
import myteam.project4.model.response.CompanyDetailResponse;
import myteam.project4.model.response.CompanyResponse;

import java.util.List;

public interface CompanyService {
    CompanyResponse save(CompanyRequest request);

    CompanyResponse updateById(Long id, CompanyRequest request);

    CompanyDetailResponse findById(Long id);

    String deleteById(Long id);

    List<CompanyResponse> getAllCompany();

    List<CompanyResponse> findCompanyByNameLike(String name);
}
