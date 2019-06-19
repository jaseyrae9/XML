import React from 'react'
import { Navbar, Button } from 'reactstrap'
import { Link, withRouter } from 'react-router-dom'

const MyNavbar = (props) => {

    const onClickOut = (e) => {
        localStorage.removeItem('token')
        props.history.push('/')
    }

    let logoutButton = <Button color="danger" onClick={onClickOut}>Logout</Button>
    return (

        <Navbar color="dark" light>
            <Link className="text-danger h3" to='/'>Admin panel</Link>
            {logoutButton}
        </Navbar>
    )
} 
export default withRouter(MyNavbar);