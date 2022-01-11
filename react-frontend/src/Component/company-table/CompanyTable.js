import React, { Component } from 'react';
import CompanyService from './CompanyService';
import './CompanyTable.css';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class CompanyTable extends Component {
    constructor(props) {
        super(props);
        this.state = {
            companies: [],
            id: 0,
            name: "",
            taxCode: "",
            authorizedCapital: 0.0,
            fieldOfActivity: "",
            floor: "",
            hotline: "",
            area: 0.0,
            numberOfEmployee: 0
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
            return <button type="button" className="btn btn-primary" data-bs-dismiss="modal" onClick={(name, taxCode, authorizedCapital, fieldOfActivity, floor, hotline, area, numberOfEmployee) => this.addNewCompany(
                name = this.state.name,
                taxCode = this.state.taxCode,
                authorizedCapital = this.state.authorizedCapital,
                fieldOfActivity = this.state.fieldOfActivity,
                floor = this.state.floor,
                hotline = this.state.hotline,
                area = this.state.area,
                numberOfEmployee = this.state.numberOfEmployee)
            }>Add New</button>
        } else {
            return <button type="button" className="btn btn-warning" data-bs-dismiss="modal" onClick={(id, name, taxCode, authorizedCapital, fieldOfActivity, floor, hotline, area, numberOfEmployee) => this.editCompany(
                id = this.state.id,
                name = this.state.name,
                taxCode = this.state.taxCode,
                authorizedCapital = this.state.authorizedCapital,
                fieldOfActivity = this.state.fieldOfActivity,
                floor = this.state.floor,
                hotline = this.state.hotline,
                area = this.state.area,
                numberOfEmployee = this.state.numberOfEmployee)}>Update</button>
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
        // console.log(value);
        // console.log(this.state)
        CompanyService.searchByName(this.state.name).then((response) => {
            this.setState({ companies: response.data.data })
        });
    }

    addNewCompany = (name, taxCode, authorizedCapital, fieldOfActivity, floor, hotline, area, numberOfEmployee) => {
        if (name === "" || taxCode == "" || authorizedCapital === "" || fieldOfActivity === "" || floor === "" || hotline === "" || area === "" || numberOfEmployee === "") {
            toast.error('Please fill all the empty!!')
        }
        else {
            var company = {};
            company.name = name;
            company.taxCode = taxCode;
            company.authorizedCapital = parseFloat(authorizedCapital);
            company.fieldOfActivity = fieldOfActivity;
            company.floor = floor;
            company.hotline = hotline;
            company.area = parseFloat(area);
            company.numberOfEmployee = parseInt(numberOfEmployee);
            console.log(company)
            CompanyService.createCompany(company).then(() => {
                this.componentDidMount();
            })
            toast.success('Added Company successfully!!!');
            this.setState({
                id: 0,
                name: "",
                taxCode: "",
                authorizedCapital: 0.0,
                fieldOfActivity: "",
                floor: "",
                hotline: "",
                area: 0.0,
                numberOfEmployee: 0
            });
        }
    }

    editCompany = (id, name, taxCode, authorizedCapital, fieldOfActivity, floor, hotline, area, numberOfEmployee) => {
        var company = {};
        company.id = parseInt(id);
        company.name = name;
        company.taxCode = taxCode;
        company.authorizedCapital = parseFloat(authorizedCapital);
        company.fieldOfActivity = fieldOfActivity;
        company.floor = floor;
        company.hotline = hotline;
        company.area = parseFloat(area);
        company.numberOfEmployee = parseInt(numberOfEmployee);
        CompanyService.updateCompany(company.id, company).then(() => {
            this.componentDidMount();
        })
        toast.info('Updated Company successfully!!!');
        this.setState({
            id: 0,
            name: "",
            taxCode: "",
            authorizedCapital: 0.0,
            fieldOfActivity: "",
            floor: "",
            hotline: "",
            area: 0.0,
            numberOfEmployee: 0
        });
    }

    getCompany = (company) => {
        this.setState({
            id: company.id,
            name: company.name,
            taxCode: company.taxCode,
            authorizedCapital: company.authorizedCapital,
            fieldOfActivity: company.fieldOfActivity,
            floor: company.floor,
            hotline: company.hotline,
            area: company.area,
            numberOfEmployee: company.numberOfEmployee
        });
    }

    deleteCompany = (id) => {
        CompanyService.deleteCompany(parseInt(id)).then(() => {
            this.componentDidMount();
        });
        toast.error('Deleted Company successfully!!!');
    }

    componentDidMount() {
        CompanyService.getAllCompany().then((response) => {
            this.setState({ companies: response.data.data })
        })
    }

    render() {
        return (
            <div>
                <main>
                    {/* form add new Company */}
                    <div className="modal fade" id="formCompany" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div className="modal-dialog">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <h5 className="modal-title" id="exampleModalLabel">Company Infomation</h5>
                                    <br />
                                    <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div className="modal-body">
                                    <form>
                                        <div className="mb-3">
                                            <label for="code" className="form-label">Name</label>
                                            <input value={this.state.name} type="text" onChange={(event) => this.isChange(event)} name="name" className="form-control" id="name" />
                                        </div>
                                        <div className="mb-3">
                                            <label for="name" className="form-label">Tax Code</label>
                                            <input value={this.state.taxCode} type="text" onChange={(event) => this.isChange(event)} name="taxCode" className="form-control" id="taxCode" />
                                        </div>
                                        <div className="mb-3">
                                            <label for="dateOfBirth" className="form-label">Authorized Capital</label>
                                            <input value={this.state.authorizedCapital} type="text" onChange={(event) => this.isChange(event)} name="authorizedCapital" className="form-control" id="authorizedCapital" />
                                        </div>
                                        <div className="mb-3">
                                            <label for="address" className="form-label">Field Of Activity</label>
                                            <input value={this.state.fieldOfActivity} type="text" onChange={(event) => this.isChange(event)} name="fieldOfActivity" className="form-control" id="fieldOfActivity" />
                                        </div>
                                        <div className="mb-3">
                                            <label for="phone" className="form-label">Floor</label>
                                            <input value={this.state.floor} type="text" onChange={(event) => this.isChange(event)} name="floor" className="form-control" id="floor" />
                                        </div>
                                        <div className="mb-3">
                                            <label for="position" className="form-label">Hotline</label>
                                            <input value={this.state.hotline} type="text" onChange={(event) => this.isChange(event)} name="hotline" className="form-control" id="hotline" />
                                        </div>
                                        <div className="mb-3">
                                            <label for="level" className="form-label">Area</label>
                                            <input value={this.state.area} type="text" onChange={(event) => this.isChange(event)} name="area" className="form-control" id="area" />
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
                        <h1 className="mt-1">Companies Table</h1>
                        <br />
                        <div className="card mb-4">
                            <div className="card-body">
                                <div className="row">
                                    <div className="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                                        <button type="button" className="btn btn btn-success" data-bs-toggle="modal" data-bs-target="#formCompany">Add new Company</button>
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
                        </div>
                        <div className="card mb-4">
                            <div className="card-header">
                                <i className="fas fa-table me-1"></i>
                                DataTable Company
                            </div>
                            <div className="card-body">
                                <table className="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>TaxCode</th>
                                            <th>Authorized capital</th>
                                            <th>Activity</th>
                                            <th>Quantity</th>
                                            <th>Address</th>
                                            <th>Phone Number</th>
                                            <th>Area</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            this.state.companies.map((company) => (
                                                <tr key={company.id}>
                                                    <td> {company.name} </td>
                                                    <td> {company.taxCode} </td>
                                                    <td> {company.authorizedCapital} </td>
                                                    <td> {company.fieldOfActivity} </td>
                                                    <td> {company.numberOfEmployee} </td>
                                                    <td> {company.floor} </td>
                                                    <td> {company.hotline} </td>
                                                    <td> {company.area} </td>
                                                    <td>
                                                        <button type="button" className="btn btn-danger" onClick={() => this.deleteCompany(company.id)}>Delete</button>
                                                        <button type="button" className="btn btn-primary" data-bs-toggle="modal" data-bs-target="#formCompany" onClick={() => this.getCompany(company)}>Edit</button>
                                                        <button type="button" className="btn btn-warning">
                                                            <a id="viewDetail" href={`/company-detail/${company.id}`}>view Detail</a>
                                                        </button>
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
        );
    }
}

export default CompanyTable;
