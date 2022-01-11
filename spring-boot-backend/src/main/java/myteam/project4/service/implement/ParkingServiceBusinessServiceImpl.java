package myteam.project4.service.implement;

import lombok.extern.log4j.Log4j2;
import myteam.project4.entity.ParkingService;
import myteam.project4.entity.UsedService;
import myteam.project4.exception.BusinessCode;
import myteam.project4.exception.BusinessException;
import myteam.project4.mapper.ParkingServiceMapper;
import myteam.project4.model.request.ParkingServiceRequest;
import myteam.project4.model.response.ParkingServiceResponse;
import myteam.project4.repository.ServiceRepository;
import myteam.project4.repository.UsedServiceRepository;
import myteam.project4.service.ParkingServiceBusinessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ParkingServiceBusinessServiceImpl implements ParkingServiceBusinessService {

    private final ServiceRepository serviceRepository;

    private final ParkingServiceMapper parkingServiceMapper;

    private final UsedServiceRepository usedServiceRepository;

    public ParkingServiceBusinessServiceImpl(ServiceRepository serviceRepository, ParkingServiceMapper parkingServiceMapper, UsedServiceRepository usedServiceRepository) {
        this.serviceRepository = serviceRepository;
        this.parkingServiceMapper = parkingServiceMapper;
        this.usedServiceRepository = usedServiceRepository;
    }

    @Override
    public ParkingServiceResponse getActiveParkingService() {
        ParkingService parkingService = serviceRepository.findParkingServiceByActiveIs(true).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_CURRENT_SERVICE)
        );
        return parkingServiceMapper.to(parkingService);
    }

    @Override
    @Transactional
    public ParkingServiceResponse createNewParkingService(ParkingServiceRequest request) {
        request.setType("parking");
        if(serviceRepository.findParkingServiceByActiveIs(true).isPresent()) {
            ParkingService oldService = serviceRepository.findParkingServiceByActiveIs(true).get();
            List<UsedService> usedServiceList = usedServiceRepository.findByService(oldService);
            usedServiceRepository.deleteUsedServiceByService(oldService);
            serviceRepository.deactivateAllParkingService();
            ParkingService newService = serviceRepository.save(parkingServiceMapper.to(request));
            usedServiceList = usedServiceList.stream().map(usedService -> {
                UsedService newUsedService = new UsedService();
                newUsedService.setService(newService);
                newUsedService.setCompany(usedService.getCompany());
                newUsedService.setStartDate(new Timestamp((new Date()).getTime()));
                return newUsedService;
            }).collect(Collectors.toList());
            usedServiceRepository.saveAll(usedServiceList);
            return parkingServiceMapper.to(newService);
        } else {
            ParkingService newService = serviceRepository.save(parkingServiceMapper.to(request));
            return parkingServiceMapper.to(newService);
        }
    }
}
