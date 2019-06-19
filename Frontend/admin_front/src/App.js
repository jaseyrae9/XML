import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import MyNavbar from './Navbar'
import Home from './Home';
import GenericView from './components/type_category_additional/GenericView';
import HotelView from './components/hotel/Hotel';
import Customer from './components/customer_agent/Customer';
import NewAgent from './components/customer_agent/Agent';
import HotelDetails from './components/hotel/HotelDetails';
import Recensions from './components/recension/Recensions';
import AuthComponent from './components/auth/Auth';
import Login from './components/auth/Login';

function App() {

  const roomType = { id: "integer", name: "", active: "bool" }
  const roomCategory = { id: "integer", numberOfStars: "integer", description: "", active: "bool" }
  const additionalServices = { id: "integer", name: "", active: "bool" }
  const hotel = { id: "", name: "", address: { id: "", country: "", state: "", city: "", postalCode: "", street: "", streetNumber: "", lat: "integer", lng: "integer" }, pib: "" }
  const customer = { id: "integer", username: "", firstName: "", lastName: "", blocked: "bool", active: "bool" }
  const recension = { id: "integer", isApproved: "bool", rating: "integer", comment: "", creationDate: "", modificationDate: "" }

  return (
    <BrowserRouter>
      <div className="App">
        <Route component={MyNavbar}/>
        <Route exact path='/login' component={Login} />

        <AuthComponent>

          <Route exact path='/' component={Home} />
          <Route exact path='/types' render={() => <GenericView url='http://127.0.0.1:8762/admin-service/roomType' modalData={roomType} />} />
          <Route exact path='/categories' render={() => <GenericView url='http://127.0.0.1:8762/admin-service/roomCategory' modalData={roomCategory} />} />
          <Route exact path='/additional_services' render={() => <GenericView url='http://localhost:8762/admin-service/additionalService' modalData={additionalServices} />} />
          <Route exact path='/hotels' render={() => <HotelView url='http://localhost:8762/room-service/hotel' modalData={hotel} />} />
          <Route path='/hotels/:id' component={HotelDetails} />
          <Route exact path='/customers' render={() => <Customer url='http://localhost:8762/auth-service/customer' modalData={customer} />} />
          <Route exact path='/add_agent' render={() => <NewAgent url='http://localhost:8762/auth-service/auth/registerAgent' />} />
          <Route exact path='/recensions' render={() => <Recensions url='http://localhost:8762/reservations-service/recension' modalData={recension} />} />

        </AuthComponent>

      </div>
    </BrowserRouter>
  );
}

export default App;
