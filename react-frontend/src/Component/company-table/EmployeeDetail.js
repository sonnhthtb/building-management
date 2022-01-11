import React, { Component } from 'react';
import CompanyEmployeeService from './CompanyEmployeeService';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class EmployeeDetail extends Component {

    constructor(props) {
        super(props);
        this.state = {
            companyEmployees: [],
            serviceNotUsed: [],
            company: {
                id: 0,
                name: "",
                taxCode: "",
                authorizedCapital: 0,
                fieldOfActivity: "",
                floor: "",
                hotline: "",
                area: 0,
                totalPrice: 0,
                companyEmployeeList: [],
                serviceList: [],
            },
            companyId: props.match.params.companyId,
            id: 0,
            code: "",
            identification: "",
            name: "",
            dateOfBirth: "",
            phone: ""
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
    changeButton = (id) => {
        if (id === 0) {
            return <button type="button" className="btn btn-primary" data-bs-dismiss="modal" onClick={(identification, name, dateOfBirth, phone) => this.addNewCompany(
                
                identification = this.state.identification,
                name = this.state.name,
                dateOfBirth = this.state.dateOfBirth,
                phone = this.state.phone)
            }>Add New</button>
        } else {
            return <button type="button" className="btn btn-warning" data-bs-dismiss="modal" onClick={(id, identification, name, dateOfBirth, phone) => this.editCompany(
                id = this.state.id,
                identification = this.state.identification,
                name = this.state.name,
                dateOfBirth = this.state.dateOfBirth,
                phone = this.state.phone)}>Update</button>
        }
    }

    getName = (nameLike) => {
        const value = nameLike.target.value;
        // console.log(name);
        // console.log(value);
        this.setState({
            name: value
        })

    }

    searchName = () => {
        CompanyEmployeeService.searchByName(this.state.name, this.state.companyId).then((response) => {
            let newCompany = this.state.company;
            newCompany.companyEmployeeList = response.data.data;
            this.setState({ company: newCompany })

        });
    }

    addNewCompany = (identification, name, dateOfBirth, phone) => {
        if(identification==="" || name==="" || dateOfBirth==="" || phone===""){
            toast.error('Please fill all the empty!!')
        }else{
            let companyEmployee = {};
            companyEmployee.identification = identification;
            companyEmployee.name = name;
            companyEmployee.dateOfBirth = dateOfBirth;
            companyEmployee.phone = phone;
            companyEmployee.company_id = this.state.companyId;
            CompanyEmployeeService.createCompanyEmployee(companyEmployee.company_id, companyEmployee).then(() => {
                this.componentDidMount();
            });
            toast.success('Added Employee successfully!!!');
            this.setState({
                id: 0,
                code: "",
                identification: "",
                name: "",
                dateOfBirth: "",
                phone: ""
            });
        }
    }

    editCompany = (id, identification, name, dateOfBirth, phone) => {
        let companyEmployee = {};
        companyEmployee.identification = identification;
        companyEmployee.name = name;
        companyEmployee.dateOfBirth = dateOfBirth;
        companyEmployee.phone = phone;
        companyEmployee.companyId = this.state.companyId;
        CompanyEmployeeService.updateCompanyEmployee(companyEmployee.companyId, id, companyEmployee).then(() => {
            this.componentDidMount();
        });
        toast.info('Updated Employee successfully!!!');
        this.setState({
            id: 0,
            code: "",
            identification: "",
            name: "",
            dateOfBirth: "",
            phone: ""
        });
    }

    getCompanyEmployee = (companyEmployee) => {
        this.setState({
            id: companyEmployee.id,
            code: companyEmployee.code,
            identification: companyEmployee.identification,
            name: companyEmployee.name,
            dateOfBirth: companyEmployee.dateOfBirth,
            phone: companyEmployee.phone
        });
    }

    deleteCompanyEmployee = (id) => {
        CompanyEmployeeService.deleteCompanyEmployee(this.state.companyId, parseInt(id)).then(() => {
            this.componentDidMount();
        });
        toast.error('Deleted Employee successfully!!!');
    }

    createNewService = (serviceId) => {
        console.log(serviceId);
        CompanyEmployeeService.createNewService(this.state.companyId, serviceId).then((response)=>{
            this.componentDidMount();
        })
    }
    
    addNewService = () => {
        this.state.serviceNotUsed.map((service) => {
            console.log(service.name);
            return <button type="button" class="btn btn-success" onClick = {() => this.createNewService(service.id)}>{service.name}</button>
        })
        
    }

    deleteService = (serviceId, serviceN) => {
        if(serviceN==="Dịch vụ bảo vệ" || serviceN === "Dịch vụ vệ sinh"){
            toast.error('Không thể xoá dịch vụ này !!!');
        }else{
            CompanyEmployeeService.deleteService(this.state.companyId, serviceId).then((response) => {
                this.componentDidMount();
            })
        }
    }

    componentDidMount() {
        CompanyEmployeeService.getServiceNotUsed(this.state.companyId).then((response) => {
            this.setState({serviceNotUsed: response.data.data});
            console.log(response);
        });
        CompanyEmployeeService.getCompanyById(this.state.companyId).then((response) => {
            this.setState({ company: response.data.data });
            console.log(this.state.company)
        })
    };


    render() {
        return (
            <div>
                <div class="col-lg-12 ml-auto">
                    <main>

                        {/* form add new Company Employee */}
                        <div className="modal fade" id="formEmployee" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div className="modal-dialog">
                                <div className="modal-content">
                                    <div className="modal-header">
                                        <h5 className="modal-title" id="exampleModalLabel">Employee Infomation</h5>
                                        <br />
                                        <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div className="modal-body">
                                        <form>
                                            <div className="mb-3">
                                                <label for="name" className="form-label">Identification</label>
                                                <input value={this.state.identification} type="text" onChange={(event) => this.isChange(event)} name="identification" className="form-control" id="identification" />
                                            </div>
                                            <div className="mb-3">
                                                <label for="code" className="form-label">Name</label>
                                                <input value={this.state.name} type="text" onChange={(event) => this.isChange(event)} name="name" className="form-control" id="name" />
                                            </div>
                                            <div className="mb-3">
                                                <label for="dateOfBirth" className="form-label">Date Of Birth</label>
                                                <input value={this.state.dateOfBirth} type="date" onChange={(event) => this.isChange(event)} name="dateOfBirth" className="form-control" id="dateOfBirth" />
                                            </div>
                                            <div className="mb-3">
                                                <label for="address" className="form-label">Phone</label>
                                                <input value={this.state.phone} type="text" onChange={(event) => this.isChange(event)} name="phone" className="form-control" id="phone" />
                                            </div>
                                        </form>
                                    </div>
                                    <div className="modal-footer">
                                        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                        {this.changeButton(this.state.id)}
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="container-fluid px-4">
                            <h1 className="mt-1">Detail Company Table</h1>
                            <br />
                            <div className="card mb-4">

                                <div className="card-body">
                                    <div className="row">
                                        <div className="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                                            {
                                                this.state.serviceNotUsed.map((service) => (
                                                    <button type="button" class="btn btn-success me-1" onClick = {() => this.createNewService(service.id)}>{service.name}</button>
                                                ))
                                            }
                                        </div>
                                    </div>
                                </div>

                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    DataTable Company Service
                                </div>
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Price</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {
                                                this.state.company.serviceList.map((service) => (
                                                    <tr key={service.id}>
                                                        <td> {service.name}</td>
                                                        <td> {service.price}</td>
                                                        <td>
                                                            <button type="button" className="btn btn-warning badge-pill" onClick={() => this.deleteService(service.id, service.name)}>Delete</button>
                                                        </td>
                                                    </tr>
                                                ))
                                            }
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div className="row">
                                <div class="col-lg-8">
                                    
                                </div>
                                <div class="col-lg-4">
                                    <b>Total Service Price: {this.state.company.totalPrice}</b>
                                </div>
                            </div>
                            <br />
                            <br />
                            <div className="card mb-4">
                                <div className="card-body">
                                    <div className="row">
                                        <div className="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                                            <button type="button" className="btn btn btn-success" data-bs-toggle="modal" data-bs-target="#formEmployee">Add new Employee</button>
                                        </div>
                                        <div className="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                                            <form className="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" >
                                                <div className="input-group">
                                                    <input onChange={(event) => this.getName(event)} className="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                                                    <button onClick={() => this.searchName(this.state.name)} className="btn btn-primary" id="btnNavbarSearch" type="button"><i className="fas fa-search"></i></button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    DataTable Company Employee
                                </div>
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>Code</th>
                                                <th>Name</th>
                                                <th>Date Of Birth</th>
                                                <th>Identification</th>
                                                <th>Phone</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {
                                                this.state.company.companyEmployeeList.map((companyEmployee) => (
                                                    <tr key={companyEmployee.id} >
                                                        <td> {companyEmployee.code}</td>
                                                        <td> {companyEmployee.name}</td>
                                                        <td> {companyEmployee.dateOfBirth}</td>
                                                        <td> {companyEmployee.identification}</td>
                                                        <td> {companyEmployee.phone}</td>
                                                        <td>
                                                            <button type="button" className="btn btn-warning badge-pill" onClick={() => this.deleteCompanyEmployee(companyEmployee.id)}>Delete</button>
                                                            <button type="button" className="btn btn-primary" data-bs-toggle="modal" data-bs-target="#formEmployee" onClick={() => this.getCompanyEmployee(companyEmployee)}>Edit</button>

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

export default EmployeeDetail;
