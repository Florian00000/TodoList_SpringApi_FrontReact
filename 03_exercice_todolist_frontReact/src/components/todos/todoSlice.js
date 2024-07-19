import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import api from "../../services/api";
import { authHeader } from "../../services/auth-header";

export const fetchAllTodos = createAsyncThunk("todos/getAll", async() => {
    const response = await api.get(`/user/todos`, {headers: authHeader()} );
    const data = await response.data

    return data
})

const todoSlice = createSlice({
    name: "todo",
    initialState: {
        todos: null
    },
    reducers: {

    },
    extraReducers: (builder) => {
        builder.addCase(fetchAllTodos.fulfilled, (state, actions) => {
            state.todos = actions.payload;
            console.log(actions.payload);           
        });
    }
})

export default todoSlice.reducer;