import React from "react"
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Input, Label, FormGroup, CustomInput } from 'reactstrap';

const returnHeader = (modelData) => {
    return Object.keys(modelData)
        .map(name => (<th key={name}> {name} </th>))
}

// pazi uvrnutog resenja na liniji 21, da bi posalo samo podatke a ne kopiju reference na objekat
const returnBody = (error, isLoading, data, updateInternal, del) => {
    if (error) {
        return <tr><td className="tema">{error}</td></tr>
    } else if (isLoading) {
        return <tr><td className="tema"> Loading...</td></tr>
    } else {
        return data.map(object => {
            const values = Object.values(object).map((value, index) => (<td key={index}>{JSON.stringify(value)}</td>))
            // const active_deactive = data
            return <tr key={object.id}>
                {values}
                <td>
                    <Button color="warning" onClick={() => updateInternal(JSON.parse(JSON.stringify(object)))} className="mr-2">Change</Button>
                    <Button color="danger" value={object.id} onClick={() => del(object.id)}>Delete</Button>
                </td>
            </tr>
        })
    }
}

const returnModal = (modal, toggle, form, postAPI, updateInternal) => {
    return <th>
        <Modal isOpen={modal} toggle={toggle}>
            <ModalHeader toggle={toggle}>Post/Update dialog</ModalHeader>
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

const returnForm = (modelData, data, changeToSend) => {
    return Object.keys(modelData).map((field, index) => {

        let formField
        if (field === 'active')
            return null

        if (field === 'id')
            return formField = false
        else if (modelData[field] === '')
            formField = <Input name={field} defaultValue={data[field]} onChange={changeToSend} disabled/>
        else if (modelData[field] === 'integer')
            formField = <Input type="number" name={field} defaultValue={data[field]} onChange={changeToSend} disabled/>
        else if (modelData[field] === 'bool')
            formField = <div>
                <CustomInput type="radio" id={"radio_true" + field} name={field} label="True" value="True" checked={String(data[field]).toLowerCase()==="true"} onChange={changeToSend}/>
                <CustomInput type="radio" id={"radio_false" + field} name={field} label="False" value="False" checked={String(data[field]).toLowerCase()==="false"} onChange={changeToSend}/>
            </div>

        return <FormGroup key={field}>
            <Label className="pr-5">{field}</Label>
            {formField}
        </FormGroup>
    })
}
export { returnHeader, returnBody, returnModal, returnForm };