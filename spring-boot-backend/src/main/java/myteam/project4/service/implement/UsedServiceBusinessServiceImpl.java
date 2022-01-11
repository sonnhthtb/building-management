package myteam.project4.service.implement;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import myteam.project4.entity.MonthUsedService;
import myteam.project4.entity.UsedService;
import myteam.project4.mapper.UsedServiceMapper;
import myteam.project4.model.request.UsedServiceRequest;
import myteam.project4.model.response.UsedServiceResponse;
import myteam.project4.repository.MonthUsedServiceRepository;
import myteam.project4.repository.UsedServiceRepository;
import myteam.project4.service.UsedServiceBusinessService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class UsedServiceBusinessServiceImpl implements UsedServiceBusinessService {

    private final UsedServiceRepository repository;
    private final MonthUsedServiceRepository monthUsedServiceRepository;
    private final UsedServiceMapper mapper;

    @Override
    public UsedServiceResponse save(UsedServiceRequest request) {
        UsedService usedService = repository.saveAndFlush(mapper.to(request));
        MonthUsedService monthUsedService = new MonthUsedService();
        Date toDate = Date.valueOf(LocalDate.now().withDayOfMonth(1));
        monthUsedService.setFromDate(new Timestamp(System.currentTimeMillis()));
        monthUsedService.setUsedService(usedService);
        monthUsedService.setToDate(new Timestamp(DateUtils.addMonths(toDate,1).getTime()));
        monthUsedServiceRepository.saveAndFlush(monthUsedService);
        return mapper.to(usedService);
    }

    @Override
    @Transactional
    public String deleteByCompanyIdAndServiceId(Long companyId, Long serviceId) {
        UsedService usedService = repository.findByIsDeletedAndCompanyIdAndServiceId(false,companyId, serviceId);
        usedService.setIsDeleted(true);
        repository.saveAndFlush(usedService);
        return "Deleted";
    }

    @Override
    public List<UsedServiceResponse> findUsedServiceMonthByCompany(Long companyId, Long month) {
        List<UsedService> usedServiceList = repository.findUsedServiceMonthByCompanyId(companyId,month);
        return usedServiceList.stream().map(mapper::to).collect(Collectors.toList());
    }
}
