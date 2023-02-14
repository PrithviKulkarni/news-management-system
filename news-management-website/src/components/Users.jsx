import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom"
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/esm/Button";
import Article from "./Article";

const ListUser = () => {
    const params = useParams();
    const navigate = useNavigate();
    const api = `http://localhost:8088/api/v1/user/${params.id}`;
    const [user, setUser] = useState([]);

    const loadUser = () => {
        axios.get(api).then((response) => {
            setUser(response.data);
        }).catch((error) => console.log("Coudn't retrieve user"));
    }

    const handleDelete = (event) => {
        axios.delete(api).then(navigate("/")).catch((error) => console.log("Couldn't delete user"));
    }

    useEffect(() => {
        loadUser();
    }, []);

    const { name, email, password, likedArticles } = user;
    return (
        <div className="page">

            <h2>Welcome: {name}</h2>
            <div>
                <h4>Email: </h4>{email}
                <br/> <br/>
                <h4>Liked Articles: </h4>Coming Soon!
                </div>
                <br />
            <div>
            <div className="buttons">
                <Button className="edit-btn">
                    <Link to={`/edit-user/${params.id}`} className="auth-btn">Edit my account</Link>
                </Button>
            </div>
                <p className="del" onClick={handleDelete}>Delete my account</p>
            </div>


            {/* <Card>
                <Card.Body>
                    <Card.Title>
                        <b>Name: {name}</b>
                    </Card.Title>
                    <Card.Text><b> Email: {email} </b></Card.Text>
                    <Card.Text>
                        <b>Liked Articles:</b>
                        <br />
                        {likedArticles?.map((article, key) => (
                            <Article key={key} article={article} />
                        ))}
                    </Card.Text>
                </Card.Body>
            </Card>
            <Button type="submit" variant="primary" onClick={handleDelete}>
                Delete
            </Button>
            <br />
            <br />
            <Link to={`/edit-user/${params.id}`}>
                <Button type="submit" variant="primary">
                    Edit Details
                </Button>
            </Link> */}
        </div>
    )
}

export default ListUser