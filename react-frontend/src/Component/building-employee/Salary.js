import React, { Component } from 'react';
import SalaryService from './SalaryService';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class BuildingEmployee extends Component {
    constructor(props) {
        super(props);
        this.state = {
            salaries: [],
            id: 0,
            position: "",
            level: "",
            salary: ""
        }
        toast.configure();
    }

    isChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        console.log(name);
        console.log(value);
        this.setState({
            [name]: value
        })

    }
    getPosition = (positionLike) => {
        const position = positionLike.target.name;
        const value = positionLike.target.value;
        console.log(position);
        console.log(value);
        this.setState({
            position: value
        })

    }

    changeButton = (id) => {
        if (id === 0) {
            return <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onClick={(position, level, salary) => this.addNewSalary(
                position = this.state.position,
                level = this.state.level,
                salary = this.state.salary)}>Add New</button>
        } else {
            return <button type="button" class="btn btn-warning" data-bs-dismiss="modal" onClick={(id, position, level, salary) => this.editSalary(
                id = this.state.id,
                position = this.state.position,
                level = this.state.level,
                salary = this.state.salary)}>Update</button>
        }
    }

    addNewSalary = (position, level, salary) => {
        if(position==="" || level === "" || salary===""){
            toast.error('Please fill all the empty!!')
        }else{
            var Salary = {};
            Salary.position = position;
            Salary.level = level;
            Salary.salary = parseFloat(salary);
            SalaryService.createSalary(Salary).then(() => {
                this.componentDidMount();
            });
            toast.success('Added Salary successfully!!!');
            this.setState({
                id: 0,
                position: "",
                level: "",
                salary: ""
            });
        }
    }

    getSalary = (Salary) => {
        this.setState({
            id: Salary.id,
            position: Salary.position,
            level: Salary.level,
            salary: Salary.salary
        });

    }
    editSalary = (id, position, level, salary) => {
        var Salary = {};
        var idUpdate = parseInt(id);
        var salaryUpdate = parseFloat(salary);
        Salary.id = idUpdate;
        Salary.position = position;
        Salary.level = level;
        Salary.salary = salaryUpdate;
        SalaryService.updateSalary(idUpdate, Salary).then(() => {
            this.componentDidMount();
            // console.log(idUpdate);
        });
        toast.info('Updated Salary successfully!!!');
        this.setState({
            id: 0,
            level: "",
            position: "",
            salary: ""
        });
    }

    deleteSalary = (id) => {
        var idDelete = parseInt(id);
        SalaryService.deleteSalary(idDelete).then(() => {
            this.componentDidMount();
        });
        toast.error('Deleted Salary successfully!!!');
    }

    searchByPosition = () => {
        SalaryService.searchByPosition(this.state.position).then((response) => {
            this.setState({ salaries: response.data.data })
        });
    }
    componentDidMount() {
        SalaryService.getAllSalary().then((response) => {
            this.setState({ salaries: response.data.data });
            console.log(response.data);
        });
    }


    render() {
        return (
            <div>
                {/* form add new Employee */}
                <div class="modal fade" id="formEmployee" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Salary Infomation</h5>
                                <br/>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form>
                                    <div class="mb-3">
                                        <label for="position" class="form-label">Position</label>
                                        <input value={this.state.position} type="text" onChange={(event) => this.isChange(event)} name="position" class="form-control" id="position" />
                                    </div>
                                    <div class="mb-3">
                                        <label for="level" class="form-label">Level</label>
                                        <input value={this.state.level} type="text" onChange={(event) => this.isChange(event)} name="level" class="form-control" id="level" />
                                    </div>
                                    <div class="mb-3">
                                        <label for="level" class="form-label">Salary</label>
                                        <input value={this.state.salary} type="text" onChange={(event) => this.isChange(event)} name="salary" class="form-control" id="salary" />
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                {this.changeButton(this.state.id)}
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-12 ml-auto" id="layoutSidenav_content">
                    <main>
                        <div class="container-fluid px-4">
                            <h1 class="mt-1">Salary Table</h1>
                            <br/>
                            <div class="card mb-4">
                                <div class="card-body">
                                    <div className="row">
                                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                                            <button type="button" class="btn btn btn-success" data-bs-toggle="modal" data-bs-target="#formEmployee">Add new Salary</button>
                                        </div>
                                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                            <form className="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" >
                                                <div class="input-group">
                                                    <input onChange={(event) => this.getPosition(event)} class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                                                    <button onClick={() => this.searchByPosition(this.state.position)} class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    DataTable Salary
                                </div>
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>Position</th>
                                                <th>Level</th>
                                                <th>Salary</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {
                                                this.state.salaries.map((Salary) => (
                                                    <tr key={Salary.id} >
                                                        <td> {Salary.position}</td>
                                                        <td> {Salary.level}</td>
                                                        <td> {Salary.salary}</td>
                                                        <td>
                                                            <button type="button" className="btn btn-warning badge-pill" onClick={() => this.deleteSalary(Salary.id)}>Delete</button>
                                                            <button type="button" className="btn btn-primary" data-bs-toggle="modal" data-bs-target="#formEmployee" onClick={() => this.getSalary(Salary)}>Edit</button>
                                                            
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