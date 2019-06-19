import React from "react"
import { Button, Table, Input, FormGroup, Label, Col } from 'reactstrap'
import { post } from '../crud'
import { withRouter } from 'react-router-dom';

class NewAgent extends React.Component {

    state = {
        address: {
            country: "",
            state: "",
            city: "",
            postalCode: "",
            street: "",
            streetNumber: "",
            lat: "integer",
            lng: "integer"
        },
        user: {
            username: "",
            firstName: "",
            lastName: "",
        },
        toSend: {
            username: "",
            firstName: "",
            lastName: "",
            address: {
                country: "",
                state: "",
                city: "",
                postalCode: "",
                street: "",
                streetNumber: "",
                lat: null,
                lng: null
            },
            hotelId: null
        }
    };

    onChange = (e) => {
        console.log("Change user");
        const toSend = this.state.toSend;
        toSend[e.target.name] = e.target.value;
        this.setState({ toSend });
    }

    onChangeAddress = (e) => {
        console.log("Change address");
        const toSend = this.state.toSend;
        toSend.address[e.target.name] = e.target.value;
        this.setState({ toSend });
    }

    postAPI = () => {
        console.log("Post")
        post(this.props.url, this.state.toSend, this.afterPost)
    }

    afterPost = (data) => {
        console.log("After Post")
        alert(this.state.toSend.username + "has been added to agents")
        this.props.history.push('/')
        this.props.history.push('/add_agent')
    }

    returnForm = (modelData, data, changeToSend) => {
        return Object.keys(modelData).map((field, index) => {

            let formField
            if (modelData[field] === '')
                formField = <Input name={field} onChange={changeToSend} />
            else if (modelData[field] === 'integer')
                formField = <Input type="number" name={field} id={field} onChange={changeToSend} />

            return <FormGroup key={field} row>
                <Label for={field} md={2}>{field}</Label>
                <Col md={10}>
                    {formField}
                </Col>
            </FormGroup>
        })
    }

    render() {
        const { toSend, address, user } = this.state
        const address_form = this.returnForm(address, toSend.address, this.onChangeAddress)
        const user_form = this.returnForm(user, toSend, this.onChange)

        return (
            <div className="tema">
                <h4 className="text-center">Add a new agent</h4>
                <Table dark>
                    <tbody>
                        <tr>
                            <td className="col-5">
                                <Label className="pr-5">Agent address</Label>
                                {address_form}
                            </td>
                            <td className="col-3">
                                <Label className="pr-5">Agent</Label>
                                {user_form}
                                <FormGroup key='hotelId' row>
                                    <Label for='hotelId' md={2}>hotelId</Label>
                                    <Col md={10}>
                                        <Input type="number" name='hotelId' id='hotelId' onChange={this.onChange} />
                                    </Col>
                                </FormGroup>
                                <Button color="primary" onClick={this.postAPI} method="POST" className="text-center">Add agent</Button>
                            </td>

                            <td className="col-4">
                            </td>
                        </tr>
                    </tbody>
                </Table>
            </div>
        );
    }
}

export default withRouter(NewAgent);