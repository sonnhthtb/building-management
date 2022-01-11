package myteam.project4.mapper;

import myteam.project4.entity.ProtectedService;
import myteam.project4.model.request.ProtectedServiceRequest;
import myteam.project4.model.response.ProtectedServiceResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProtectedServiceMapper implements Mapper<ProtectedServiceMapper>{
    public ProtectedService to(ProtectedServiceRequest request) {
        ProtectedService protectedService = new ProtectedService();
        BeanUtils.copyProperties(request,protectedService);
        return protectedService;
    }

    public ProtectedServiceResponse to(ProtectedService protectedService) {
        ProtectedServiceResponse protectedServiceResponse = new ProtectedServiceResponse();
        BeanUtils.copyProperties(protectedService, protectedServiceResponse);
        return protectedServiceResponse;
    }
}
