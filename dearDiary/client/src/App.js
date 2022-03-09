import logo from './logo.svg';

import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import Login from "./pages/Login"
import CreateAccount from "./pages/CreateAccount"
import  store  from './store/configur-store';
import {Provider} from "react-redux"

import './App.css';

function App() {
  return (
    <Provider store={store}>
    <Router>
      <Routes>
        <Route path="/" element={<Login/>}/>
        <Route path="/register" element={<CreateAccount/>}/>
      </Routes>
    </Router>
    </Provider>
    
  );
}

export default App;
