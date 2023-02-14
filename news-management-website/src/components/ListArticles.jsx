import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Article from "./Article";
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

const ListArticles = (props) => {
  const { api } = props;
  const navigate = useNavigate();
  const [articleList, setArticleList] = useState([]);

  const loadArticleList = () => {
    axios
      .get(api)
      .then((response) => setArticleList(response.data))
      .catch((error) => console.log("Couldn't retrieve article"));
  };

  useEffect(() => {
    loadArticleList();
  }, [articleList]);

  return (
    <div>
      <Row xs={1} md={3} className="g-4">
        {articleList.map((article, key) => (
          <Col>
            <Article key={key} article={article} />
          </Col>
        ))}
        </Row>
    </div>
  );
};

export default ListArticles;
