import Button from "react-bootstrap/Button";
import InputGroup from "react-bootstrap/InputGroup";
import Form from "react-bootstrap/Form";
import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";

const EditArticle = () => {
  const authorApi = "http://localhost:8088/api/v1/author";
  const articleApi = "http://localhost:8088/api/v1/article";
  const [article, setArticle] = useState([]);
  const [invalidArticle, setInvalidArticle] = useState(false)
  const [authorList, setAuthorList] = useState([]);
  const [id, setid] = useState(article?.id);
  const [title, setTitle] = useState(article?.title);
  const [shortDescription, setShortDescription] = useState(
    article?.shortDescription
  );
  
  const [fullStory, setFullStory] = useState(article?.fullStory);
  const [publishDate, setPublishDate] = useState(article?.publishDate);
  const [category, setCategory] = useState(article?.category);
  const [coverPhotoURL, setCoverPhotoURL] = useState(article?.coverPhotoURL);
  const [author, setAuthor] = useState(article?.authorName);
  const navigate = useNavigate();
  const params = useParams();

  const getAuthors = () => {
    axios.get(authorApi).then((response) => {
      setAuthorList(response.data);
    });
  };

  const getArticle = () => {
    axios.get(`${articleApi}/${params.id}`).then((response) => {
      setid(response.data.id);
      setTitle(response.data.title);
      setShortDescription(response.data.shortDescription);
      setFullStory(response.data.fullStory);
      setPublishDate(response.data.publishDate);
      setCategory(response.data.category);
      setCoverPhotoURL(response.data.coverPhotoURL);
      setAuthor(response.data.authorName);
    });
  };

  const handleSubmit = (event) => {
    const authorPicked = authorList.filter(
      (authorFromList) => authorFromList.name === author
    )[0];
    const article = {
      id: id,
      title: title,
      shortDescription: shortDescription,
      fullStory: fullStory,
      publishDate: publishDate,
      category: category,
      coverPhotoURL: coverPhotoURL,
      authorName: authorPicked.name,
    };
    axios
      .put(`${articleApi}`, article)
      .then(navigate("/"))
      .catch((error) => {
        console.log("Couldn't update article: " + error.message);
      });
    event.preventDefault();
  };

  useEffect(() => {
    getAuthors();
    getArticle();
  }, []);

  return (
    <div className="page">
      <h1 className="title">Edit Article</h1>
      <Form onSubmit={handleSubmit} className="check-form">
        <Form.Label>Name</Form.Label>
        <Form.Control
          type="text"
          value={title}
          placeholder={title}
          onChange={(event) => {
            setTitle(event.target.value);
          }}
        />
        <Form.Label>Short Description</Form.Label>
        <Form.Control
          type="text"
          value={shortDescription}
          placeholder={shortDescription}
          onChange={(event) => {
            setShortDescription(event.target.value);
          }}
        />
        <Form.Label>Full Story</Form.Label>
        <Form.Control
          type="text"
          value={fullStory}
          placeholder={fullStory}
          onChange={(event) => {
            setFullStory(event.target.value);
          }}
        />
        <Form.Label>Publish Date</Form.Label>
        <Form.Control
          type="date"
          value={publishDate}
          placeholder={publishDate}
          onChange={(event) => {
            setPublishDate(event.target.value);
          }}
        />
        <Form.Label>Category</Form.Label>
        <Form.Control
          type="text"
          value={category}
          placeholder={category}
          onChange={(event) => {
            setCategory(event.target.value);
          }}
        />
        <Form.Label>Photo URL</Form.Label>
        <Form.Control
          type="text"
          value={coverPhotoURL}
          placeholder={coverPhotoURL}
          onChange={(event) => {
            setCoverPhotoURL(event.target.value);
          }}
        />
        <Button type="submit">Submit Edit</Button>
      </Form>
    </div>
  );
};

export default EditArticle;
