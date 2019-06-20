import React from 'react'
import { Nav, NavItem } from 'reactstrap'
import { NavLink } from 'react-router-dom'

const Home = () => {
    return (
        <div className = "tema">
        <Nav vertical className="p-3">
          <NavItem  className="link">
            <NavLink to="/types">Room types</NavLink>
          </NavItem>
          <NavItem  className="link">
            <NavLink to="/categories">Room category</NavLink>
          </NavItem>
          <NavItem  className="link">
            <NavLink to="/additional_services">Additional services</NavLink>
          </NavItem>
          <NavItem  className="link">
            <NavLink to="/hotels">Hotels</NavLink>
          </NavItem>
          <NavItem  className="link">
            <NavLink to="/customers">Customers</NavLink>
          </NavItem>
          <NavItem  className="link">
            <NavLink to="/add_agent">New agent</NavLink>
          </NavItem>
          <NavItem  className="link">
            <NavLink to="/recensions">Recensions</NavLink>
          </NavItem>
        </Nav>
      </div>
    )
}

export default Home;
