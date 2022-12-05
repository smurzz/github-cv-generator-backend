import React from 'react';
import { usePromiseTracker } from "react-promise-tracker";
import ReactDOM from 'react-dom/client';
import * as Loader from 'react-loader-spinner';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';

const root = ReactDOM.createRoot(document.getElementById('root'));

const LoadingIndicator = props => {
  const { promiseInProgress } = usePromiseTracker();

  return (
    promiseInProgress &&
    <div
      style={{
        width: "100%",
        height: "100",
        display: "flex",
        justifyContent: "center",
        alignItems: "center"
      }}
    >
      <Loader.ThreeDots type="ThreeDots" color="#636363" height="100" width="100" />
    </div>
  );
}

root.render(
  <React.StrictMode>
    <App />
    <LoadingIndicator />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
