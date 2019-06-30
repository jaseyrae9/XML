import React from "react"
import { get } from '../crud'
import { Button, Table } from 'reactstrap';

class HotelDetails extends React.Component {

    state = {
        data: {},
        isLoading: true,
        error: null,
        toShow: {},
        showing: false,
    };

    componentDidMount() {
        this.setState({ isLoading: true });
        get('http://192.168.0.100:8762/room-service/hotel/' + this.props.match.params.id, this.readCRUD)
    }

    readCRUD = (data, isLoading, error) => {
        this.setState({ error, data, isLoading })
    }

    updateToShow = (id) => {
        let toShow = this.state.data.rooms.find(item => item.id === id)
        this.setState({ toShow, showing: true })
    }

    returnAddress = (data) => {
        return <div className="pl-3 tema ">
            <table>
                <tbody>
                    <tr>
                        <th>Id:</th>
                        <td className='p-3'></td>
                        <td>{data.id}</td>
                    </tr>
                    <tr>
                        <th>Country:</th>
                        <td className='p-3'></td>
                        <td>{data.country}</td>
                    </tr>
                    <tr>
                        <th>State:</th>
                        <td className='p-3'></td>
                        <td>{data.state}</td>
                    </tr>
                    <tr>
                        <th>City:</th>
                        <td className='p-3'></td>
                        <td>{data.city}</td>
                    </tr>
                    <tr>
                        <th>Postal code:</th>
                        <td className='p-3'></td>
                        <td>{data.postalCode}</td>
                    </tr>
                    <tr>
                        <th>Street:</th>
                        <td className='p-3'></td>
                        <td>{data.street}</td>
                    </tr>
                    <tr>
                        <th>Street Number:</th>
                        <td className='p-3'></td>
                        <td>{data.streetNumber}</td>
                    </tr>
                    <tr>
                        <th>Latitude:</th>
                        <td className='p-3'></td>
                        <td>{data.lat}</td>
                    </tr>
                    <tr>
                        <th>Longitude:</th>
                        <td className='p-3'></td>
                        <td>{data.lng}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    }

    returnRoomType = (data) => {
        return <div className="pl-3 tema ">
            <table>
                <tbody>
                    <tr>
                        <th>Type id:</th>
                        <td className='p-3'></td>
                        <td>{data.id}</td>
                    </tr>
                    <tr>
                        <th>Type name:</th>
                        <td className='p-3'></td>
                        <td>{data.name}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    }

    returnRoomCategory = (data) => {
        return <div className="pl-3 tema ">
            <table>
                <tbody>
                    <tr>
                        <th>Category id:</th>
                        <td className='p-3'></td>
                        <td>{data.id}</td>
                    </tr>
                    <tr>
                        <th>Number of stars:</th>
                        <td className='p-3'></td>
                        <td>{data.numberOfStars}</td>
                    </tr>
                    <tr>
                        <th>Category description:</th>
                        <td className='p-3'></td>
                        <td>{data.description}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    }

    returnRooms = (data) => {
        return <div className="pl-3 tema ">
            <table>
                <tbody>
                    <tr>
                        <th>Room id:</th>
                        <td className='p-3'></td>
                        <td>{data.id}</td>
                    </tr>
                    <tr>
                        <th>Room number:</th>
                        <td className='p-3'></td>
                        <td>{data.roomNumber}</td>
                    </tr>
                    <tr>
                        <th>Number of beds:</th>
                        <td className='p-3'></td>
                        <td>{data.numberOfPeople}</td>
                    </tr>
                    <tr>
                        <th>Room type:</th>
                        <td className='p-3'></td>
                        <td>{this.returnRoomType(data.type)}</td>
                    </tr>
                    <tr>
                        <th>Room category:</th>
                        <td className='p-3'></td>
                        <td>{this.returnRoomCategory(data.category)}</td>
                    </tr>
                    <tr>
                        <th>Room address:</th>
                        <td className='p-3'></td>
                        <td>{this.returnAddress(data.address)}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    }

    render() {
        const { error, isLoading, data } = this.state
        if (error)
            return <p className="tema">Error: {error.message}</p>
        else if (isLoading)
            return <p className="tema"> Loading...</p>
        else {
            const hotel_address = this.returnAddress(data.address)
            const room_detail = this.state.showing ? this.returnRooms((this.state.toShow)) : false
            const hotel_rooms = data.rooms.map(room => <div className="pl-3 tema" key={room.id}>
                <Table dark borderless hover size="sm" className="container-fluid mb-0">
                    <tbody>
                        <tr>
                            <th>Room id:</th>
                            <td className="col-3">{room.id}</td>
                            <td>
                                <Button size="md" color="info" onClick={() => this.updateToShow(room.id)}>Show</Button>
                            </td>
                        </tr>
                    </tbody>
                </Table>
            </div>)

            return (
                <div className='tema'>
                    <Table dark>
                        <tbody>
                            <tr>
                                <td className='col-3'>
                                    <dl className="pl-3">
                                        <dt>Hotel name:</dt>
                                        <dd className='ml-5'>{data.name}</dd>
                                        <dt>Hotel id:</dt>
                                        <dd className='ml-5'>{data.id}</dd>
                                        <dt>Hotel address:</dt>
                                        <dd className='ml-5'>{hotel_address}</dd>
                                    </dl>
                                </td>
                                <td className='col-3'>
                                    <dl className="pl-5">
                                        <dt>Hotel rooms:</dt>
                                        <dd>{hotel_rooms}</dd>
                                    </dl>
                                </td>
                                <td>
                                    <dl className="pl-5">
                                        <dt>Room details:</dt>
                                        <dd>{room_detail}</dd>
                                    </dl>
                                </td>
                            </tr>
                        </tbody>
                    </Table>
                </div>
            )
        }
    }
}
export default HotelDetails