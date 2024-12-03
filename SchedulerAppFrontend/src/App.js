import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import HeaderComponent from "./Header/HeaderComponent";
import HomePageComponent from "./Home/Home";
import RegisterComponent from "./Register/RegisterComponent";
import LoginComponent from "./Login/LoginComponent";

function App() {
  return (
    <div>
      <BrowserRouter>
        <HeaderComponent />
        <div className="container">
          <Routes>
            <Route path="/" element={<HomePageComponent />}></Route>
            <Route path="/register" element={<RegisterComponent />}></Route>
            <Route path="/login" element={<LoginComponent />}></Route>
          </Routes>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;
