import { Container, Nav, Navbar, Button } from "react-bootstrap";
import { Link, useNavigate, useLocation } from "react-router-dom";

const MyNav = function () {
  const navigate = useNavigate();
  const location = useLocation();
  const token = localStorage.getItem("token");

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };
  return (
    <Navbar bg="dark" variant="dark" expand="lg" className="shadow">
      <Container>
        <Navbar.Brand as={Link} to="/">
          Epic Energy
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            {/* Mostra i link solo se c'è il token corretto */}
            {token && (
              <>
                <Nav.Link as={Link} to="/" active={location.pathname === "/"}>
                  Dashboard
                </Nav.Link>
                {/* Aggiungere altri link come Fatture o Comuni */}
              </>
            )}
          </Nav>
          <Nav>
            {token ? (
              <Button variant="outline-danger" onClick={handleLogout}>
                Logout <i className="bi bi-box-arrow-right"></i>
              </Button>
            ) : (
              location.pathname !== "/login" && (
                <Link to="/login" className="btn btn-outline-info">
                  Accedi
                </Link>
              )
            )}
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default MyNav;
