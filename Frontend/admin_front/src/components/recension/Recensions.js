import React from "react"
import { Table, Button } from 'reactstrap'
import { get, update, del } from '../crud'

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
            body = <tr><td className="tema">Error: {error.message}</td></tr>
        } else if (isLoading) {
            body = <tr><td className="tema"> Loading...</td></tr>
        } else {
            body = data.map(object => {
                const values = Object.values(object).map((value, index) => (<td key={index}>{JSON.stringify(value)}</td>))
                return <tr key={object.id}>
                    {values}
                    <td>
                        <Button color="warning" onClick={() => update(this.props.url, object.id, this.afterDelete)} className="mr-2">Change</Button>
                        <Button color="danger" value={object.id} onClick={() => del(this.props.url, object.id, this.afterDelete)}>Delete</Button>
                    </td>
                </tr>
            })
        }

        return (
            <Table dark>
                <thead>
                    <tr>
                        {headers}
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