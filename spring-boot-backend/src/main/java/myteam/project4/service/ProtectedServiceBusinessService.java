package myteam.project4.service;

import myteam.project4.model.request.ProtectedServiceRequest;
import myteam.project4.model.response.ProtectedServiceResponse;

public interface ProtectedServiceBusinessService {
    ProtectedServiceResponse getActiveProtectedService();
    ProtectedServiceResponse createNewProtectedService(ProtectedServiceRequest request);
}
