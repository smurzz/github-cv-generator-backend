import React from 'react';
import { Card, ListGroup, Badge, Button } from 'react-bootstrap';
import { useLocation, useNavigate } from "react-router-dom";
import './App.css';

const Resume = () => {
    const location = useLocation();
    const resume = location.state;
    const navigate = useNavigate();
   
    return (
        (<div>
            <Button variant="outline-secondary mb-4" onClick={() => navigate("/")}>Back</Button>
            <main role="main" className="m-1">
                <style>{'body{height: 100%; box-shadow: 5px 10px #888888; padding: 10rem 15rem 0rem 15rem; }'}</style>
                <style>{'main{box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px; padding: 2rem 4rem 4rem 4rem;}'}</style>
                <h1 className="cover-heading ">GITHUB RESUME</h1>
                <h2>{resume.nameOwner}</h2>
                <p className="font-weight-bold mr-4" >GitHub usename: {resume.loginOwner}</p>
                <p className="font-weight-bold mr-4" >Website: <a href={resume.blogOfOwner}>{resume.blogOfOwner}</a></p>
                <p className="lead"> On GitHub as an early adopter since {new Date(resume.dateOfCreation).getFullYear()}, {resume.nameOwner} is a developer with {resume.numOfRepos} public repositories and {resume.followers} followers. </p>
                <h4>Languages:</h4>

                <ListGroup as="ol" numbered>
                    {resume.languages && Object.entries(resume.languages).map((entity) => {
                        return <ListGroup.Item
                            as="li"
                            className="d-flex justify-content-between align-items-start" key={entity[0]}>
                            <div className="ms-2 me-auto">
                                <div className="fw-bold">{entity[0]}</div>
                            </div>
                            <Badge bg="primary" pill>
                                ({Math.round(entity[1])}%)
                            </Badge>
                        </ListGroup.Item>
                    })}
                </ListGroup>

                <h4>Repositories: </h4>
                {(resume.repoDetails && Object.values(resume.repoDetails).map(repo => {
                    return (
                        <Card key={repo.repoId}>
                            <Card.Header>{repo.repoName}</Card.Header>
                            <ListGroup variant="flush">
                                <ListGroup.Item>{repo.repoDescription}</ListGroup.Item>
                                <ListGroup.Item>
                                    <Card.Link href={repo.repoSvnUrl}>{repo.repoSvnUrl}</Card.Link>
                                </ListGroup.Item>
                                <ListGroup.Item>{repo.repoLanguage}</ListGroup.Item>
                            </ListGroup>
                        </Card>);
                }))}
            </main>
        </div>)
    )
}

export default Resume;