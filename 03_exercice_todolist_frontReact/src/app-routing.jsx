import { createBrowserRouter } from "react-router-dom";
import App from './App'
import AuthForm from "./components/auth/AuthForm";
import ErrorPage from "./components/shared/ErrorPage";
import ProtectedRoute from "./components/shared/ProtectedRoute";
import TodoList from "./components/todos/todoList";

const router = createBrowserRouter([
    {
        path: "/",
        element: <App/>,
        errorElement: <ErrorPage/>,
        children: [
            {
                path: "/",
                element: <ProtectedRoute> <TodoList/></ProtectedRoute> 
            },            
            {
                path: "/auth",
                element: <AuthForm/>
            }
            
        ]
    }
])

export default router;