package myteam.project4.service;

import myteam.project4.model.response.ServiceResponse;

import java.util.List;

public interface ServiceBusinessService {
    List<ServiceResponse> findAllService();
    List<ServiceResponse> findServiceNotUsedByCompanyId(Long companyId);
}
