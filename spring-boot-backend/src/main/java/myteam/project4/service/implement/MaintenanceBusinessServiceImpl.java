package myteam.project4.service.implement;

import lombok.AllArgsConstructor;
import lombok.Data;
import myteam.project4.entity.CleanedService;
import myteam.project4.entity.MaintenanceService;
import myteam.project4.entity.ParkingService;
import myteam.project4.entity.UsedService;
import myteam.project4.exception.BusinessCode;
import myteam.project4.exception.BusinessException;
import myteam.project4.mapper.CleanedServiceMapper;
import myteam.project4.mapper.MaintenanceMapper;
import myteam.project4.model.request.CleanedRequest;
import myteam.project4.model.request.MaintenanceRequest;
import myteam.project4.model.response.CleanedResponse;
import myteam.project4.model.response.MaintenanceResponse;
import myteam.project4.repository.ServiceRepository;
import myteam.project4.repository.UsedServiceRepository;
import myteam.project4.service.MaintenanceBusinessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MaintenanceBusinessServiceImpl implements MaintenanceBusinessService {

    private final ServiceRepository serviceRepository;

    private final MaintenanceMapper maintenanceMapper;

    private final UsedServiceRepository usedServiceRepository;

    @Override
    public MaintenanceResponse getActiveMaintenanceService() {
        MaintenanceService maintenanceService = serviceRepository.findMaintenanceServiceByActiveIs(true).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_CURRENT_SERVICE)
        );
        return maintenanceMapper.to(maintenanceService);
    }

    @Override
    @Transactional
    public MaintenanceResponse createNewMaintenanceService(MaintenanceRequest request) {
        request.setType("maintenance");
        if(serviceRepository.findMaintenanceServiceByActiveIs(true).isPresent()) {
            MaintenanceService oldService = serviceRepository.findMaintenanceServiceByActiveIs(true).get();
            List<UsedService> usedServiceList = usedServiceRepository.findByService(oldService);
            usedServiceRepository.deleteUsedServiceByService(oldService);
            serviceRepository.deactivateAllMaintenanceService();
            MaintenanceService newService = serviceRepository.save(maintenanceMapper.to(request));
            usedServiceList = usedServiceList.stream().map(usedService -> {
                UsedService newUsedService = new UsedService();
                newUsedService.setService(newService);
                newUsedService.setCompany(usedService.getCompany());
                newUsedService.setStartDate(new Timestamp((new Date()).getTime()));
                return newUsedService;
            }).collect(Collectors.toList());
            usedServiceRepository.saveAll(usedServiceList);
            return maintenanceMapper.to(newService);
        } else {
            MaintenanceService newService = serviceRepository.save(maintenanceMapper.to(request));
            return maintenanceMapper.to(newService);
        }
    }
}
