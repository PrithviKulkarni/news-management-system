import { useState } from "react"
import { useNavigate } from "react-router-dom"
import axios from "axios";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

const AddAuthor = () => {

    const navigate = useNavigate()
    const api = "http://localhost:8088/api/v1/author"

    const [name, setName] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [articlesList, setArticlesList] = useState([])
    const [twitterLink, setTwitterLink] = useState('')

    const [nameInvalid, setNameInvalid] = useState(false)
    const [emailInvalid, setEmailInvalid] = useState(false)
    const [passwordInvalid, setPasswordInvalid] = useState(false)

    const submitAuthor = (event) => {
        setNameInvalid(false)
        setEmailInvalid(false)

        if(name === ''){
            setNameInvalid(true)
        }

        if(email === ''){
            setEmailInvalid(true)
        }

        if(password === ''){
            setPasswordInvalid(true)
        }

        if(name !== '' && email !== '' && password !== ''){
            axios.post(api,
                {name : name,
                email : email,
                password : password,
                articlesList : articlesList,
                twitterLink : twitterLink})
                .then(() => navigate('/login'))
                .catch(error => setEmailInvalid(true))
        }

        event.preventDefault()
    }

    return(
        <div className="page">
            <h1 className="title">Add a new Author</h1>
            <Form onSubmit={submitAuthor}>
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
                    {passwordInvalid && <div class="text-danger">Please enter a password</div>}
                </Form.Group>

                <Form.Group className="mb-3">
                    <Form.Label>Twitter Link</Form.Label>
                    <Form.Control
                    type="twitterLink"
                    placeholder="Twitter Link"
                    onChange={(event) => setTwitterLink(event.target.value)}
                    />
                </Form.Group>
                <Button variant="primary" type="submit">
                    Submit
                </Button>
            </Form>
        </div>
    )
}

export default AddAuthor