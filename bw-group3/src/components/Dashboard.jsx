import { useState, useEffect } from "react";
import { Container, Table, Spinner, Alert, Form, Row, Col, Button, Card, InputGroup, Badge } from "react-bootstrap";

const Dashboard = function () {
  const [clienti, setClienti] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchParams, setSearchParams] = useState({
    nome: "",
    fatturatoMin: "",
    fatturatoMax: "",
    sortBy: "ragioneSociale",
  });

  const fetchClienti = function () {
    setLoading(true);
    const token = localStorage.getItem("token");

    let url = `http://localhost:3001/clienti?sortBy=${searchParams.sortBy}`;
    if (searchParams.nome) url += `&nome=${searchParams.nome}`;
    if (searchParams.fatturatoMin) url += `&fatturatoMin=${searchParams.fatturatoMin}`;
    if (searchParams.fatturatoMax) url += `&fatturatoMax=${searchParams.fatturatoMax}`;
    if (searchParams.dataInserimento) url += `&dataInserimento=${searchParams.dataInserimento}`;
    if (searchParams.dataUltimoContatto) url += `&dataUltimoContatto=${searchParams.dataUltimoContatto}`;

    fetch(url, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => {
        if (res.ok) return res.json();
        throw new Error("Errore nel caricamento dati.");
      })
      .then((data) => {
        setClienti(data.content || data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  };

  useEffect(() => {
    const token = localStorage.getItem("token");

    fetch("http://localhost:3001/clienti", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => {
        if (res.ok) {
          return res.json();
        } else {
          throw new Error("Impossibile caricare i clienti. Sei loggato?");
        }
      })
      .then((data) => {
        setClienti(data.content || data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  return (
    <Container className="mt-5">
      <h2 className="mb-4">Dashboard Epic Energy</h2>

      {/* --- SEZIONE FILTRI BOOTSTRAP --- */}
      <Card className="p-3 mb-4 shadow-sm bg-light">
        <Form>
          <Row className="g-3">
            <Col md={4}>
              <Form.Label className="fw-bold">Ragione Sociale</Form.Label>
              <Form.Control placeholder="Cerca nome..." onChange={(e) => setSearchParams({ ...searchParams, nome: e.target.value })} />
            </Col>
            <Col md={2}>
              <Form.Label className="fw-bold">Fatturato Min</Form.Label>
              <InputGroup>
                <InputGroup.Text>€</InputGroup.Text>
                <Form.Control type="number" onChange={(e) => setSearchParams({ ...searchParams, fatturatoMin: e.target.value })} />
              </InputGroup>
            </Col>
            <Col md={2}>
              <Form.Label className="fw-bold">Fatturato Max</Form.Label>
              <InputGroup>
                <InputGroup.Text>€</InputGroup.Text>
                <Form.Control type="number" onChange={(e) => setSearchParams({ ...searchParams, fatturatoMax: e.target.value })} />
              </InputGroup>
            </Col>
            <Col md={4}>
              <Form.Label className="fw-bold">Ordina per</Form.Label>
              <Form.Select onChange={(e) => setSearchParams({ ...searchParams, sortBy: e.target.value })}>
                <option value="ragioneSociale">Ragione Sociale</option>
                <option value="fatturatoAnnuale">Fatturato</option>
                <option value="dataInserimento">Data Inserimento</option>
                <option value="provinciaSedeLegale">Provincia</option>
              </Form.Select>
            </Col>

            {/* Filtri Data */}
            <Col md={4}>
              <Form.Label className="fw-bold">Data Inserimento</Form.Label>
              <Form.Control type="date" onChange={(e) => setSearchParams({ ...searchParams, dataInserimento: e.target.value })} />
            </Col>
            <Col md={4}>
              <Form.Label className="fw-bold">Ultimo Contatto</Form.Label>
              <Form.Control type="date" onChange={(e) => setSearchParams({ ...searchParams, dataUltimoContatto: e.target.value })} />
            </Col>
            <Col md={4} className="d-flex align-items-end">
              <Button variant="primary" className="w-100" onClick={fetchClienti}>
                <i className="bi bi-search"></i> Applica Filtri
              </Button>
            </Col>
          </Row>
        </Form>
      </Card>

      {/* --- TABELLA DATI --- */}
      {loading && <Spinner animation="border" className="d-block mx-auto" />}
      {error && <Alert variant="danger">{error}</Alert>}

      {!loading && !error && (
        <Table striped bordered hover responsive className="shadow-sm">
          <thead className="table-dark">
            <tr>
              <th>Ragione Sociale</th>
              <th>Tipo</th>
              <th>Partita IVA</th>
              <th>Email Aziendale</th>
              <th>PEC</th>
              <th>Contatto (Nome/Cognome)</th>
              <th>Fatturato</th>
              <th>Data Inserimento</th>
            </tr>
          </thead>
          <tbody>
            {clienti.map((c) => (
              <tr key={c.idCliente}>
                <td>
                  <img src={c.logoAziendale} alt="logo" style={{ width: "30px", marginRight: "10px" }} />
                  {c.ragioneSociale}
                </td>
                <td>
                  <Badge bg="secondary">{c.tipoCliente}</Badge>
                </td>
                <td>{c.partitaIva}</td>
                <td>{c.email}</td>
                <td>{c.pec}</td>
                <td>
                  {c.nomeContatto} {c.cognomeContatto}
                </td>
                <td>{c.fatturatoAnnuale?.toLocaleString()} €</td>
                <td>{c.dataInserimento}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      )}
    </Container>
  );
};

export default Dashboard;
