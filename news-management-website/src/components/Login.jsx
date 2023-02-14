import axios from "axios";
import React, { useEffect, useState } from "react";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import "../styles/LoginPageLayout.css";

const Login = () => {
  const userApi = "http://localhost:8088/api/v1/user";
  const authorApi = "http://localhost:8088/api/v1/author";
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [authorList, setAuthorList] = useState([]);
  const [userList, setUserList] = useState([]);
  const [invalidLogin, setInvalidLogin] = useState(false);

  const handleSubmit = (event) => {
    const author = authorList.filter(
      (author) => author.email === email && author.password === password
    )[0];
    const user = userList.filter(
      (user) => user.email === email && user.password === password
    )[0];

    if (!user && !author) {
      setInvalidLogin(true);
    } else {
      if (user) {
        navigate("/user/" + user.id);
        setInvalidLogin(false);
      } else {
        navigate("/author/" + author.id);
        setInvalidLogin(false);
      }
    }
    event.preventDefault();
  };

  const getUsersAndAuthors = () => {
    axios.get(authorApi).then((response) => setAuthorList(response.data));
    axios.get(userApi).then((response) => setUserList(response.data));
  };

  useEffect(() => {
    getUsersAndAuthors();
  }, []);

  return (
    <div className="page">
      <div className="login-field">
        <h1 className="title">Login</h1>
        <div className="fields">
          <Form onSubmit={handleSubmit}>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Email address</Form.Label>
              <Form.Control
                type="email"
                placeholder="Enter email"
                onChange={(event) => setEmail(event.target.value)}
              />
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control
                type="password"
                placeholder="Password"
                onChange={(event) => setPassword(event.target.value)}
              />
            </Form.Group>
            {invalidLogin && <div class="text-danger">Login incorrect</div>}
            <Button variant="primary" className="register-btn">
              <Link className="register-btn-text" to={"create-user"}>
                Register
              </Link>
            </Button>
            <Button variant="primary" type="submit" className="sbmt-btn">
              Submit
            </Button>
          </Form>
        </div>
      </div>
    </div>
  );
};

export default Login;
