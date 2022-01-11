package myteam.project4.service.implement;

import lombok.extern.log4j.Log4j2;
import myteam.project4.entity.ProtectedService;
import myteam.project4.entity.UsedService;
import myteam.project4.exception.BusinessCode;
import myteam.project4.exception.BusinessException;
import myteam.project4.mapper.ProtectedServiceMapper;
import myteam.project4.model.request.ProtectedServiceRequest;
import myteam.project4.model.response.ProtectedServiceResponse;
import myteam.project4.repository.ServiceRepository;
import myteam.project4.repository.UsedServiceRepository;
import myteam.project4.service.ProtectedServiceBusinessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProtectedServiceBusinessServiceImpl implements ProtectedServiceBusinessService {

    private final ServiceRepository serviceRepository;

    private final ProtectedServiceMapper protectedServiceMapper;

    private final UsedServiceRepository usedServiceRepository;

    public ProtectedServiceBusinessServiceImpl(ServiceRepository serviceRepository, ProtectedServiceMapper protectedServiceMapper, UsedServiceRepository usedServiceRepository) {
        this.serviceRepository = serviceRepository;
        this.protectedServiceMapper = protectedServiceMapper;
        this.usedServiceRepository = usedServiceRepository;
    }

    @Override
    public ProtectedServiceResponse getActiveProtectedService() {
        ProtectedService protectedService = serviceRepository.findProtectedServiceByActiveIs(true).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_CURRENT_SERVICE)
        );
        return protectedServiceMapper.to(protectedService);
    }

    @Override
    @Transactional
    public ProtectedServiceResponse createNewProtectedService(ProtectedServiceRequest request) {
        request.setType("protected");
        if(serviceRepository.findProtectedServiceByActiveIs(true).isPresent()) {
            ProtectedService oldService = serviceRepository.findProtectedServiceByActiveIs(true).get();
            List<UsedService> usedServiceList = usedServiceRepository.findByService(oldService);
            usedServiceRepository.deleteUsedServiceByService(oldService);
            serviceRepository.deactivateAllProtectedService();
            ProtectedService newService = serviceRepository.save(protectedServiceMapper.to(request));
            usedServiceList = usedServiceList.stream().map(usedService -> {
                UsedService newUsedService = new UsedService();
                newUsedService.setService(newService);
                newUsedService.setCompany(usedService.getCompany());
                newUsedService.setStartDate(new Timestamp((new Date()).getTime()));
                return newUsedService;
            }).collect(Collectors.toList());
            usedServiceRepository.saveAll(usedServiceList);
            return protectedServiceMapper.to(newService);
        } else {
            ProtectedService newService = serviceRepository.save(protectedServiceMapper.to(request));
            return protectedServiceMapper.to(newService);
        }
    }
}
