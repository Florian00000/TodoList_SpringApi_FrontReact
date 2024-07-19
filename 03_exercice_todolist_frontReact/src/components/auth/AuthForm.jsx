import React, { useEffect, useRef } from "react";
import { Navigate, useNavigate, useSearchParams } from "react-router-dom";
import { Link } from "react-router-dom";
import axios from 'axios';
import { useDispatch, useSelector } from "react-redux";
import { permutRegisteSucces, postLogin, postRegisterAdmin, postRegisterUser } from "./authSlice";

const AuthForm = () => {
  const [searchParams] = useSearchParams();
  const mode = searchParams.get("mode") ?? "login";

  const emailRef = useRef();
  const passwordRef = useRef();
  const fistnameRef = useRef();
  const lastnameRef = useRef();
  const roleRef = useRef();


  const dispacth = useDispatch();
  const navigate = useNavigate();

  // const mode = useSelector(state => state.auth.authMode);
  const token = useSelector(state => state.auth.token)
  const registerSuccess = useSelector(state => state.auth.registerSuccess)

  //Fonction du formulaire
  const authSubmit = (e) => {
    e.preventDefault();

    if (mode === "login") {
      const credentials = {
        email: emailRef.current.value,
        password: passwordRef.current.value,
      }

      dispacth(postLogin(credentials))
    }else{
      const credentials = {
        firstname: fistnameRef.current.value,
        lastname: lastnameRef.current.value,
        email: emailRef.current.value,
        password: passwordRef.current.value,
      }
      console.log(credentials);
      if (roleRef.current.value === "ROLE_USER") {
        dispacth(postRegisterUser(credentials))          
      }else{
        dispacth(postRegisterAdmin(credentials))
      }
    }


  };


  useEffect(() => {
    console.log(" token: " + token);
    if (token) {
      
      navigate("/")
    }
  }, [token])

  useEffect(() => {
    if (registerSuccess) {
      navigate("/auth?mode=login")
      dispacth(permutRegisteSucces())
    }
  }, [registerSuccess])



  return (
    <main className="container">
      {mode === "signup" ? <h3 className="mt-4">S'inscire</h3> : <h3 className="mt-4">Connexion</h3>}
      <form onSubmit={authSubmit} >
        {mode === "signup" && (
          <>
            <div>
              <label htmlFor="firstname" className="form-label mt-4">Prénom:</label>
              <input type="text" required ref={fistnameRef} className="form-control" />
            </div>
            <div>
              <label htmlFor="lasttname" className="form-label mt-4">Nom:</label>
              <input type="text" required ref={lastnameRef} className="form-control" />
            </div>
          </>

        )}

        <div>
          <label htmlFor="email" className="form-label mt-4">Email:</label>
          <input type="email" required ref={emailRef} className="form-control" />
        </div>
        <div>
          <label htmlFor="password" className="form-label mt-4">Mot de passe:</label>
          <input type="password" min={6} required ref={passwordRef} className="form-control" />
        </div>
        {mode === "signup" && (
          <div>
            <label htmlFor="role" className="form-label mt-4">Role: </label>
            <select className="form-select" id="role" ref={roleRef}>
              <option value={"ROLE_USER"}>utilisateur</option>
              <option value={"ROLE_ADMIN"}>admin</option>
            </select>
          </div>
        )}

        <div className="d-flex">
          <button type="submit" className="btn btn-success mt-4 ms-auto"> {mode === "signup" ? "S'inscire" : "Connexion"} </button>

        </div>


      </form>
      {mode === "login" && <Link to={"/auth?mode=signup"}>Pas encore de compte?</Link>}
      {mode === "signup" && <Link to={"/auth?mode=login"}>Déjà un compte?</Link>}
    </main>
  );
};

export default AuthForm;
