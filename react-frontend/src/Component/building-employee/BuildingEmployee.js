import React, { Component } from 'react';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import BuildingEmployeeService from './BuildingEmployeeService';
import SalaryService from "./SalaryService";

class BuildingEmployee extends Component {
    constructor(props) {
        super(props);
        this.state = {
            buildingEmployees: [],
            newBuildingEmployee: {
                id: 0,
                code: "",
                name: "",
                dateOfBirth: "",
                address: "",
                phone: "",
                position: "",
                level: "",
            },
            listLevel : [],
            listPosition: [],
        }
        toast.configure();
    }

    isChange = (event) => {
        let buildingEmployee = this.state.newBuildingEmployee;
        const name = event.target.name;
        const value = event.target.value;
        // console.log(name);
        // console.log(value);
        if(name === "name") {
            buildingEmployee.name = value
        } else if(name === "dateOfBirth") {
            buildingEmployee.dateOfBirth = value;
        }else if(name === "address") {
            buildingEmployee.address = value;
        }else if(name === "phone") {
            buildingEmployee.phone = value;
        }else if(name === "position") {
            buildingEmployee.position = value;
            SalaryService.getAllLevelByPosition(value).then((response) => {
                this.setState({listLevel: response.data.data})
            });
        }else if(name === "level") {
            buildingEmployee.level = value;
        }
        this.setState({
            newBuildingEmployee: buildingEmployee
        });
        // console.log(this.state.newBuildingEmployee);

    }
    getName = (nameLike) => {
        const value = nameLike.target.value;
        this.setState({
            name: value
        })

    }

    changeButton = () => {
        if (this.state.newBuildingEmployee.id === 0) {
            return <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onClick={() => this.addNewBuildingEmployee()}>Add New</button>
        } else {
            return <button type="button" class="btn btn-warning" data-bs-dismiss="modal" onClick={() => this.editBuildingEmployee()}>Update</button>
        }
    }

    addNewBuildingEmployee = () => {
        if(this.state.newBuildingEmployee.position === "" || this.state.newBuildingEmployee.level === "") {
            this.state.newBuildingEmployee.position = this.state.listPosition[0];
            this.state.newBuildingEmployee.level = 1;
        }
        // console.log(this.state.newBuildingEmployee)
        if(this.state.newBuildingEmployee.name==="" || this.state.newBuildingEmployee.dateOfBirth==="" || this.state.newBuildingEmployee.address==="" || this.state.newBuildingEmployee.phone==="" || this.state.newBuildingEmployee.level==="" || this.state.newBuildingEmployee.position==="" ){
            toast.error('Please fill all the empty!!')
        }else{
            BuildingEmployeeService.createBuildingEmployee(this.state.newBuildingEmployee).then(() => {
                this.componentDidMount();
            });
            this.setState({
                newBuildingEmployee: {
                    id: 0,
                    code: "",
                    name: "",
                    dateOfBirth: "",
                    address: "",
                    phone: "",
                    position: "",
                    level: "",
                }
            });

        }
    }

    getBuildingEmployee = (buildingEmployee) => {
        let editBuildingEmployee = {
            id: 0,
            code: "",
            name: "",
            dateOfBirth: "",
            address: "",
            phone: "",
            position: "",
            level: "",
        };
        editBuildingEmployee.id = buildingEmployee.id;
        editBuildingEmployee.code = buildingEmployee.code;
        editBuildingEmployee.name = buildingEmployee.name;
        editBuildingEmployee.dateOfBirth = buildingEmployee.dateOfBirth;
        editBuildingEmployee.address = buildingEmployee.address;
        editBuildingEmployee.phone = buildingEmployee.phone;
        editBuildingEmployee.position = buildingEmployee.salaryResponse.position;
        editBuildingEmployee.level = buildingEmployee.salaryResponse.level;
        this.setState({
            newBuildingEmployee: editBuildingEmployee
        });

    }
    editBuildingEmployee = () => {
        if(this.state.newBuildingEmployee.position === "" || this.state.newBuildingEmployee.level === "") {
            this.state.newBuildingEmployee.position = this.state.listPosition[0];
            this.state.newBuildingEmployee.level = this.state.listLevel[0];
        }
        console.log(this.state)
        BuildingEmployeeService.updateBuildingEmployee(this.state.newBuildingEmployee.id, this.state.newBuildingEmployee).then(() => {
            this.componentDidMount();
        });
        this.setState({
            newBuildingEmployee: {
                id: 0,
                code: "",
                name: "",
                dateOfBirth: "",
                address: "",
                phone: "",
                position: "",
                level: "",
            }
        });
    }

    deleteBuildingEmployee = (id) => {
        let idDelete = parseInt(id);
        BuildingEmployeeService.deleteBuildingEmployee(idDelete).then(() => {
            this.componentDidMount();
        });
    }

    searchName = (value) => {
        BuildingEmployeeService.searchByName(this.state.name).then((response) => {
            this.setState({ buildingEmployees: response.data.data })
        });
    }
    componentDidMount() {
        BuildingEmployeeService.getAllBuildingEmployee().then((response) => {
            this.setState({ buildingEmployees: response.data.data });
        });
        SalaryService.getAllPosition().then((response) => {
           this.setState({listPosition: response.data.data})
        }).then( () => {
            SalaryService.getAllLevelByPosition(this.state.listPosition[0]).then((response) => {
                this.setState({listLevel: response.data.data});
                if(this.state.newBuildingEmployee.position === "" || this.state.newBuildingEmployee.level === "") {
                    this.state.newBuildingEmployee.position = this.state.listPosition[0];
                    this.state.newBuildingEmployee.level = 1;
                }
            });

        })
    }


    render() {
        return (
            <div>
                {/* form add new Employee */}
                <div class="modal fade" id="formEmployee" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Building Employee Infomation</h5>
                                <br/>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form>
                                    <div class="mb-3">
                                        <label for="name" class="form-label">Name</label>
                                        <input value={this.state.newBuildingEmployee.name} type="text" onChange={(event) => this.isChange(event)} name="name" class="form-control" id="name" />
                                    </div>
                                    <div class="mb-3">
                                        <label for="dateOfBirth" class="form-label">Date Of Birth</label>
                                        <input value={this.state.newBuildingEmployee.dateOfBirth} type="date" onChange={(event) => this.isChange(event)} name="dateOfBirth" class="form-control" id="dateOfBirth" />
                                    </div>
                                    <div class="mb-3">
                                        <label for="address" class="form-label">Address</label>
                                        <input value={this.state.newBuildingEmployee.address} type="text" onChange={(event) => this.isChange(event)} name="address" class="form-control" id="address" />
                                    </div>
                                    <div class="mb-3">
                                        <label for="phone" class="form-label">Phone</label>
                                        <input value={this.state.newBuildingEmployee.phone} type="text" onChange={(event) => this.isChange(event)} name="phone" class="form-control" id="phone" />
                                    </div>
                                    <div class="mb-3">
                                        <label for="position" class="form-label">Position</label>
                                        <select className="form-select" value={this.state.newBuildingEmployee.position} name="position"
                                                onChange={(event) => this.isChange(event)}>
                                            {
                                                this.state.listPosition.map((position) => (
                                                    <option selected={position === this.state.newBuildingEmployee.position} value={position}>{position}</option>
                                                ))
                                            }
                                        </select>
                                    </div>
                                    <div class="mb-3">
                                        <label for="level" class="form-label">Level</label>
                                        <select className="form-select" value={this.state.newBuildingEmployee.level} name="level"
                                                onChange={(event) => this.isChange(event)}>
                                            {
                                                this.state.listLevel.map((level) => (
                                                    <option selected={level === this.state.newBuildingEmployee.level} value={level}>{level}</option>
                                                ))
                                            }
                                        </select>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                {this.changeButton(this.state.newBuildingEmployee.id)}
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-12 ml-auto" id="layoutSidenav_content">
                    <main>
                        <div class="container-fluid px-4">
                            <h1 class="mt-1">Building Employee Table</h1>
                            <div class="card mb-4">
                                <div class="card-body">
                                    <div className="row">
                                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                                            <button type="button" class="btn btn btn-success" data-bs-toggle="modal" data-bs-target="#formEmployee">Add new Employee</button>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                            <form className="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" >
                                                <div class="input-group">
                                                    <input onChange={(event) => this.getName(event)} class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                                                    <button onClick={() => this.searchName(this.state.value)} class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    DataTable Employee
                                </div>
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>Code</th>
                                                <th>Name</th>
                                                <th>Birthday</th>
                                                <th>Address</th>
                                                <th>PhoneNumber</th>
                                                <th>Level</th>
                                                <th>Position</th>
                                                <th>Salary</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {
                                                this.state.buildingEmployees.map((buildingEmployee) => (
                                                    <tr key={buildingEmployee.id} >
                                                        <td> {buildingEmployee.code}</td>
                                                        <td> {buildingEmployee.name}</td>
                                                        <td> {buildingEmployee.dateOfBirth}</td>
                                                        <td> {buildingEmployee.address}</td>
                                                        <td> {buildingEmployee.phone}</td>
                                                        <td> {buildingEmployee.salaryResponse.level}</td>
                                                        <td> {buildingEmployee.salaryResponse.position}</td>
                                                        <td> {buildingEmployee.salaryResponse.salary}</td>
                                                        <td>
                                                            <button type="button" className="btn btn-warning badge-pill" onClick={() => this.deleteBuildingEmployee(buildingEmployee.id)}>Delete</button>
                                                            <button type="button" className="btn btn-primary" data-bs-toggle="modal" data-bs-target="#formEmployee" onClick={() => this.getBuildingEmployee(buildingEmployee)}>Edit</button>
                                                            
                                                        </td>
                                                    </tr>
                                                ))

                                            }
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </main>
                </div>
            </div>
        );
    }
}

export default BuildingEmployee;
