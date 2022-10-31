import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

// ResisterPage Header
export default function Header3() {
  const navigate = useNavigate();

  const mainPage = (e) => {
    console.log("LOGOUT COMPLETE");
    localStorage.removeItem("access_token");
    localStorage.removeItem("refresh_token");
    navigate("/");
  };
  const loginPage = (e) => {
    console.log("LOGIN COMPLETE");
    navigate("/login");
  };

  return (
    <header>
      <img onClick={mainPage} id="logo" src="../img/logo.png" width="150px" />
      <p onClick={loginPage} id="login">
        로그인
      </p>
      <p id="theme">Dark</p>
      <div id="circle"></div>
    </header>
  );
}
