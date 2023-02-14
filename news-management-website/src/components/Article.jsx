
import React from "react";
import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import { Link } from "react-router-dom";
import '../styles/ListArticles.css'

const Article = (props) => {
  const { id, title, author, shortDescription, coverPhotoURL } = props.article;

  const content = shortDescription

  const truncate = (content, maxLength) => {
    if(content.length <= maxLength){
      return content;
    }
    return content.substring(0, maxLength) + "...";
  }

  return (
    <div className="d-flex justify-content-between divwhole">
      <Card className="card m-3 card-display">
        <Card.Img variant="top" src={coverPhotoURL} />
        <Card.Body>
          <Card.Title>
            <b className="title">{title}</b>
          </Card.Title>
          <Card.Text>{truncate(content, 100)}</Card.Text>
          <Card.Text>{author}</Card.Text>
          <Button>
            <Link
              variant="primary"
              to={`/fullStory/${id}`}
              style={{ textDecoration: "none", color: "white" }}
            >
              Full Story
            </Link>
          </Button>
        </Card.Body>
      </Card>
    </div>
  );
};

export default Article;
