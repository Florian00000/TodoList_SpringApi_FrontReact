import {configureStore} from "@reduxjs/toolkit"
import authSlice from "./components/auth/authSlice"
import todoSlice from "./components/todos/todoSlice"

export default configureStore({
    reducer: {
        auth: authSlice,  
        todo: todoSlice      
    }
})