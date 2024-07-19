import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import axios from "axios";
import api from "../../services/api";
import { jwtDecode } from "jwt-decode";

export const postLogin = createAsyncThunk("auth/login", async (credentials) => {    
    const response = await api.post(`/auth/login`, credentials);
    const token = await response.data.data.token

    return token
})

export const postRegisterUser = createAsyncThunk("auth/registerUser", async(credentials) => {
    const response = await api.post(`/auth/register/user`, credentials);
    const status = await response.status;
    return status
})

export const postRegisterAdmin = createAsyncThunk("auth/registerAdmin", async(credentials) => {
    const response = await api.post(`/auth/register/admin`, credentials);
    const status = await response.status;
    return status
})

const authSlice = createSlice({
    name: "auth",
    initialState: {      
        
        token: localStorage.getItem('token') || null,       
        login: false,
        registerSuccess: false
    },
    reducers: {
        permutRegisteSucces: (state, action) => {
            state.registerSuccess = !state.registerSuccess
        },
        logout: (state, action) => {
            state.login = false;
            state.token = null;
            localStorage.removeItem("token");
            localStorage.removeItem('user');
        },
        logged: (state, action) => {
            state.login = true;
        }        
    },
    extraReducers: (builder) => {
        builder.addCase(postLogin.fulfilled, (state, actions) => {
            localStorage.setItem('token', actions.payload);
            state.token = actions.payload;
            console.log(actions.payload);
            localStorage.setItem('user', JSON.stringify(jwtDecode(localStorage.getItem('token'))));
            state.login = true; 
            
        });
        builder.addCase(postRegisterUser.fulfilled, (state, actions) => {
            console.log(actions.payload);
            if(actions.payload === 200){
                state.registerSuccess = true
            }            
        });
        builder.addCase(postRegisterAdmin.fulfilled, (state, actions) => {
            console.log(actions.payload);
            if(actions.payload === 200){
                state.registerSuccess = true
            }            
        });
    }
});

 export const { permutRegisteSucces, logout, logged} = authSlice.actions;
export default authSlice.reducer