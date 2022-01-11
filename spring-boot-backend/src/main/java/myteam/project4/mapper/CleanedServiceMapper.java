package myteam.project4.mapper;

import myteam.project4.entity.CleanedService;
import myteam.project4.model.request.CleanedRequest;
import myteam.project4.model.response.CleanedResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CleanedServiceMapper implements Mapper<CleanedServiceMapper>{

    public CleanedService to(CleanedRequest request) {
        CleanedService cleanedService = new CleanedService();
        BeanUtils.copyProperties(request, cleanedService);
        return cleanedService;
    }

    public CleanedResponse to(CleanedService cleanedService) {
        CleanedResponse response = new CleanedResponse();
        BeanUtils.copyProperties(cleanedService, response);
        return response;
    }
}
