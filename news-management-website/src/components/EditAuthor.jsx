import axios from "axios"
import { useEffect, useState } from "react"
import { useNavigate, useParams } from "react-router-dom"
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";

const EditAuthor = () => {

    const [author, setAuthor] = useState([]);
    const [id, setId] = useState(author?.id);
    const [name, setName] = useState(author?.name);
    const [email, setEmail] = useState(author?.email);
    const [password, setPassword] = useState(author?.password);
    const [articlesList, setArticlesList] = useState(author?.articlesList);
    const [twitterLink, setTwitterLink] = useState(author?.twitterLink);

    const authorApi = "http://localhost:8088/api/v1/author";
    const navigate = useNavigate();
    const params = useParams();

    const getAuthor =() => {
        axios.get(`${authorApi}/${params.id}`).then((response) => {
            setId(response.data.id);
            setName(response.data.name);
            setEmail(response.data.email);
            setPassword(response.data.password);
            setArticlesList(response.data.articlesList);
            setTwitterLink(response.data.twitterLink);
        });
    };

    const submitAuthor = (event) => {
        axios.put(authorApi, {
            id: id,
            name: name,
            email: email,
            password: password,
            articlesList: articlesList,
            twitterLink: twitterLink
        }).then(() => navigate(`/author/${params.id}`));
        event.preventDefault();
    };

    useEffect(() => {
        getAuthor();
    }, []);

    return(
        <div className="page">
            <h1 className="title">Edit my account</h1>
            <Form onSubmit={submitAuthor}>
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

                <Form.Group className="mb-3" controlId="formBasicTwitterlink">
                    <Form.Label>Twitter Link</Form.Label>
                    <Form.Control
                    type="twitter link"
                    value={twitterLink}
                    placeholder={twitterLink}
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

export default EditAuthor