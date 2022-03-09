
// Be sure to install:
// npm i react-router-dom @mui/material @mui/icons-material @emotion/react @emotion/styled
// idengifier beeing either email or username for login

import React, { useEffect, useState } from "react";

import Fab from "@mui/material/Fab";
import ArrowForwardIcon from "@mui/icons-material/ArrowForward";
import {Link} from "react-router-dom"
import { useDispatch, useSelector } from "react-redux";
import {login_user} from "../../store/slice"
import "./style.css";
export default function Login() {
  const dispatch = useDispatch()


  const [formstate, setFormState] = useState({
    email: "",
    password: "",
  });

  const handleInput = (event) =>{
    let name = event.target.name
    let value = event.target.value
    setFormState({
      ...formstate, [name]:value
    })

  }

  const handleSubmit = (event) => {
    event.preventDefault()
    dispatch(login_user(formstate))
  }
  return (
    <main className="login-create-container">
      <section className="top-wave"></section>
      <section className="center-contain">
        <div>
          <h1 className="center-align entry-title">Login</h1>
          <div className="entry-form-wraper">
            <form onSubmit={handleSubmit} className="entry-form">
              <div className="form-content-wraper align">
                <div className="entry-input-contain">
                  <input
                    placeholder="email"
                    onChange={handleInput}
                    id="top-input"
                    style={{ display: "block" }}
                    name="email"
                    value={formstate.email}
                  ></input>
                  <input
                    type="password"
                    placeholder="Password"
                    onChange={handleInput}
                    id="bottom-input"
                    style={{ display: "block" }}
                    name="password"
                    value={formstate.password}
                  ></input>
                </div>
                <Fab
                  type="submit"
                  id="login-botton"
                  color="primary"
                  aria-label="edit"
                >
                  <ArrowForwardIcon />
                </Fab>
              </div>
            </form>
          </div>
        </div>
        <Link id="register-link" to="/register">Register</Link>
      </section>
      <section className="bottom-wave"></section>
    </main>
  );
}
