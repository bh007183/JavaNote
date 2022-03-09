
  // Be sure to install:
// npm i react-router-dom @mui/material @mui/icons-material @emotion/react @emotion/styled
  import React, { useState } from "react";
import { Button, Box, Grid, TextField } from "@mui/material";
import {create_user} from "../../store/slice"
import {useDispatch, useSelector} from "react-redux"
import Error from "../../components/Error"
import "./style.css";


export default function Register() {
  const dispatch = useDispatch()

  const error = useSelector((state) => state.Slice.Error)
  

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
    event.preventDefault();
    dispatch(create_user(formstate))

  }
  return (
    <div className="register-container">
      {error.errors ? <Error message={error.errors[0]} /> : error.message ? <Error message={error.message} /> : <></>}
      
      <form onSubmit={handleSubmit} className="register-form">
        <div>
          <p>Welcome to Dear Diary, fill out the form below to register!</p>
        </div>
        <Box sx={{ flexGrow: 1 }}>
          <Grid className="grid-item" container spacing={2}>
            
            <Grid className="grid-item" item  md={9} >
              <TextField
              onChange={handleInput}
                size="small"
                variant="outlined"
                name="email"
                value={formstate.email}
                label="Email"
                required="true"
               
              ></TextField>
            </Grid>
            <Grid className="grid-item" item  md={9} >
              <TextField
              onChange={handleInput}
                size="small"
                variant="outlined"
                name="password"
                value={formstate.password}
                label="Password"
                type="password"
                required="true"
              
              ></TextField>
            </Grid>
            
          </Grid>
        </Box>
        <div className="form-submit-button-container">
        <Button  type="submit" variant="outlined">Register</Button>
        </div>
      </form>
    </div>
  );
}

  