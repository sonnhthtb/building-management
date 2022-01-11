package myteam.project4.controller;

import lombok.AllArgsConstructor;
import myteam.project4.entity.CleanedService;
import myteam.project4.repository.ServiceRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("public-api/v1.0.0/demo")
public class DemoController {
    private final ServiceRepository serviceRepository;
    @PostMapping("/create")
    public CleanedService create() {
        CleanedService cleanedService = new CleanedService();
        cleanedService.setName("Dịch vụ dọn dẹp");
        cleanedService.setPrice(1000.00f);
        cleanedService.setType("clean");
        cleanedService.setTimesPerWeek(1);
        return serviceRepository.save(cleanedService);
    }
}
