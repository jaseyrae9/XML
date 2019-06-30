import React from "react"
import { Table, Button } from 'reactstrap'
import { get, getHeader } from '../crud'
import axios from 'axios'

class Recensions extends React.Component {

    state = {
        data: [],
        isLoading: true,
        error: null,
    };

    componentDidMount() {
        this.setState({ isLoading: true });
        get(this.props.url, this.readCRUD)
    }

    readCRUD = (data, isLoading, error) => {
        this.setState({ error, data, isLoading })
    }

    update = (url, data, func) => {
        axios.put(url + "/" + data.id, JSON.stringify(data), getHeader())
                .then(results => func(data.id))
                .catch(error => alert(error))
    }

    afterDelete = (id) => {
        console.log("After Update Delete")
        let data = this.state.data.filter(item => item.id !== id)
        this.setState({ data });
    }


    render() {
        const { error, isLoading, data } = this.state
        const modelData = this.props.modalData
        const headers = Object.keys(modelData).map(name => (<th key={name}> {name} </th>))

        let body
        if (error) {
            body = <tr><td className="tema">{error}</td></tr>
        } else if (isLoading) {
            body = <tr><td className="tema"> Loading...</td></tr>
        } else {
            body = data.map(object => {
                const values = Object.keys(modelData).map((key, index) => (<td key={index}>{JSON.stringify(object[key])}</td>))
                return <tr key={object.id}>
                    {values}
                    <td>
                        <Button color="warning" onClick={() => this.update(this.props.url, object, this.afterDelete)} className="mr-2">Change</Button>
                        {/* <Button color="danger" value={object.id} onClick={() => del(this.props.url, object.id, this.afterDelete)}>Delete</Button> */}
                    </td>
                </tr>
            })
        }

        return (
            <Table dark>
                <thead>
                    <tr>
                        {headers}
                        <td />
                    </tr>
                </thead>
                <tbody>
                    {body}
                </tbody>
            </Table>
        );
    }
}

export default Recensions;
