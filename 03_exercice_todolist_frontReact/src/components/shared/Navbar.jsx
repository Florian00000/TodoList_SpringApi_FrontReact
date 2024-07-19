import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from "react-redux"
import { Link, useNavigate } from "react-router-dom"
import { logged, logout } from '../auth/authSlice';


const Navbar = () => {
  const [user, setUser] = useState();
  const login = useSelector(state => state.auth.login)
  const dispacth = useDispatch();

  const navigate = useNavigate();

  useEffect(() => {
    console.log(user);
    console.log("login: "+login);
    if (!user && localStorage.getItem("user")) {
      setUser(JSON.parse(localStorage.getItem('user')))
      dispacth(logged())
    }
    if (!login) {
      setUser(null)
    }
  }, [login])

  const logoutButton = () => {    
    dispacth(logout());
    console.log("ici");
    navigate("/auth")
  }

  return (
    // <nav className='bg-dark' id='nav-header'>
    //     <Link to={"/"} id='logoHeader'><span><b> Navbar</b></span></Link> 
    //     {/* <div>
    //         {user &&   <button className='btn btn-outline-light me-2' onClick={() => navigate("/album/add?mode=add")}>Ajouter</button> } 

    //     {!user && <Link to={'/auth/login?mode=login'}> <button className='btn btn-outline-primary ms-2'>Connexion</button> </Link>}

    //     {user && <button onClick={() => dispacth(removeUser())} className='btn btn-outline-primary ms-2' >Déconnexion</button>}
    //     </div> */}
    // </nav>


    <nav className="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
      <div className="container-fluid">
        <Link className="navbar-brand" to={"/"}>TodoList</Link>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarColor01">
          <ul className="navbar-nav me-auto">
            <li className="nav-item">
              {/* <a className="nav-link active" href="#">Home
                  <span className="visually-hidden">(current)</span>
                </a> */}
            </li>
          </ul>
          {user && (
            <>
              <p className='text-light mt-3 me-3'>{user.sub}</p>
              <button onClick={() => logoutButton()} className='btn btn-outline-light'>Déconnexion</button>

            </>
          )}

        </div>
      </div>
    </nav>



  );
}

export default Navbar;
