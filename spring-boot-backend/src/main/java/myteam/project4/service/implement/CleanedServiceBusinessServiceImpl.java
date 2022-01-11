package myteam.project4.service.implement;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import myteam.project4.entity.CleanedService;
import myteam.project4.entity.UsedService;
import myteam.project4.exception.BusinessCode;
import myteam.project4.exception.BusinessException;
import myteam.project4.mapper.CleanedServiceMapper;
import myteam.project4.model.request.CleanedRequest;
import myteam.project4.model.response.CleanedResponse;
import myteam.project4.repository.ServiceRepository;
import myteam.project4.repository.UsedServiceRepository;
import myteam.project4.service.CleanedServiceBusinessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor
public class CleanedServiceBusinessServiceImpl implements CleanedServiceBusinessService {

    private final ServiceRepository serviceRepository;

    private final CleanedServiceMapper cleanedServiceMapper;

    private final UsedServiceRepository usedServiceRepository;

    @Override
    public CleanedResponse getActiveCleanedService() {
        CleanedService cleanedService = serviceRepository.findCleanedServiceByActiveIs(true).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_CURRENT_SERVICE)
        );
        return cleanedServiceMapper.to(cleanedService);
    }

    @Override
    @Transactional
    public CleanedResponse createNewCleanedService(CleanedRequest cleanedRequest) {
        cleanedRequest.setType("cleaned");
        if(serviceRepository.findCleanedServiceByActiveIs(true).isPresent()) {
            CleanedService cleanedService = serviceRepository.findCleanedServiceByActiveIs(true).get();
            List<UsedService> usedServiceList = usedServiceRepository.findByService(cleanedService);
            usedServiceRepository.deleteUsedServiceByService(cleanedService);
            serviceRepository.deactivateAllCleanedService();
            CleanedService newCleanedService = serviceRepository.save(cleanedServiceMapper.to(cleanedRequest));
            usedServiceList = usedServiceList.stream().map(usedService -> {
                UsedService newUsedService = new UsedService();
                newUsedService.setService(newCleanedService);
                newUsedService.setCompany(usedService.getCompany());
                newUsedService.setStartDate(new Timestamp((new Date()).getTime()));
                return newUsedService;
            }).collect(Collectors.toList());
            usedServiceRepository.saveAll(usedServiceList);
            return cleanedServiceMapper.to(newCleanedService);
        } else {
            CleanedService newCleanedService = serviceRepository.save(cleanedServiceMapper.to(cleanedRequest));
            return cleanedServiceMapper.to(newCleanedService);
        }
    }
}
