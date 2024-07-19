import React from 'react';
import { useSelector } from 'react-redux';
import { Navigate, useNavigate } from 'react-router-dom';

const ProtectedRoute = (props) => {
    const navigate = useNavigate();
    const token = useSelector((state) => state.auth.token);

    if (token) {
        return (
            <>
               {props.children} 
            </>
        );
        
    } else {
        return <Navigate to={"/auth?mode=login"}/>
    }
}

export default ProtectedRoute;
