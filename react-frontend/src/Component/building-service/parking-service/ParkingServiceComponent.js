import React, { Component } from 'react';
import ParkingServiceService from "./ParkingServiceService";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class ParkingService extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentParkingService: {
                name: "",
                price: 0,
                slot: 0,
            },
            newParkingService: {
                name: "",
                price: 0,
                slot: 0,
            },
        }
        toast.configure();
    }
    componentDidMount() {
        ParkingServiceService.getCurrentParkingService().then((response) => {
            if (response.data.code !== 404) {
                this.setState({ currentParkingService: response.data.data });
                this.setState({ newParkingService: response.data.data })
            }
        });
    }

    handleChanged = (event) => {
        let parkingService = this.state.newParkingService;
        const name = event.target.name;
        const value = event.target.value;
        if (name === "name") {
            parkingService.name = value;
        } else if (name === "price") {
            parkingService.price = value;
        }
        else if (name === "slot") {
            parkingService.slot = value;
        }
        this.setState({
            newParkingService: parkingService
        })
    }


    addNewParkingService() {
        if (this.state.newParkingService.name === "" || this.state.newParkingService.price === "" || this.state.newParkingService.slot === "") {
            toast.error('Please fill all the empty!!');
        } else {
            ParkingServiceService.createParkingService(this.state.newParkingService)
                .then(() => this.componentDidMount());
            toast.success('Updated Parking Service successfully!!!');
        }
    }

    render() {
        return (
            <div>
                <div className="modal fade" id="formParkingService" tabIndex="-1" aria-labelledby="exampleModalLabel"
                    aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title" id="exampleModalLabel">Parking Service Information</h5>
                                <button type="button" className="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close" />
                            </div>
                            <div className="modal-body">
                                <form>
                                    <div className="mb-3">
                                        <label htmlFor="name" className="form-label">Tên</label>
                                        <input type="text" onChange={event => this.handleChanged(event)} className="form-control" name="name" id="name"
                                            value={this.state.newParkingService.name} />
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="price" className="form-label">Giá</label>
                                        <input type="number" onChange={event => this.handleChanged(event)} className="form-control" name="price" id="price"
                                            value={this.state.newParkingService.price} />
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="times" className="form-label">Số lượng</label>
                                        <input type="number" onChange={event => this.handleChanged(event)} className="form-control" name="slot" id="slot"
                                            value={this.state.newParkingService.slot} />
                                    </div>
                                </form>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close
                                </button>
                                <button type="button" className="btn btn-primary" data-bs-dismiss="modal"
                                    onClick={(event) => this.addNewParkingService(event)}>Update
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <main>
                    <div className="container-fluid px-4">
                        <h1 className="mt-4">Parking Service</h1>
                        <div className="card mb-4">
                            <div className="card-body">
                                <button type="button" className="btn btn btn-success" data-bs-toggle="modal"
                                    data-bs-target="#formParkingService">Update
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
                                            <td>{this.state.currentParkingService.name}</td>
                                        </tr>
                                        <tr>
                                            <td><b>Giá:</b></td>
                                            <td>{this.state.currentParkingService.price} vnđ</td>
                                        </tr>
                                        <tr>
                                            <td><b>Số lượng:</b></td>
                                            <td>{this.state.currentParkingService.slot}</td>
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

export default ParkingService;
