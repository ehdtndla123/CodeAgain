import axios from "axios";
import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useLocation } from "react-router-dom";
import Header3 from "../../Common/Header3";
import { register } from "../../Common/Service/HttpServiceManger";

const USERNAME_MIN_LENGTH = 2;
const VERIFIED_IMG_PATH = "../img/check.png";
const ERROR_IMG_PATH = "../img/noneCheck.png";

const ResisterPage = () => {
  const navigate = useNavigate();

  const loginPage = (e) => {
    console.log(e);
    navigate("/login");
  };

  const mainPage = (e) => {
    console.log(e);
    navigate("/");
  };
  //  console.log(useLocation());
  const [checkResult, setCheckResult] = useState(useLocation().state.Check);
  const [emailValid, setEmailValid] = useState(useLocation().state.EmailValid); //이메일 유효성
  const [usernameValid, setUsernameValid] = useState(false); //닉네임 확인 유효성
  const [pwdValid, setPwdValid] = useState(false); //비번 유효성
  const [pwdCheckValid, setPwdCheckValid] = useState(false); //비번확인 유효성
  const [userInfo, setUserInfo] = useState({
    pw: "",
    pwCheck: "",
    username: "",
    email: useLocation().state.Email,
  }); //유저정보 저장 필드
  const { pw, pwCheck, username, email } = userInfo;

  const [allValid, setAllVaild] = useState(false); //전체 유효성

  //username필드 변경시 유효성 검증 length >= USERNAME_MIN_LENGTH
  const onUsernameChange = (e) => {
    const value = e.target.value;
    setUserInfo({
      ...userInfo,
      username: value,
    });
    setUsernameValid(value.length >= USERNAME_MIN_LENGTH);
  };

  //pwd/ pwdcheck 변경시 유효성 검증
  const onPwdChange = (e) => {
    const pwvalue = e.target.value;
    let regPass = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/;
    if (regPass.test(pwvalue)) {
      setPwdValid(true);
    } else {
      setPwdValid(false);
    }
    setUserInfo({
      ...userInfo,
      pw: pwvalue,
    });
    document.getElementById("disabledCheck").value = null;
    setPwdCheckValid(false);
  };

  const onPwdCheckChange = (e) => {
    const value = e.target.value;
    setUserInfo({
      ...userInfo,
      pwCheck: value,
    });
    if (value != null) {
      setPwdCheckValid(userInfo.pw == value);
    }
  };
  //email 유효성 검증 (중복 email 체크의 경우 여기서 하기 어려워서 회원가입 버튼 누를 때 axios->post 보낸 뒤  response 에 대한 error handling 으로 진행)
  const checkEmailValid = (e) => {
    const emailValue = e.target.value;

    setUserInfo({
      ...userInfo,
      email: emailValue,
    });
    var regExp = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/; //이메일 검증 bnf
    if (regExp.test(emailValue)) {
      setEmailValid(true);
    } else {
      setEmailValid(false);
    }
    axios
      .post(
        "http://13.209.48.23/api/check/email",
        { email: emailValue },
        {
          headers: {
            "Content-Type": `application/json`,
          },
        }
      )
      .then((res) => {
        console.log(res);
        if (res.data.data == true) {
          setCheckResult(true);
        } else {
          setCheckResult(false);
        }
      });
  };

  //전체 검증 로직(모든 필드의 valid 체크)

  useEffect(() => {
    verify();
  }, [emailValid, usernameValid, pwdValid, pwdCheckValid]);
  const verify = () => {
    if (emailValid && pwdValid && pwdCheckValid && usernameValid) {
      setAllVaild(true);
    } else {
      setAllVaild(false);
    }
  };

  async function registerBtnClick() {
    axios
      .post(
        "http://13.209.48.23/api/signup",
        { email: email, name: username, password: pw },
        {
          headers: {
            "Content-Type": `application/json`,
          },
        }
      )
      .then((res) => {
        console.log(res);
        if (res.data.success === true) {
          navigate("/login");
        }
      });
  }
  return (
    <div className="ResisterPage">
      <Header3 />

      <div id="main">
        <p id="title">회원가입</p>
        <p class="subTitle">이메일</p>
        <input
          type="text"
          defaultValue={userInfo.email}
          //  placeholder="이메일"
          id="email"
          onChange={checkEmailValid}
        />
        <img
          class="check"
          src={checkResult && emailValid ? VERIFIED_IMG_PATH : ERROR_IMG_PATH}
          width="30px"
        />
        <p id="error">
          {checkResult && emailValid
            ? ""
            : !emailValid && checkResult
            ? "잘못된 이메일 형식입니다"
            : !checkResult && emailValid
            ? "가입된 이메일 입니다"
            : ""}
        </p>

        <p class="subTitle">비밀번호</p>
        <input
          id="pw"
          name="pw"
          onChange={onPwdChange}
          type="password"
          // placeholder="비밀번호"
        />
        <img
          class="check"
          src={pwdValid ? VERIFIED_IMG_PATH : ERROR_IMG_PATH}
          width="30px"
        />
        <p id="error">
          {pwdValid ? "" : "영문, 숫자 조합으로 8-20자리 입력해주세요"}
        </p>
        <p class="subTitle">비밀번호 확인</p>
        <input
          name="pwCheck"
          onChange={onPwdCheckChange}
          type="password"
          id={pwdValid ? "pwCheck" : "disabledCheck"}
          readOnly={pwdValid ? false : true}
          // placeholder="비밀번호 확인"
        />
        <img
          id={pwdValid ? "" : "darkCheck"}
          class="check"
          src={pwdCheckValid && pwdValid ? VERIFIED_IMG_PATH : ERROR_IMG_PATH}
          width="30px"
        />
        <br />
        {/* <span name="check">일치여부 : {pwdValid} </span>
        <br />
        <span>pw : {pw} </span>
        <br />
        <span>pwCheck : {pwCheck} </span> */}
        <p class="subTitle"> 사용자 이름</p>
        <input
          type="text"
          placeholder=""
          id="username"
          onChange={onUsernameChange}
        />
        <img
          class="check"
          src={
            userInfo.username.length >= USERNAME_MIN_LENGTH
              ? VERIFIED_IMG_PATH
              : ERROR_IMG_PATH
          }
          width="30px"
        />
        <p id="error">{usernameValid ? "" : "2자 이상 입력해주세요"}</p>
        <button
          className="join"
          id={"btn" + (allValid ? "Active" : "Disabled")}
          // onClick={loginPage}
          onClick={registerBtnClick}
        >
          가입하기
        </button>
          {/*
        <button
          id="google"
          // onClick={clickGoogle}
        >
          <img id="googleIcon" src="../img/google.png" width="30px"></img>
          <p id="googleP">구글 계정으로 가입하기</p>
        </button>
        */}
      </div>
      <style jsx>{`
        #darkCheck {
          opacity: 10%;
        }
        body {
          margin-left: 0px;
        }
        #error {
          background: none;
          font-size: 13px;
          margin-top: 0px;
          margin-left: 55px;
          color: #ffb400;
        }
        .ResisterPage {
          background-image: url("../../img/background.png");
          background-size: 110%;
          background-repeat: no-repeat;
          background-position: center top;
          padding-bottom: 0px;
        }

        #google {
          display: block;
          background-color: transparent;
          color: white;
          height: 40px;
          width: 80%;
          line-height: 40px;
          text-align: center;
          margin: 50px auto 0px auto;
          border: 1px solid #808080;
        }

        #googleIcon {
          background: none;
          float: left;
          margin-top: 2px;
        }

        #googleP {
          display: inline-block;
          background: none;
          margin: 0px;
        }

        input {
          outline: none;
          background-color: #343a40;
        }
        #disabledCheck {
          outline: none;
          background-color: #343a40;
          opacity: 30%;
        }
        * {
        font-family: 'Pretendard-Regular';
          background-color: #343a40;
        }

        #logo {
          cursor: pointer;
          margin-left: 5px;
          background: none;
          position: relative;
          z-index: 2;
          display: inline-block;
        }

        #circle {
          margin-top: 10px;
          display: block;
          background-color: black;
          float: right;
          margin-right: 5px;
          width: 10px;
          height: 10px;
          border-radius: 50%;
        }

        header {
          padding: 10px;
          background: none;
        }

        #theme {
          display: block;
          float: right;
          color: #d9d9d9;
          font-size: 10px;
          margin-right: 10px;
        }

        #login {
          margin-top: 5px;
          display: block;
          float: right;
          right: 0px;
          display: block;
          color: #d9d9d9;
          font-size: 20px;
          cursor: pointer;
          margin-left: 10px;
        }

        #main {
          position: relative;
          width: 550px;

          display: block;
          margin: 0px auto 60px auto;

          height: auto;
          padding-bottom: 30px;
          background-color: #212529;
          z-index: 100;
          border-radius: 10px;
        }

        #p1 {
          color: #ffffff;
          font-size: 30px;
          line-height: 10px;
        }

        #p2 {
          color: #fff;
          font-size: 45px;
          line-height: 0px;
          font-weight: 500;
        }

        #p3 {
          text-decoration: underline;
          text-underline-position: under;
          margin-top: 100px;
          color: #d9d9d9;
          font-size: 15px;
        }

        #p4 {
          background: none;
          color: #d9d9d9;
          font-size: 15px;
          line-height: 25px;
          position: relative;
          z-index: 2;
        }

        input {
          color: #d9d9d9;
          position: relative;
          z-index: 2;
          margin-bottom: 10px;
          margin-left: 50px;
          background-color: #343a40;
          border: none;
          width: 80%;
          height: 40px;
          border-radius: 2px;
          padding-left: 10px;
        }

        #btnActive {
          border: none;
          color: #d9d9d9;
          border-radius: 2px;
          display: block;
          text-align: center;
          background-color: #808080;
          margin: 40px auto auto auto;
          height: 40px;
          width: 40%;
          font-size: 16px;
          cursor: pointer;
        }

        #btnDisabled {
          border: none;
          color: #d9d9d9;
          border-radius: 2px;
          display: block;
          text-align: center;
          background-color: #808080;
          margin: 40px auto auto auto;
          height: 40px;
          width: 40%;
          font-size: 16px;
          cursor: pointer;
          opacity: 10%;
        }

        .back {
          font-family: "Rubik", sans-serif;
          font-weight: 900;
          position: absolute;
          color: #212529;
          font-size: 100px;
          z-index: 0;
          opacity: 60%;
        }

        #b1 {
          left: 50px;
          top: -45px;
          line-height: 0px;
          transform: rotate(-27deg);
        }

        #b2 {
          left: 350px;
          top: 230px;
          line-height: 0px;
          transform: rotate(20deg);
        }

        #b3 {
          right: 350px;
          top: -60px;
          line-height: 0px;
          transform: rotate(-27deg);
        }

        #b4 {
          right: -20px;
          top: 100px;
          line-height: 0px;
          transform: rotate(-10deg);
        }

        #b5 {
          left: -20px;
          top: 350px;
          line-height: 0px;
          transform: rotate(-20deg);
        }

        #b6 {
          left: 250px;
          top: 500px;
          line-height: 0px;
          transform: rotate(10deg);
        }

        #b7 {
          right: 330px;
          top: 370px;
          line-height: 0px;
          transform: rotate(-30deg);
        }

        #b8 {
          right: -20px;
          top: 500px;
          line-height: 0px;
          transform: rotate(30deg);
        }

        .subTitle {
          color: white;
          background: none;
          z-index: 2;
          margin-left: 50px;
        }

        #title {
          text-align: center;
          color: white;
          background: none;
          padding-top: 30px;
          font-size: 20px;
          padding-bottom: 20px;
        }

        #background {
          z-index: -1;
        }

        .check {
          position: absolute;
          background: none;
          line-height: 40px;

          margin-left: -40px;
          margin-top: 5px;
          z-index: 2;
        }
      `}</style>
    </div>
  );
};

export default ResisterPage;
