import { api_start } from "./actions.js";
import { createSlice } from "@reduxjs/toolkit";

export const slice = createSlice({
  name: "User",
  initialState: {
    Success: "",
    Error: "",
    LoggedIn: false,
  },
  reducers: {
    reset_error:(User, action) => {
        User.Error= ""
  
      },
      user_created:(User,action) => {
        window.location.href = "/"
        
      },
      loggin_success:(User, action)=>{
        User.LoggedIn = true

      },
  
      set_error: (User, action) => {
        console.log(action.payload)
        User.Error = action.payload
      },
  },
});

export const {loggin_success, user_created,logged_in, reset_error,set_error } = slice.actions

export default slice.reducer

export const create_user = (data) => api_start({
    url: "http://localhost:8080/api/user",
    method: "POST",
    data: data,
    onSuccess: user_created.type,
    onError: set_error.type
  })

export const login_user = (data) => api_start({
  url: "http://localhost:8080/api/login",
  method: "POST",
  data: data,
  onSuccess: loggin_success.type,
  onError: set_error.type
})
