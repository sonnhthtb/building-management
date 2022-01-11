import React, { Component } from 'react';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import CompanyStatService from './CompanyStatService';

class CompanyStat extends Component {

    constructor(props) {
        super(props);
        this.state = {
            listStatitics: [],
            month: 12,
            year: 2021,
            check : 1
        }
        toast.configure();
    }
    
    getMonth = (event) => {
        const value = event.target.value;
        this.setState({
            month: value
        })
        console.log(value);
        console.log(typeof(this.state.month));
    } 

    getYear = (event) => {
        const value = event.target.value;
        this.setState({
            year: value
        })
    } 

    
    componentDidMount() {
        CompanyStatService.getAll(this.state.month, this.state.year).then((response) => {
            if(response.data.data[0] === null ) {
                toast.error('No statitic in this time !!');
                this.setState({ check: 0 });
                console.log(response);
            }else{
                this.setState({ listStatitics: response.data.data });
                this.setState({ check: 1 });
                // console.log(this.state.listStatitics);
            }
        });

    }
    

    render() {
        return (
            <div>
                <div class="col-lg-12 ml-auto" id="layoutSidenav_content">
                    <main>
                        <div class="container-fluid px-4">
                            <h1 class="mt-1">Company Statitic Table</h1>
                            <br />
                            <div class="card mb-4">
                                <div class="card-body">
                                <div className="row">
                                        <div class="col-lg-2">
                                            <select className="form-select" aria-label="Default select example"
                                                onChange={(event) => this.getMonth(event)}>
                                                <option value="01">1</option>
                                                <option value="02">2</option>
                                                <option value="03">3</option>
                                                <option value="04">4</option>
                                                <option value="05">5</option>
                                                <option value="06">6</option>
                                                <option value="07">7</option>
                                                <option value="08">8</option>
                                                <option value="09">9</option>
                                                <option value="10">10</option>
                                                <option value="11">11</option>
                                                <option value="12" selected>12</option>
                                            </select>
                                        </div>

                                        <div class="col-lg-2">
                                            <select className="form-select" aria-label="Default select example"
                                                onChange={(event) => this.getYear(event)}>
                                                <option value="2017">2017</option>
                                                <option value="2018">2018</option>
                                                <option value="2019">2019</option>
                                                <option value="2020">2020</option>
                                                <option value="2021" selected >2021</option>
                                                <option value="2022">2022</option>
                                            </select>
                                        </div>

                                        <div class="col-lg-2">
                                            <button type="button" class="btn btn-success" onClick={()=> this.componentDidMount()}>Statitic</button>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    DataTable Company Statitics
                                </div>
                                <div class="card-body">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Area</th>
                                                <th>Number Of Employee</th>
                                                <th>Rented Price</th>
                                                <th>Service Price</th>
                                                <th>Total Price</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {
                                                this.state.check !== 0 ?
                                                this.state.listStatitics.map((statitic) => (
                                                    <tr key={statitic.id} >
                                                        <td> {statitic.companyResponse.name}</td>
                                                        <td> {statitic.companyResponse.area}</td>
                                                        <td> {statitic.companyResponse.numberOfEmployee}</td>
                                                        <td> {statitic.rentalPrice}</td>
                                                        <td> {statitic.servicePrice}</td>
                                                        <td> {statitic.totalPrice}</td>
                                                    </tr>
                                                ))
                                                : ""
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

export default CompanyStat;
