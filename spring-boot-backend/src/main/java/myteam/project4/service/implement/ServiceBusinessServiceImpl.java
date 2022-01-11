package myteam.project4.service.implement;

import lombok.AllArgsConstructor;
import myteam.project4.entity.Service;
import myteam.project4.entity.UsedService;
import myteam.project4.mapper.ServiceMapper;
import myteam.project4.model.response.ServiceResponse;
import myteam.project4.repository.ServiceRepository;
import myteam.project4.repository.UsedServiceRepository;
import myteam.project4.service.ServiceBusinessService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceBusinessServiceImpl implements ServiceBusinessService {

    private final ServiceRepository serviceRepository;
    private final UsedServiceRepository usedServiceRepository;
    private final ServiceMapper mapper;

    @Override
    public List<ServiceResponse> findAllService() {
        return null;
    }

    @Override
    public List<ServiceResponse> findServiceNotUsedByCompanyId(Long companyId) {
        List<UsedService> usedServiceList = usedServiceRepository.findByIsDeletedAndCompanyId(false, companyId);
        List<Service> serviceNotUsed = new ArrayList<>();
        List<Service> serviceList = serviceRepository.findAllByActive(true);
        List<Service> serviceUsedList = usedServiceList.stream().map(usedService ->
                usedService.getService()).collect(Collectors.toList());
        for(Service service: serviceList){
            boolean flag = false;
            for(Service serviceUsed: serviceUsedList){
                if(service.getType().equals(serviceUsed.getType())){
                    flag = true;
                    break;
                }
            }
            if(!flag) serviceNotUsed.add(service);
        }

        return serviceNotUsed.stream().map(mapper::to).collect(Collectors.toList());
    }
}
