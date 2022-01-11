package myteam.project4.service.implement;

import lombok.AllArgsConstructor;
import myteam.project4.entity.MonthUsedService;
import myteam.project4.entity.UsedService;
import myteam.project4.repository.MonthUsedServiceRepository;
import myteam.project4.repository.UsedServiceRepository;
import myteam.project4.service.MonthUsedServiceBusinessService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MonthUsedServiceBusinessServiceImpl implements MonthUsedServiceBusinessService {

    private final MonthUsedServiceRepository monthUsedServiceRepository;
    private final UsedServiceRepository usedServiceRepository;

    @Override
    @Transactional
    public void updateServicePerMonth() {
        List<UsedService> usedServiceList = usedServiceRepository.findUsedServiceByIsDeleted(false);
        Date fromDate = new Date();
        Date toDate = DateUtils.addMonths(new Date(), 1);
        long fromDateTime = fromDate.getTime();
        long toDateTime = toDate.getTime();
        List<MonthUsedService> monthUsedServices = usedServiceList.stream().map(usedService
                -> new MonthUsedService(usedService, new Timestamp(fromDateTime),new Timestamp(toDateTime)))
                .collect(Collectors.toList());
        monthUsedServiceRepository.saveAll(monthUsedServices);
    }
}
