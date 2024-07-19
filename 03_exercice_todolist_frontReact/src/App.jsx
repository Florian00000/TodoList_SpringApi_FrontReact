import { useState } from 'react'
import './App.css'
import Navbar from './components/shared/Navbar'
import {Outlet} from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import "bootswatch/dist/flatly/bootstrap.min.css"

function App() {
  

  return (
    <>
      <Navbar/>
      <main>
        <Outlet/>
      </main>
    </>
  )
}

export default App
