import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import { Link, NavLink } from "react-router-dom";
import "../styles/Header.css";

const Header = () => {
  return (
    <Navbar bg="primary" variant="dark" className="nav">
      <>
        <Container>
          <Navbar.Brand>
            <Link to="/"><img
              src="https://upload.wikimedia.org/wikipedia/en/thumb/c/c5/News-com-au_logo.svg/1200px-News-com-au_logo.svg.png"
              alt="FDM Logo"
              className="fdm-logo"
            /></Link>
          </Navbar.Brand>
          <Nav className="me-auto">
            <Link to="/all-articles" className="nav-link">
              Home
            </Link>
            <Link to="/trending-articles" className="nav-link">
              Trending
            </Link>
            <Link to="/sports-articles"className="nav-link">
              Sport
            </Link>
            <Link to="/science-articles" className="nav-link">
              Science
            </Link>
            <Link to="/economy-articles" className="nav-link">
              Economy
            </Link>
            <Link to="/environment-articles" className="nav-link">
              Environment
            </Link>
            <Link to="/login" className="nav-link">
              Login
            </Link>
          </Nav>
        </Container>
      </>
    </Navbar>
  );
};

export default Header;
