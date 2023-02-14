import axios from "axios"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

const AddUser = () => {

    const [name, setName] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    // const [likedArticles, setLikedArticles] = useState([])

    const [nameInvalid, setNameInvalid] = useState(false)
    const [emailInvalid, setEmailInvalid] = useState(false)

    const api = `http://localhost:8088/api/v1/user`;
    let navigate = useNavigate();

    const submitUser = (event) => {

        setNameInvalid(false)
        setEmailInvalid(false)

        if(name === ''){
            setNameInvalid(true)
        }

        if(email === ''){
            setEmailInvalid(true)
        }

        if(name !== '' && email !== ''){
        axios.post(api, {
            name: name,
            email: email,
            password: password
        }).then(() => navigate('/login')).catch(error => setEmailInvalid(true))
        }

        event.preventDefault()
    }

    return(
        <div className="page">
            <h1 className="title">Register Account</h1>
            <Form onSubmit={submitUser}>
                <Form.Group className="mb-3" controlId="formBasicName">
                    <Form.Label>Name</Form.Label>
                    <Form.Control
                    type="name"
                    placeholder="Enter name"
                    onChange={(event) => setName(event.target.value)}
                    />
                    {nameInvalid && <div class="text-danger">Please enter a name</div>}
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Email address</Form.Label>
                    <Form.Control
                    type="email"
                    placeholder="Enter email"
                    onChange={(event) => setEmail(event.target.value)}
                    />
                    {emailInvalid && <div class="text-danger">Email already exists, please enter a new email</div>}
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                    type="password"
                    placeholder="Password"
                    onChange={(event) => setPassword(event.target.value)}
                    />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </div>
    )
}

export default AddUser