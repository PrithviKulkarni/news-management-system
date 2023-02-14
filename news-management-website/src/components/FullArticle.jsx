import axios from "axios";
import React, { useEffect, useState } from "react";
import Card from "react-bootstrap/Card";
import Button from "react-bootstrap/esm/Button";
import { Link, useNavigate, useParams } from "react-router-dom";
import '../styles/FullArticle.css'

const FullArticle = (props) => {
  const params = useParams();
  const navigate = useNavigate();
  const api = `http://localhost:8088/api/v1/article/${params.id}`;
  const [fullArticle, setFullArticle] = useState([]);

  const loadArticle = () => {
    axios
      .get(api)
      .then((response) => {
        setFullArticle(response.data);
      })
      .catch((error) => console.log("Couldn't retrieve full story"));
  };

  useEffect(() => {
    loadArticle();
  }, []);

  const handleArticleClick = (event) => {
    navigate("/")
  };

  const { id, title, authorName, shortDescription, fullStory, category, publishDate, coverPhotoURL } =
    fullArticle;

  return (
    <div className="full-art">
      <div><Link to="/" className="return"> &lt; return </Link> </div>
      <img className="cover-img" src={coverPhotoURL} alt="story image" />
      <div className="side-pads">
        <h1>{title}</h1>
        <h5 className="date">{publishDate}</h5>
        <h5>{category}</h5>
        <h5>By: {authorName}</h5>
        <br />
        <b>{shortDescription}</b>
        <br /> <br />
        <p>{fullStory}</p>
       </div> 
    </div>
  );
};

export default FullArticle;
