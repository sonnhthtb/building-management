package myteam.project4.service;

import myteam.project4.model.request.CleanedRequest;
import myteam.project4.model.response.CleanedResponse;

public interface CleanedServiceBusinessService {
    CleanedResponse getActiveCleanedService();
    CleanedResponse createNewCleanedService(CleanedRequest cleanedRequest);
}
