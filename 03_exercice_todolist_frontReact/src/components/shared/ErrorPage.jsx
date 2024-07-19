import React from 'react';
import {Link, useRouteError } from 'react-router-dom'

const ErrorPage = () => {
    const error = useRouteError()

    return (
        <>
            <h1>Error: {error.status}  </h1>
            <p> {error.data} </p>
            <Link to={'/'}>Si t'es perdu c'est par ici</Link>
        </>
    );
}

export default ErrorPage;
