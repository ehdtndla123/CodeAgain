import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Header2 from "../../Common/Header2";
import OTPInput from "otp-input-react";
import axios from "axios";

const StudyPage = () => {
  const navigate = useNavigate();

  const GroupAdd = () => {
    document.getElementById("main").style.display = "none";
    document.getElementById("find").style.display = "none";
    document.getElementById("create").style.display = "block";
  };
  const GroupFind = () => {
    document.getElementById("main").style.display = "none";
    document.getElementById("find").style.display = "block";
    document.getElementById("create").style.display = "none";
  };
  const accessToken = localStorage.getItem("access_token")
  const [inputName, setInputName] = useState("");
  const study = () => {
    document.getElementById("main").style.display = "none";
    document.getElementById("create").style.display = "none";
    document.getElementById("find").style.display = "none";
    axios
      .post(
        "http://13.209.48.23/api/study",
        {
          name: inputName,
        },
        {
          headers: {
            "X-AUTH-TOKEN": accessToken,
          },
        }
      )
      .then((res) => {
        let id = "";
        console.log(res);
        axios
          .get("http://13.209.48.23/api/study", {
            headers: {
              "X-AUTH-TOKEN": accessToken,
            },
          })
          .then(function (response) {
            id = response.data.data.sno;
            console.log(id);
            navigate("/study/" + id);
          });
      });
  };

  const [OTP, setOTP] = useState("");

  const [groupName, setGroupName] = useState("");
  const [groupTag, setGroupTag] = useState("");

  const onGroupNameChange = (e) => {
    const value = e.target.value;
    setInputName(value);
  };
  const [id, setId] = useState("");

  //id가져오기

  const join = () => {
    axios
      .post(
        "http://13.209.48.23/api/study/join/" + OTP,
        {},
        {
          headers: {
            "X-AUTH-TOKEN": accessToken,
          },
        }
      )
      .then((res) => {
        axios
          .get("http://13.209.48.23/api/study", {
            headers: {
              "X-AUTH-TOKEN": accessToken,
            },
          })
          .then(function (response) {
            navigate("/study/" + response.data.data.sno);
          });

        navigate("/study/" + id);
      });
  };
  return (
    <div className="StudyPage">
      <Header2 />
      <div id="main">
        <p id="title">스터디</p>
        <p id="groupAdd" onClick={GroupAdd}>
          그룹 생성
        </p>
        <img
          id="plus"
          src="../../img/plus.png"
          width="14px"
          alt ="plus"
        />
        <p id="groupFind" onClick={GroupFind}>
          그룹 찾기
        </p>
        <img id="searchIcon" src="../../img/search.png" width="15px" alt ="search"/>
        <hr id="hr2" />
        <div id="content">
          <p id="info">그룹을 만들고 스터디원들과 함께 문제를 풀어나가요</p>
        </div>
      </div>

      <div id="find">
        <p id="title">그룹 찾기</p>
        <p id="groupAdd" onClick={GroupAdd}>
          그룹 생성
        </p>
        <img
          id="plus"
          onClick={GroupFind}
          src="../../img/plus.png"
          width="14px"
          alt ="plus"
        />
        <p id="groupFind" onClick={GroupFind}>
          그룹 찾기
        </p>
        <img id="searchIcon" src="../../img/search.png" width="15px" alt ="search"/>
        <hr id="hr2" />
        <div id="findContent">
          <p>그룹 태그를 입력해주세요</p>
          <p>그룹 이름 옆의 # 뒤 숫자를 입력해주세요</p>
          <OTPInput
            value={OTP}
            onChange={setOTP}
            autoFocus
            OTPLength={6}
            otpType="number"
            disabled={false}
          />
          <button id="findBtn" onClick={join}>
            참가
          </button>
        </div>
      </div>
      <div id="create">
        <p id="title">그룹 생성</p>
        <p id="groupAdd" onClick={GroupAdd}>
          그룹 생성
        </p>
        <img
          id="plus"
          src="../../img/plus.png"
          width="14px"
          alt ="plus"
        />
        <p id="groupFind" onClick={GroupFind}>
          그룹 찾기
        </p>
        <img id="searchIcon" src="../../img/search.png" width="15px" alt ="search"/>
        <hr id="hr2" />
        <div id="findContent">
          <p>그룹 이름을 입력해주세요</p>
          <input id="name" onChange={onGroupNameChange}></input>
          <button id="createBtn" onClick={study}>
            생성
          </button>
        </div>
      </div>
      <div id="study">
        <p id="title">{groupName}</p>
        <p id="tag">#{groupTag}</p>
        <p id="groupAdd" onClick={GroupAdd}>
          그룹 생성
        </p>
        <p id="plus">+</p>
        <p id="groupFind" onClick={GroupFind}>
          그룹 찾기
        </p>
        <img id="searchIcon" src="../../img/search.png" width="15px" alt ="search"/>
        <hr id="hr2" />
        <div id="content">
          <p id="info">그룹을 만들고 스터디원들과 함께 문제를 풀어나가요</p>
        </div>
      </div>
      <style jsx>{`
        #study {
          display: none;
        }
        #name {
          margin: 50px auto 0px 0px;
          font-size: 20px;
          outline: none;
          position: relative;
          text-align: center;
          background-color: #808080;
          border: none;
          width: 80%;
          height: 40px;
          border-radius: 2px;
          padding-left: 10px;
          color: white;
        }
        #Icon {
          background: none;
          margin-right: 5px;
        }
        #findBtn {
          cursor: pointer;
          display: inline-block;
          background-color: #808080;
          border: none;
          color: #fff;
          border-radius: 5px;
          height: 40px;
          width: 150px;
          margin-top: 60px;
        }
        #createBtn {
          cursor: pointer;
          display: block;
          background-color: #808080;
          border: none;
          color: #fff;
          border-radius: 5px;
          height: 40px;
          width: 150px;
          margin: 60px auto 0 auto;
        }
        #findContent {
          width: 500px;
          height: 300px;
          margin: 80px auto 10px auto;
          font-size: 20px;
          background-color: #212529;
          color: white;
          display: block;
          text-align: center;
          padding: 30px;
          border-radius: 20px;
        }
        #findContent > *:not(#findBtn, #Icon, #name, #createBtn) {
          background: none;
          text-align: center;
          display: block;
        }
        #findContent > p:nth-child(1) {
          font-size: 23px;
          margin-bottom: 0px;
        }
        #findContent > p:nth-child(2) {
          font-size: 15px;
          margin-bottom: 30px;
        }
        input:not(#name) {
          background: none;
          color: white;
          border: none;
          border-bottom: 1px solid;
          display: block;
          outline: none;
        }
        input:nth-child(1) {
          margin-left: 100px;
        }

        #info {
          color: white;
          text-align: center;
          font-size: 17px;
          margin-top: 200px;
        }
        #searchIcon {
          display: inline-block;
          float: right;
          margin-top: 41px;
          margin-right: 5px;
          cursor: pointer;
        }
        #plus {
          float: right;
          margin-right: 5px;
          margin-top: 41px;
          margin-bottom: 0px;
          color: white;
          background: none;
          line-height: 0px;
          cursor: pointer;
          display: inline-block;
        }
        table {
          width: 100%;
          margin: 0 auto 0 auto;

          border-collapse: collapse;
          color: #d9d9d9;
          font-size: 15px;
        }
        tr > td:nth-child(1) {
          padding: 5px;
          width: 10%;
          height: 20px;
          border-top: 1px solid #808080;
          border-right: 1px solid #808080;
          border-bottom: 1px solid #808080;
          border-left: none;
          text-align: center;
        }
        tr > td:nth-child(2) {
          border-top: 1px solid #808080;
          border-right: 1px solid #808080;
          border-bottom: 1px solid #808080;
          padding-left: 10px;
          width: 85%;
        }
        tr > td:nth-child(3) {
          text-align: center;
          border-top: 1px solid #808080;
          border-bottom: 1px solid #808080;
          border-right: none;
          width: 10%;
        }
        #date {
          color: white;
          margin-bottom: 10px;
          display: inline-block;
        }
        #groupAdd {
          display: inline-block;
          float: right;
          color: white;
          margin-top: 40px;
          font-size: 15px;
          margin-right: 50px;
          cursor: pointer;
        }
        #groupFind {
          display: inline-block;
          float: right;
          color: white;
          margin-top: 40px;
          font-size: 15px;
          margin-right: 20px;
          cursor: pointer;
        }
        #content {
          width: 80%;
          margin: 40px auto 10px auto;
          font-size: 20px;
        }
        * {
        font-family: 'Pretendard-Regular';
          background-color: #343a40;
        }
        #logo {
          margin-left: 5px;
          margin-top: 10px;
          background: none;
          position: relative;
          z-index: 2;
          display: inline-block;
          cursor: pointer;
        }
        #circle {
          margin-top: 7px;
          display: block;
          background-color: black;
          float: right;
          margin-right: 5px;
          width: 10px;
          height: 10px;
          border-radius: 50%;
        }
        #theme {
          margin-top: 7px;
          display: block;
          float: right;
          color: #d9d9d9;
          font-size: 10px;
          margin-right: 15px;
        }
        #my {
          margin-top: 7px;
          display: block;
          float: right;
          color: #d9d9d9;
          font-size: 10px;
          margin-right: 15px;
        }
        #setting {
          margin-top: 7px;
          display: block;
          float: right;
          color: #d9d9d9;
          font-size: 10px;
          margin-right: 15px;
        }
        header {
          padding: 5px;
        }
        #logout {
          margin-top: 7px;
          display: block;
          float: right;
          color: #d9d9d9;
          font-size: 10px;
          margin-right: 15px;
          cursor: pointer;
        }
        .bar {
          margin-top: 7px;
          display: block;
          float: right;
          color: #d9d9d9;
          font-size: 10px;
          margin-right: 15px;
        }
        #hr1 {
          margin-top: 15px;
          background-color: #808080;
          height: 0.005em;
          border: none;
        }
        #reviewnote {
          text-align: center;
          display: inline-block;
          width: 100px;
          color: #fff;
          cursor: pointer;
        }
        #todo {
          display: inline-block;
          text-align: center;
          width: 100px;
          color: #fff;
          cursor: pointer;
        }
        #planner {
          display: inline-block;
          text-align: center;
          width: 100px;
          color: #fff;
          text-decoration: underline;
          text-underline-position: under;
          cursor: pointer;
        }
        #tab {
          font-size: 18px;
          margin-top: 10px;
          display: inline-block;
          text-align: center;
          left: 50%;
          position: absolute;
          margin-left: -150px;
          background: none;
        }
        #title {
          display: inline-block;
          color: white;
          font-weight: 400;
          font-size: 25px;
          margin-bottom: 20px;
          margin-left: 50px;
          margin-top: 50px;
          line-height: 0px;
        }
        #main {
          width: 90%;
          margin: 0 auto 0 auto;
        }
        #find {
          width: 90%;
          margin: 0 auto 0 auto;
          display: none;
        }
        #create {
          width: 90%;
          margin: 0 auto 0 auto;
          display: none;
        }
        #hr2 {
          margin-top: 5px;
          background-color: #808080;
          height: 0.005em;
          width: 95%;
          border: none;
        }
      `}</style>
    </div>
  );
};

export default StudyPage;
