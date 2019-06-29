import React from 'react'
import { Input, Button, FormGroup, Label } from 'reactstrap';
import { post } from '../crud'

class Login extends React.Component {

    state = {
        username: "",
        password: "",
    }

    onChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }

    onClick = (e) => {
        post('http://127.0.0.1:8762/auth-service/login', { username: this.state.username, password: this.state.password }, this.afterSubmit)
    }

    afterSubmit = (token) => {
        localStorage.setItem('token', token.token);
        this.props.history.push('/')
    }

    render() {
        return <div className="tema">
            <div className="row">
                <form onSubmit={this.handleSubmit} className='col-3 ml-3'>
                    <FormGroup>
                        <Label>Username</Label>
                        <Input autoFocus name='username' onChange={this.onChange} />
                    </FormGroup>
                    <FormGroup>
                        <Label>Password</Label>
                        <Input name='password' onChange={this.onChange} type="password" />
                    </FormGroup>
                    <Button color="success" onClick={this.onClick}>Login</Button>
                </form>
            </div>
        </div>
    }
}

export default Login;