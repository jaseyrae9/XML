import React from 'react'
import { Table } from 'reactstrap'
import { Link } from 'react-router-dom'

const Home = () => {
    return (
        <Table dark>
            <tbody>
                <tr>
                    <th scope="row" className="col-1 text-center">1</th>
                    <td>
                        <Link to="/types">Room types</Link>
                    </td>
                </tr>
                <tr>
                    <th scope="row" className="col-1 text-center">2</th>
                    <td>
                        <Link to="/categories">Room category</Link>
                    </td>
                </tr>
                <tr>
                    <th scope="row" className="col-1 text-center">3</th>
                    <td>
                        <Link to="/additional_services">Additional services</Link>
                    </td>
                </tr>
                <tr>
                    <th scope="row" className="col-1 text-center">5</th>
                    <td>
                        <Link to="/hotels">Hotels</Link>
                    </td>
                </tr>
                <tr>
                    <th scope="row" className="col-1 text-center">5</th>
                    <td>
                        <Link to="/customers">Customers</Link>
                    </td>
                </tr>
                <tr>
                    <th scope="row" className="col-1 text-center">4</th>
                    <td>
                        <Link to="/add_agent">New agent</Link>
                    </td>
                </tr>
                <tr>
                    <th scope="row" className="col-1 text-center">6</th>
                    <td>
                        <Link to="/recensions">Recension</Link>
                    </td>
                </tr>
            </tbody>
        </Table>
    )
}

export default Home;
