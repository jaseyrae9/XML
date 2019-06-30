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
  const hotel = { id: "", pib: "", name: "", address: { id: "", country: "", state: "", city: "", postalCode: "", street: "", streetNumber: "", lat: "integer", lng: "integer" } }
  const customer = { id: "integer", username: "", firstName: "", lastName: "", blocked: "bool", active: "bool" }
  const recension = { id: "integer", isApproved: "bool", rating: "integer", comment: "", creationDate: "", reservationId: "integer", title: "", username: "jeca" }

  // const url_prefix = 'http://localhost:8762'
  const url_prefix = 'http://192.168.0.100:8762'

  return (
    <BrowserRouter>
      <div className="App">
        <Route component={MyNavbar}/>
        <Route exact path='/login' component={Login} />

        <AuthComponent>

          <Route exact path='/' component={Home} />
          <Route exact path='/types' render={() => <GenericView url={url_prefix + '/admin-service/roomType'} modalData={roomType} />} />
          <Route exact path='/categories' render={() => <GenericView url={url_prefix + '/admin-service/roomCategory'} modalData={roomCategory} />} />
          <Route exact path='/additional_services' render={() => <GenericView url={url_prefix + '/admin-service/additionalService'} modalData={additionalServices} />} />
          <Route exact path='/hotels' render={() => <HotelView url={url_prefix + '/room-service/hotel'} modalData={hotel} />} />
          <Route path='/hotels/:id' component={HotelDetails} />
          <Route exact path='/customers' render={() => <Customer url={url_prefix + '/auth-service/customer'} modalData={customer} />} />
          <Route exact path='/add_agent' render={() => <NewAgent url={url_prefix + '/auth-service/registerAgent'} />} />
          <Route exact path='/recensions' render={() => <Recensions url={url_prefix + '/reservations-service/recension'} modalData={recension} />} />

        </AuthComponent>

      </div>
    </BrowserRouter>
  );
}

export default App;
