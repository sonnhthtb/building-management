
import './App.css';
import Menu from "./Component/home/menu/Menu";
import Header from "./Component/home/header/Header";
import RouterSystem from "./router/RouterSystem";


function App() {
  return (
      <div className="container-fluid">
          <div className="row">
              <Header/>
          </div>
          <div className="row mt-5">
              <div className="col-lg-2">
                  <Menu/>
              </div>
              <div className="col-lg-10 mt-5">
                <RouterSystem/>
              </div>
          </div>
      </div>
  );
}

export default App;
