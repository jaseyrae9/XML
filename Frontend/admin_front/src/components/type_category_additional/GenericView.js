import React from "react"
import { Table } from 'reactstrap'
import { get, post, update, del } from '../crud'
import { returnHeader, returnBody, returnModal, returnForm } from './rendering'

class GenericView extends React.Component {

    state = {
        modal: false,
        data: [],
        isLoading: true,
        error: null,
        toSend: {}
    };

    componentDidMount() {
        this.setState({ isLoading: true });
        get(this.props.url, this.readCRUD)
    }

    readCRUD = (data, isLoading, error) => {
        this.setState({ error, data, isLoading })
    }

    toggle = () => {
        console.log("Change modal state")
        this.setState({ modal: !this.state.modal });
    }

    updateInternal = (toSend) => {
        console.log("UpdateInternal")
        this.setState({ toSend });
        this.toggle();
    }

    onChange = (e) => {
        console.log("Change toSend");
        const toSend = this.state.toSend;
        toSend[e.target.name] = e.target.value;
        this.setState({ toSend });
    }

    deleteAPI = (id) => {
        console.log("Delete")
        del(this.props.url, id, this.afterDelete)
    }

    post_updateAPI = () => {
        console.log("Post")
        if(!this.state.toSend.id)
            post(this.props.url, this.state.toSend, this.afterPost)
        else
            update(this.props.url, this.state.toSend, this.afterUpdate)
        this.toggle();
    }

    afterPost = (data) => {
        console.log("After Post")
        this.setState({ data: [...this.state.data, data] })
    }

    afterUpdate = (updatedItem) => {
        console.log("After Update")
        let data = this.state.data.map(item => item.id!==updatedItem.id?item:updatedItem)
        this.setState({ data });
    }

    afterDelete = (id) => {
        console.log("After Delete")
        let data = this.state.data.filter(item => item.id !== id)
        this.setState({ data });
    }

    render() {
        const { error, isLoading, data, modal, toSend } = this.state
        const modelData = this.props.modalData
        const headers = returnHeader(modelData)
        const form = returnForm(modelData, toSend, this.onChange)   
        const modalForm = returnModal(modal, this.toggle, form, this.post_updateAPI, this.updateInternal)
        const body = returnBody(error, isLoading, data, this.updateInternal, this.deleteAPI)

        return (
            <Table dark>
                <thead>
                    <tr>
                        {headers}
                        {modalForm}
                    </tr>
                </thead>
                <tbody>
                    {body}
                </tbody>
            </Table>
        );
    }
}

export default GenericView;