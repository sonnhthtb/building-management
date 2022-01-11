package myteam.project4.controller;

import lombok.AllArgsConstructor;
import myteam.project4.model.request.BuildingEmployeeRequest;
import myteam.project4.model.request.SalaryRequest;
import myteam.project4.model.response.BaseResponse;
import myteam.project4.model.response.BuildingEmployeeResponse;
import myteam.project4.service.BuildingEmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("public-api/v1.0.0/building_employee")
@AllArgsConstructor
public class BuildingEmployeeController {

    private final BuildingEmployeeService buildingEmployeeService;

    @GetMapping("{id}")
    BaseResponse<BuildingEmployeeResponse> getBuildingEmployeeById(@PathVariable Long id){
        return BaseResponse.ofSuccess(buildingEmployeeService.findById(id));
    }

    @GetMapping("/list")
    BaseResponse<List<BuildingEmployeeResponse>> getAllBuildingEmployee(){
        return BaseResponse.ofSuccess(buildingEmployeeService.getAllBuildingEmployee());
    }

    @PostMapping("/create")
    BaseResponse<BuildingEmployeeResponse> createBuildingEmployee(@RequestBody @Valid BuildingEmployeeRequest buildingEmployeeRequest){
        return BaseResponse.ofSuccess(buildingEmployeeService.save(buildingEmployeeRequest));
    }

    @PutMapping("/update/{id}")
    BaseResponse<BuildingEmployeeResponse> updateBuildingEmployee(@PathVariable Long id, @RequestBody @Valid BuildingEmployeeRequest request){
        return BaseResponse.ofSuccess(buildingEmployeeService.save(id,request));
    }

    @DeleteMapping("/delete/{id}")
    BaseResponse<String> deleteBuildingEmployeeById(@PathVariable Long id){
        return BaseResponse.ofSuccess(buildingEmployeeService.deleteById(id));
    }

    @GetMapping("/search")
    BaseResponse<List<BuildingEmployeeResponse>> getBuildingEmployeeByName(@RequestParam String name){
        return BaseResponse.ofSuccess(buildingEmployeeService.getBuildingEmployeeByName(name));
    }

}
