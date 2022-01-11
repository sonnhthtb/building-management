import React, { Component } from 'react';
import MaintenanceServiceService from "./MaintenanceServiceService";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class MaintenanceService extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentMaintenanceService: {
                name: "",
                price: 0,
                period: 0,
            },
            newMaintenanceService: {
                name: "",
                price: 0,
                period: 0,
            },
        }
        toast.configure();
    }

    componentDidMount() {
        MaintenanceServiceService.getCurrentMaintenanceService().then((response) => {
            if (response.data.code !== 404) {
                this.setState({ currentMaintenanceService: response.data.data });
                this.setState({ newMaintenanceService: response.data.data })
            }
        });
    }

    handleChanged = (event) => {
        let maintenanceService = this.state.newMaintenanceService;
        const name = event.target.name;
        const value = event.target.value;
        if (name === "name") {
            maintenanceService.name = value;
        } else if (name === "price") {
            maintenanceService.price = value;
        }
        else if (name === "period") {
            maintenanceService.period = value;
        }
        this.setState({
            newMaintenanceService: maintenanceService
        })
    }


    addNewMaintenanceService() {
        if (this.state.newMaintenanceService.name === "" || this.state.newMaintenanceService.price === "" || this.state.newMaintenanceService.period === "") {
            toast.error('Please fill all the empty!!');
        } else {
            MaintenanceServiceService.createMaintenanceService(this.state.newMaintenanceService)
                .then(() => this.componentDidMount());
            toast.success('Updated Maintenance Service successfully!!!');
        }
    }

    render() {
        return (
            <div>
                <div className="modal fade" id="formCleanedService" tabIndex="-1" aria-labelledby="exampleModalLabel"
                    aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title" id="exampleModalLabel">Maintenance Service Information</h5>
                                <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close" />
                            </div>
                            <div className="modal-body">
                                <form>
                                    <div className="mb-3">
                                        <label htmlFor="name" className="form-label">Tên</label>
                                        <input type="text" onChange={event => this.handleChanged(event)} className="form-control" name="name" id="name"
                                            value={this.state.newMaintenanceService.name} />
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="price" className="form-label">Giá</label>
                                        <input type="number" onChange={event => this.handleChanged(event)} className="form-control" name="price" id="price"
                                            value={this.state.newMaintenanceService.price} />
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="times" className="form-label">Tần suất</label>
                                        <input type="number" onChange={event => this.handleChanged(event)} className="form-control" name="period" id="times"
                                            value={this.state.newMaintenanceService.period} />
                                    </div>
                                </form>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close
                                </button>
                                <button type="button" className="btn btn-primary" data-bs-dismiss="modal"
                                    onClick={(event) => this.addNewMaintenanceService(event)}>Update
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <main>
                    <div className="container-fluid px-4">
                        <h1 className="mt-4">Maintenance Service</h1>
                        <div className="card mb-4">
                            <div className="card-body">
                                <button type="button" className="btn btn btn-success" data-bs-toggle="modal"
                                    data-bs-target="#formCleanedService">Update
                                </button>
                            </div>
                        </div>
                        <div className="card mb-4">
                            <div className="card-header">
                                Thông tin dịch vụ
                            </div>
                            <div className="card-body">
                                <table className="table table-bordered text-center">
                                    <tbody>
                                        <tr>
                                            <td> <b>Tên:</b></td>
                                            <td>{this.state.currentMaintenanceService.name}</td>
                                        </tr>
                                        <tr>
                                            <td><b>Giá:</b></td>
                                            <td>{this.state.currentMaintenanceService.price} vnđ</td>
                                        </tr>
                                        <tr>
                                            <td><b>Tần suất:</b></td>
                                            <td>{this.state.currentMaintenanceService.period} lần/tháng</td>
                                        </tr>
                                        <tr>
                                            <td><b>Bắt buộc:</b></td>
                                            <td>Không</td>
                                        </tr>
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

export default MaintenanceService;
