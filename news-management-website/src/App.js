import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle";
import { Route, Routes } from "react-router-dom";
import "./App.css";
import Header from "./components/Header";
import ListArticles from "./components/ListArticles";
import Footer from "./components/Footer";
import Login from "./components/Login";
import FullArticle from "./components/FullArticle";
import AddUser from "./components/AddUser";
import AddAuthor from "./components/AddAuthor";
import CreateArticle from "./components/CreateArticle";
import Authors from "./components/Authors";
import Users from "./components/Users";
import EditArticle from "./components/EditArticle";
import EditUser from "./components/EditUser";
import ListUser from "./components/Users";
import EditAuthor from "./components/EditAuthor";

function App() {
  return (
    <div className="App">
      <Header />
      <div className="wrapper">
        <Routes>
          <Route
            path="/"
            element={<ListArticles api="http://localhost:8088/api/v1/article" />}
          />
          <Route
            path="/all-articles"
            element={<ListArticles api="http://localhost:8088/api/v1/article" />}
          />
          <Route
            path="/sports-articles"
            element={
              <ListArticles api="http://localhost:8088/api/v1/article/cat/sports" />
            }
          />
          <Route
            path="/trending-articles"
            element={
              <ListArticles api="http://localhost:8088/api/v1/article/cat/trending" />
            }
          />
          <Route
            path="/economy-articles"
            element={
              <ListArticles api="http://localhost:8088/api/v1/article/cat/economy" />
            }
          />
          <Route
            path="/science-articles"
            element={
              <ListArticles api="http://localhost:8088/api/v1/article/cat/science" />
            }
          />
          <Route
            path="/environment-articles"
            element={
              <ListArticles api="http://localhost:8088/api/v1/article/cat/environment" />
            }
          />
          <Route path="/author/:id" element={<Authors />} />
          <Route path="/user/:id" element={<Users />} />
          <Route path="/login" element={<Login />} />
          <Route path="/fullStory/:id" element={<FullArticle />} />
          <Route path="/create-article" element={<CreateArticle />} />
          <Route path="/login/create-user" element={<AddUser />} />
          <Route path="/create-author" element={<AddAuthor />} />
          <Route path="/edit-article/:id/" element={<EditArticle />} />
          <Route path="/edit-user/:id/" element={<EditUser />} />
          <Route path="/edit-author/:id" element={<EditAuthor />} />
        </Routes>
      </div>
      <Footer />
    </div>
  );
}

export default App;
