import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const CreateArticle = () => {
  const authorApi = "http://localhost:8088/api/v1/author";
  const articleApi = "http://localhost:8088/api/v1/article";
  const [authorList, setAuthorList] = useState([]);
  const [title, setTitle] = useState("");
  const [shortDescription, setShortDescription] = useState("");
  const [fullStory, setFullStory] = useState("");
  const [publishDate, setPublishDate] = useState();
  const [category, setCategory] = useState("");
  const [coverPhotoURL, setCoverPhotoURL] = useState("");
  const [author, setAuthor] = useState("");
  const [invalidArticle, setInvalidArticle] = useState(false);
  const navigate = useNavigate();

  const getAuthors = () => {
    axios.get(authorApi).then((response) => {
      setAuthorList(response.data);
    });
  };

  const handleSubmit = (event) => {
    const authorPicked = authorList.filter(
      (authorFromList) => authorFromList.name === author
    )[0];
    const article = {
      title: title,
      shortDescription: shortDescription,
      fullStory: fullStory,
      publishDate: publishDate,
      category: category,
      coverPhotoURL: coverPhotoURL,
      authorName: authorPicked.name,
    };
    axios
      .post(articleApi, article)
      .then(navigate("/"))
      .catch((error) => console.log(error), setInvalidArticle(true));

    event.preventDefault();
  };

  useEffect(() => {
    getAuthors();
  }, []);

  return (
    <div className="page">
      <h1 className="title">Create a new Article</h1>
      <Form onSubmit={handleSubmit} className="check-form">
        <Form.Label>Name</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter Article Title"
          onChange={(event) => {
            setTitle(event.target.value);
          }}
        />
        <Form.Label>Short Description</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter Short Description"
          onChange={(event) => {
            setShortDescription(event.target.value);
          }}
        />
        <Form.Label>Full Story</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter Full Story"
          onChange={(event) => {
            setFullStory(event.target.value);
          }}
        />
        <Form.Label>Publish Date</Form.Label>
        <Form.Control
          type="date"
          placeholder="Publish Date"
          onChange={(event) => {
            setPublishDate(event.target.value);
          }}
        />
        <Form.Label>Category</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter Category"
          onChange={(event) => {
            setCategory(event.target.value);
          }}
        />
        <Form.Label>Photo URL</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter Photo URL"
          onChange={(event) => {
            setCoverPhotoURL(event.target.value);
          }}
        />
        <Form.Label>Author Name</Form.Label>
        <Form.Select
          aria-label="Default select example"
          onChange={(event) => setAuthor(event.target.value)}
        >
          <Form.Check
            type="switch"
            id="custom-switch"
            label="Check this switch"
          />
          <option>Open this select menu </option>
          {authorList?.map((author, key) => (
            <option key={key}> {author.name}</option>
          ))}
        </Form.Select>
        {invalidArticle && (
          <div class="text-danger">Please fill in all fields</div>
        )}
        <br />
        <Button type="submit">
          Create
        </Button>
      </Form>
    </div>
  );
};

export default CreateArticle;
