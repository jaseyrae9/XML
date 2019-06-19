import React from 'react'
import { withRouter, Redirect } from 'react-router-dom';

class AuthComponent extends React.Component {

    render() {
        if (localStorage.getItem('token') == null)
            return <Redirect to='/login' />
        else 
            return this.props.children
    }
}
export default withRouter(AuthComponent);