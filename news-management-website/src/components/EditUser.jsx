import axios from "axios"
import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

const EditUser = (props) => {

    const [user, setUser] = useState([]);
    const [id, setId] = useState(user?.id);
    const [name, setName] = useState(user?.name);
    const [email, setEmail] = useState(user?.email);
    const [password, setPassword] = useState(user?.password);

    const api = `http://localhost:8088/api/v1/user`;
    let navigate = useNavigate();
    const params = useParams();

    const getUser = () => {
        axios.get(`${api}/${params.id}`).then((response) => {
            setId(response.data.id);
            setName(response.data.name);
            setEmail(response.data.email);
            setPassword(response.data.password);
        })
    }; 

    const submitUser = (event) => {

        axios.put(api, {
            id: id,
            name: name,
            email: email,
            password: password
        }).then(() => navigate(`/user/${params.id}`));

        event.preventDefault()
    };

    useEffect(() => {
        getUser();
    }, []);

    return(
        <div className="page">
            <h1 className="title">Edit Account</h1>
            <Form onSubmit={submitUser}>
                <Form.Group className="mb-3" controlId="formBasicName">
                    <Form.Label>Name</Form.Label>
                    <Form.Control
                    type="name"
                    value={name}
                    placeholder={name}
                    onChange={(event) => setName(event.target.value)}
                    />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Password</Form.Label>
                    <Form.Control
                    type="password"
                    value={password}
                    placeholder={password}
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

export default EditUser