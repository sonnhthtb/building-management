import React, { Component } from 'react';
import FoodServiceService from "./FoodServiceService";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

class FoodService extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentFoodService: {
                name: "",
                price: 0,
                time: 0,
            },
            newFoodService: {
                name: "",
                price: 0,
                time: 0,
            },
        }
        toast.configure();
    }
    componentDidMount() {
        
            FoodServiceService.getCurrentFoodService().then((response) => {
                if (response.data.code !== 404) {
                    this.setState({ currentFoodService: response.data.data });
                    this.setState({ newFoodService: response.data.data })
                }
            });
        
    }

    handleChanged = (event) => {
        let foodService = this.state.newFoodService;
        const name = event.target.name;
        const value = event.target.value;
        if (name === "name") {
            foodService.name = value;
        } else if (name === "price") {
            foodService.price = value;
        }
        else if (name === "time") {
            foodService.time = value;
        }
        this.setState({
            newFoodService: foodService
        })
    }


    addNewFoodService() {
        if (this.state.newFoodService.name === "" || this.state.newFoodService.price === "" || this.state.newFoodService.time === "") {
            toast.error('Please fill all the empty!!');
        } else {
            FoodServiceService.createFoodService(this.state.newFoodService)
                .then(() => this.componentDidMount());
            toast.success('Updated FoodService successfully!!!');
        }
    }

    render() {
        return (
            <div>
                <div className="modal fade" id="formFoodService" tabIndex="-1" aria-labelledby="exampleModalLabel"
                    aria-hidden="true">
                    <div className="modal-dialog">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title" id="exampleModalLabel">Food Service Information</h5>
                                <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close" />
                            </div>
                            <div className="modal-body">
                                <form>
                                    <div className="mb-3">
                                        <label htmlFor="name" className="form-label">Tên</label>
                                        <input type="text" onChange={event => this.handleChanged(event)} className="form-control" name="name" id="name"
                                            value={this.state.newFoodService.name} />
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="price" className="form-label">Giá</label>
                                        <input type="number" onChange={event => this.handleChanged(event)} className="form-control" name="price" id="price"
                                            value={this.state.newFoodService.price} />
                                    </div>
                                    <div className="mb-3">
                                        <label htmlFor="times" className="form-label">Thời gian</label>
                                        <input type="number" onChange={event => this.handleChanged(event)} className="form-control" name="time" id="time"
                                            value={this.state.newFoodService.time} />
                                    </div>
                                </form>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close
                                </button>
                                <button type="button" className="btn btn-primary" data-bs-dismiss="modal"
                                    onClick={(event) => this.addNewFoodService(event)}>Update
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <main>
                    <div className="container-fluid px-4">
                        <h1 className="mt-4">Food Service</h1>
                        <div className="card mb-4">
                            <div className="card-body">
                                <button type="button" className="btn btn btn-success" data-bs-toggle="modal"
                                    data-bs-target="#formFoodService">Update
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
                                            <td>{this.state.currentFoodService.name}</td>
                                        </tr>
                                        <tr>
                                            <td><b>Giá:</b></td>
                                            <td>{this.state.currentFoodService.price} vnđ</td>
                                        </tr>
                                        <tr>
                                            <td><b>Thời gian:</b></td>
                                            <td>{this.state.currentFoodService.time} h/hàng ngày</td>
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

export default FoodService;
