import React, { Component } from 'react';

class Header extends Component {
    render() {
        return (
            <div>
                <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
                    <img class="App-logo" src="/assets/img/office-building.png" width="40px" height="40px"/>
                    <a class="navbar-brand ps-3" href="/">Building Manage</a>
                    <button class= "btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0 ms-auto" id="sidebarToggle" href="#"><i class="fas fa-bars"/></button>

                    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"/></a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#">Settings</a></li>
                                <li><a class="dropdown-item" href="#">Activity Log</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#">Logout</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>
        );
    }
}

export default Header;
