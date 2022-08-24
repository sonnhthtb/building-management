package myteam.project4.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.AllArgsConstructor;
import myteam.project4.model.request.CompanyEmployeeRequest;
import myteam.project4.model.request.CompanyRequest;
import myteam.project4.model.request.UsedServiceRequest;
import myteam.project4.model.response.*;
import myteam.project4.service.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("public-api/v1.0.0/company")
public class CompanyController {

    private final CompanyService companyService;
    private final UsedServiceBusinessService usedService;
    private final ServiceBusinessService service;
    private final CompanyEmployeeService companyEmployeeService;
    private final MonthStatCompany monthStatCompany;

    @GetMapping("{id}")
    BaseResponse<CompanyDetailResponse> getCompanyById(@PathVariable Long id){
        return BaseResponse.ofSuccess(companyService.findById(id));
    }

    @GetMapping("/list")
    BaseResponse<List<CompanyResponse>> getAllCompany(){
        return BaseResponse.ofSuccess(companyService.getAllCompany());
    }

    @PutMapping("/update/{id}")
    BaseResponse<CompanyResponse> updateCompany(@PathVariable Long id, @RequestBody @Valid CompanyRequest request){
        return BaseResponse.ofSuccess(companyService.updateById(id,request));
    }

    @PostMapping("/create")
    BaseResponse<CompanyResponse> createCompany(@RequestBody @Valid CompanyRequest request){
        return BaseResponse.ofSuccess(companyService.save(request));
    }

    @DeleteMapping("/delete/{id}")
    BaseResponse<String> deleteCompany(@PathVariable Long id){
        return BaseResponse.ofSuccess(companyService.deleteById(id));
    }

    @GetMapping("/search")
    BaseResponse<List<CompanyResponse>> findCompanyByNameLike(@RequestParam String name){
        return BaseResponse.ofSuccess(companyService.findCompanyByNameLike(name));
    }

    @PostMapping("/{company_id}/add-service/{service_id}")
    BaseResponse<UsedServiceResponse> createUsedService(@PathVariable Long company_id, @PathVariable Long service_id){
        UsedServiceRequest request = new UsedServiceRequest(company_id,service_id);
        return BaseResponse.ofSuccess(usedService.save(request));
    }

    @DeleteMapping("/{company_id}/used-service/delete/{service_id}")
    BaseResponse<String> deleteUsedService(@PathVariable Long company_id, @PathVariable Long service_id){
        return BaseResponse.ofSuccess(usedService.deleteByCompanyIdAndServiceId(company_id,service_id));
    }

    @GetMapping("/{company_id}/used-service/month/{month}")
    BaseResponse<List<UsedServiceResponse>> listUsedServiceMonth(@PathVariable Long company_id, @PathVariable Long month){
        return BaseResponse.ofSuccess(usedService.findUsedServiceMonthByCompany(company_id,month));
    }

    @GetMapping("/{company_id}/used-service")
    BaseResponse<List<ServiceResponse>> listServiceNotUsed(@PathVariable Long company_id){
        return BaseResponse.ofSuccess(service.findServiceNotUsedByCompanyId(company_id));
    }

    @PostMapping("/{company_id}/employee/create")
    public BaseResponse<CompanyEmployeeResponse> createCompanyEmployee(@PathVariable Long company_id, @RequestBody @Valid CompanyEmployeeRequest request){
        request.setCompany_id(company_id);
        return BaseResponse.ofSuccess(companyEmployeeService.save(request));
    }

    @PutMapping("/{company_id}/employee/update/{employee_id}")
    public BaseResponse<CompanyEmployeeResponse> updateCompanyEmployeeById(@PathVariable Long company_id,@PathVariable  Long employee_id,@RequestBody CompanyEmployeeRequest request){
        request.setCompany_id(company_id);
        return BaseResponse.ofSuccess(companyEmployeeService.updateById(employee_id,request));
    }

    @GetMapping("/{company_id}/employee/{employee_id}")
    public  BaseResponse<CompanyEmployeeResponse> getCompanyEmployeeById(@PathVariable Long employee_id, @PathVariable String company_id){
        return BaseResponse.ofSuccess(companyEmployeeService.findById(employee_id));
    }
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue = "15"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string",
                    paramType = "query", value = "Sorting criteria in the format: property(,asc|desc). "
                    + "Default sort order is ascending. Multiple sort criteria are supported.")})
    @GetMapping("/{company_id}/employee")
    @PreAuthorize("hasRole('USER')")
    public BaseResponse<Page<CompanyEmployeeResponse>> getCompanyEmployeeByCompanyId(@PathVariable Long company_id,
                                                                                     @ApiIgnore Pageable pageable){
        return BaseResponse.ofSuccess(companyEmployeeService.findByCompanyId(company_id, pageable));
    }

    @GetMapping("/{company_id}/employee/list")
    public BaseResponse<List<CompanyEmployeeResponse>> getAllCompanyEmployee(@PathVariable String company_id){
        return BaseResponse.ofSuccess(companyEmployeeService.getAllCompanyEmployee());
    }

    @DeleteMapping("/{company_id}/employee/delete/{id}")
    public BaseResponse<String> deleteCompanyEmployeeById(@PathVariable Long id, @PathVariable String company_id){
        return BaseResponse.ofSuccess((companyEmployeeService.deleteById(id)));
    }

    @GetMapping("/{company_id}/employee/search")
    public BaseResponse<List<CompanyEmployeeResponse>> findCompanyEmployeeByNameLike(@PathVariable Long company_id, @RequestParam String name){
        return BaseResponse.ofSuccess(companyEmployeeService.findCompanyEmployeeByCompanyAndNameLike(company_id,name));
    }

    @Cacheable(value = "company_statistic", key = "#month+''+#year")
    @GetMapping("/view-statistic/{month}/{year}")
    public BaseResponse<List<MonthStatCompanyResponse>> viewStatistic(@PathVariable int month, @PathVariable int year){
        return BaseResponse.ofSuccess(monthStatCompany.viewStatistic(month, year));
    }
}
