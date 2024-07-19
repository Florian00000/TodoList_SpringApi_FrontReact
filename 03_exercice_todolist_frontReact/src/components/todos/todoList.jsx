import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { fetchAllTodos } from './todoSlice';

const TodoList = () => {

    const dispatch = useDispatch();


    useEffect(() => {
        dispatch(fetchAllTodos())
    }, [])

    return (
        <p>
            test
        </p>
    );
}

export default TodoList;
