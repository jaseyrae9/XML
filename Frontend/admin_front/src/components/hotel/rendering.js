import React from "react"
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Input, Label, FormGroup } from 'reactstrap';
import { Link } from 'react-router-dom'

const returnHeader = (modelData) => {
    return Object.keys(modelData)
        .map(name => typeof modelData[name]!=='object'?(<th key={name}> {name} </th>):
        Object.keys(modelData[name]).map(nestedName => (<th key={nestedName}> {name + '_' + nestedName} </th>)))
}

// pazi uvrnutog resenja na liniji 21, da bi posalo samo podatke a ne kopiju reference na objekat
const returnBody = (error, isLoading, data, updateInternal, del) => {
    if (error) {
        return <tr><td className="tema">{error}</td></tr>
    } else if (isLoading) {
        return <tr><td className="tema"> Loading...</td></tr>
    } else {
        return data.map(object => {
            const values = Object.values(object).map((value, index) => typeof value!=='object'?(<td key={index}>{JSON.stringify(value)}</td>):
            Object.values(value).map((nesteVal, nestedindex) => (<td key={nestedindex}>{JSON.stringify(nesteVal)}</td>))
            );
            return <tr key={object.id}>
                {values}
                <td>
                <Link className="btn btn-info" to={`/hotels/${object.id}`}>Detail</Link>
                </td>
            </tr>
        })
    }
}

const returnModal = (modal, toggle, form, postAPI, updateInternal) => {
    return <th>
        <Button color="success" onClick={() => updateInternal({})}>Add</Button>
        <Modal isOpen={modal} toggle={toggle}>
            <ModalHeader toggle={toggle}>Post dialog</ModalHeader>
            <ModalBody>
                {form}
            </ModalBody>
            <ModalFooter>
                <Button color="primary" onClick={postAPI} method="POST">Save</Button>{' '}
                <Button color="secondary" onClick={toggle}>Back</Button>
            </ModalFooter>
        </Modal>
    </th>
}

const returnForm = (modelData, data, changeToSend, prefix='') => {
    
    return Object.keys(modelData).map((field, index) => {

        let formField
        if (field === 'id')
            return formField = false
        else if (modelData[field] === '')
            formField = <Input name={prefix + field} defaultValue={data[field]} onChange={changeToSend} />
        else if (modelData[field] === 'integer')
            formField = <Input type="number" name={prefix + field} defaultValue={data[field]} onChange={changeToSend} />
        else if (typeof modelData[field] === 'object')
            formField = returnForm(modelData[field], (data[field] || {}), changeToSend, field+'_')
        else if (modelData[field])
            formField = <Input type="checkbox" name={prefix + field} defaultValue={data[field]} onChange={changeToSend} />

        return <FormGroup key={field}>
            <Label className="pr-5">{prefix + field}</Label>
            {formField}
        </FormGroup>
    })
}
export { returnHeader, returnBody, returnModal, returnForm };