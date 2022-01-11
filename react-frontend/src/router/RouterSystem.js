import React, { Component } from 'react';
import Home from '../Component/home/Home';
import {
    Switch,
    Route,
  } from "react-router-dom";
import CompanyTable from '../Component/company-table/CompanyTable';
import Salary from '../Component/building-employee/Salary';
import Service from "../Component/building-service/Service";
import CleanedServiceComponent from "../Component/building-service/cleaned-service/CleanedServiceComponent";
import BuildingEmployee from "../Component/building-employee/BuildingEmployee";
import MaintenanceService from "../Component/building-service/maintenance-service/MaintenanceService";
import EmployeeDetail from "../Component/company-table/EmployeeDetail";
import FoodServiceComponent from "../Component/building-service/food-service/FoodServiceComponent";
import ParkingServiceComponent from "../Component/building-service/parking-service/ParkingServiceComponent";
import ProtectedServiceComponent from "../Component/building-service/protected-service/ProtectedServiceComponent";
import CompanyStat from "../Component/company-stat/CompanyStat";
import EmployeeStat from "../Component/employee-stat/EmployeeStat";

class RouterSystem extends Component {
    render() {
        return (
            <div>
                <Switch>
                    <Route exact path="/"><Home/></Route>
                    <Route path="/companies"><CompanyTable/></Route>
                    <Route path="/building-employee"><BuildingEmployee/></Route>
                    <Route path="/salary"><Salary/></Route>
                    <Route path="/company-stat"><CompanyStat/></Route>
                    <Route path="/employee-stat"><EmployeeStat/></Route>
                    <Route path={"/building-service"}> <Service/></Route>
                    <Route path={"/cleaned-service"}><CleanedServiceComponent/></Route>
                    <Route path={"/maintenance-service"}><MaintenanceService/></Route>
                    <Route path={"/food-service"}><FoodServiceComponent/></Route>
                    <Route path={"/parking-service"}><ParkingServiceComponent/></Route>
                    <Route path={"/protected-service"}><ProtectedServiceComponent/></Route>
                    <Route path="/company-detail/:companyId" component = {EmployeeDetail}></Route>
                </Switch>
            </div>
        );
    }
}

export default RouterSystem;
