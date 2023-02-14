import React, { useEffect, useState } from "react";
import Table from "react-bootstrap/Table";
import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";
import Button from "react-bootstrap/Button";
import '../styles/AuthorsPageLayout.css'

const Authors = () => {
  const navigate = useNavigate();
  const params = useParams();
  const authorApi = `http://localhost:8088/api/v1/author`;
  const articleApi = `http://localhost:8088/api/v1/article`;
  const [author, setAuthor] = useState([]);

  const getAuthor = () => {
    axios
      .get(`${authorApi}/${params.id}`)
      .then((response) => setAuthor(response.data))
      .catch((error) =>
        console.log("Couldn't retrieve author: " + error.message)
      );
  };

  const handleDeleteArticle = (id) => {
    axios.delete(`${articleApi}/${id}`).then(getAuthor()).catch((error) => console.log(error));
  };
  const handleDeleteAuthor = () => {
    axios
      .delete(`${authorApi}/${params.id}`)
      .then(navigate("/"))
      .then(window.location.reload());
  };

  useEffect(() => {
    getAuthor();
  }, [author.articlesList]);

  const createArticle = () => {
    navigate("/create-article");
  };

  return (
    <div className="page">
      <h2>Welcome: {author.name}</h2>
      <div className="buttons">
        <Button className="edit-btn">
          <Link to={`/edit-author/${params.id}`} className="auth-btn">Edit my account</Link>
        </Button>
        <Button className="new-art">
          <Link to={"/create-article"} className="auth-btn">Create new Article</Link>
        </Button>
        <Button className="new-auth">
          <Link to={"/create-author"} className="auth-btn">Add new Author</Link>
        </Button>
      </div>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Title</th>
            <th>Date</th>
            <th>View</th>
            <th>Edit</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {author.articlesList?.map((article, key) => (
            <tr key={key}>
              <td>{article.title}</td>
              <td>{article.publishDate}</td>
              <td>
                <Button className="view-btn" ><Link className="view-btn-text" to={`/fullStory/${article.id}`}>View</Link></Button>
              </td>
              <td>
                <Button className="view-btn" ><Link className="view-btn-text" to={`/edit-article/${article.id}`}>Edit</Link></Button>
              </td>
              <td>
                <Button className="del-btn" onClick={() => handleDeleteArticle(article.id)}>X</Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
      <div>
        <p className="del" onClick={handleDeleteAuthor}>Delete my account</p>
      </div>
    </div>
  );
};

export default Authors;
