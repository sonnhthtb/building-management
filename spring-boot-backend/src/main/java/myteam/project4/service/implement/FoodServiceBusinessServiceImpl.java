package myteam.project4.service.implement;

import lombok.extern.log4j.Log4j2;
import myteam.project4.entity.FoodService;
import myteam.project4.entity.UsedService;
import myteam.project4.exception.BusinessCode;
import myteam.project4.exception.BusinessException;
import myteam.project4.mapper.FoodServiceMapper;
import myteam.project4.model.request.FoodServiceRequest;
import myteam.project4.model.response.FoodServiceResponse;
import myteam.project4.repository.ServiceRepository;
import myteam.project4.repository.UsedServiceRepository;
import myteam.project4.service.FoodServiceBusinessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class FoodServiceBusinessServiceImpl implements FoodServiceBusinessService {

    private final ServiceRepository serviceRepository;

    private final FoodServiceMapper foodServiceMapper;

    private final UsedServiceRepository usedServiceRepository;

    public FoodServiceBusinessServiceImpl(ServiceRepository serviceRepository, FoodServiceMapper foodServiceMapper, UsedServiceRepository usedServiceRepository) {
        this.serviceRepository = serviceRepository;
        this.foodServiceMapper = foodServiceMapper;
        this.usedServiceRepository = usedServiceRepository;
    }

    @Override
    public FoodServiceResponse getActiveFoodService() {
        FoodService foodService = serviceRepository.findFoodServiceByActiveIs(true).orElseThrow(
                () -> new BusinessException(BusinessCode.NOT_FOUND_CURRENT_SERVICE)
        );
        return foodServiceMapper.to(foodService);
    }

    @Override
    @Transactional
    public FoodServiceResponse createNewFoodService(FoodServiceRequest request) {
        request.setType("food");
        if(serviceRepository.findFoodServiceByActiveIs(true).isPresent()) {
            FoodService oldService = serviceRepository.findFoodServiceByActiveIs(true).get();
            List<UsedService> usedServiceList = usedServiceRepository.findByService(oldService);
            usedServiceRepository.deleteUsedServiceByService(oldService);
            serviceRepository.deactivateAllFoodService();
            FoodService newService = serviceRepository.save(foodServiceMapper.to(request));
            usedServiceList = usedServiceList.stream().map(usedService -> {
                UsedService newUsedService = new UsedService();
                newUsedService.setService(newService);
                newUsedService.setCompany(usedService.getCompany());
                newUsedService.setStartDate(new Timestamp((new Date()).getTime()));
                return newUsedService;
            }).collect(Collectors.toList());
            usedServiceRepository.saveAll(usedServiceList);
            return foodServiceMapper.to(newService);
        } else {
            FoodService newService = serviceRepository.save(foodServiceMapper.to(request));
            return foodServiceMapper.to(newService);
        }
    }
}
