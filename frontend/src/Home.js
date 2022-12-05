import { useState } from 'react';
import { Button, Form, InputGroup, Alert } from 'react-bootstrap';
import { trackPromise } from 'react-promise-tracker';
import { useNavigate } from "react-router-dom";

function Home() {

  const [login, setLogin] = useState('');
  const [messageError, setMessErr] = useState('');
  const navigation = useNavigate();

  // action for the button "generate"
  const handleSubmit = async (e) => {
    e.preventDefault();
    getResumeByLogin();
    setLogin('');
  }

  // get an resume by login 
  function getResumeByLogin() {
    trackPromise(fetch(`http://localhost:8080/resume/${login}`))
      .then((response) => response.json())
      .then(data => {
        if (data.status === 404) {
          var messageError = (<Alert variant="danger">Error: GitHub account with the usename -- {login} -- is not found!</Alert>);
          setMessErr(messageError);
        } else {
          navigation("/resume/" + login, { state: data, replace: true });
        }
      });
  }

  return <main role="main" className="m-4 justify-items-center">
    <style>{'body{height: 100%; text-align: center; padding: 20% 20% 0rem 20%; }'}</style>
    <h1 className="cover-heading ">GitHub Resume Generator</h1>
    <p className="lead">Try to create your professional resume using your GitHub account. Enter your login and click "generate".</p>
    <InputGroup className="mb-3">
      <Form.Control
        placeholder="GitHub's login"
        aria-label="GitHub's login"
        aria-describedby="basic-addon2"
        size='sm'
        value={login}
        onChange={async (e) => { setLogin(e.target.value) }}
      />
      <Button variant="secondary" size='lg' onClick={handleSubmit}> Generate </Button>
    </InputGroup>
    {messageError}
  </main>;
}

export default Home;