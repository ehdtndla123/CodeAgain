import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Header2 from "../../../Common/Header2";
import { useLocation } from "react-router-dom";
import axios from "axios";
import ReviewCheck from "./ReviewCheck";

const ReviewViewPage = () => {
  const navigate = useNavigate();
  const [id, setId] = useState(useLocation().state.ID);

  const ReviewUpdatePage = (e) => {
    console.log(e);
    navigate("/review/" + id + "/update", {
      state: { ID: id, Code: code, Description: description, Memo: memo },
    });
  };

  const [type, setType] = useState("");
  const [date, setDate] = useState("YYYY-MM-DD");
  const [problemNum, setProblemNum] = useState("");
  const [problemName, setProblemName] = useState("s");
  const [code, setCode] = useState("");
  const [description, setDescription] = useState("");
  const [memo, setMemo] = useState("");
  const accessToken = localStorage.getItem("access_token")

  axios
    .get("http://13.209.48.23/api/notes/" + id, {
      params: id,
      headers: {
        "X-AUTH-TOKEN": accessToken,
      },
    })
    .then(function (response) {
      console.log("REVIEW DETAIL SETTING COMPLETE");
      setProblemNum(response.data.data.number);
      setProblemName(response.data.data.subject);
      setType(response.data.data.category);
      setCode(response.data.data.code);
      setDescription(response.data.data.description);
      setMemo(response.data.data.memo);
      setDate(response.data.data.targetDate);
    });

  const ReviewDelete = () => {
    axios
      .delete("http://13.209.48.23/api/notes/" + id, {
        params: id,
        headers: {
          "X-AUTH-TOKEN": accessToken,
        },
      })
      .then(function (response) {
        console.log("REVIEW NOTE DELETE COMPLETE");
        navigate("/review");
      });
  };

  return (
    <div className="ReviewAddPage">
      <Header2 />
      <div id="add">
        <p id="probNum">프로그래머스 {problemNum}번</p>
        <p id="probName">{problemName}</p>
        <img onClick={ReviewUpdatePage} id="update" src="../../img/update.png" width="27px" alt="update"></img>

        <hr id="hr2" />
        <div
          id={
            type === "mistake"
              ? "circle1"
              : type === "better"
              ? "circle2"
              : type === "again"
              ? "circle4"
              : type === "unsolved"
              ? "circle3"
              : "circle5"
          }
        ></div>

        <p className="type">{type}</p>
        <p id="date">{date.split("T")[0]}</p>
        <br />
        <br />
        <div id="main">
          <p id="subtitle" className="subtitle">
            문제설명
          </p>
          <div id="probIntro">
            <p id="introP">{description}</p>
          </div>
          <br />
          <br />
          <p className="subtitle">코드</p>
          <p id="probCode">{code}</p>
          <br />
          <p id="memoTitle" className="subtitle">
            메모
          </p>
          <div id="memo">
            <p>{memo}</p>
          </div>
          <br />
          <br />
          <p className="subtitle">반복확인</p>
          <div id="reviewCheck">
            <ReviewCheck />
          </div>
          <button onClick={ReviewDelete} id="delete">
            DELETE
          </button>
        </div>
      </div>
      <style jsx>{`
        .console-log-level .console-message-text {
          color: green;
        }
        #update {
          float: right;
          display: inline-block;
          cursor: pointer;
          margin-left: 10px;
          margin-top: 1px;
        }
        #delete {
          margin-top: 0px;
          float: right;
          display: inline-block;
          cursor: pointer;
        }
        #memoTitle {
          margin-top: 20px;
        }
        #reviewCheckContent {
          border-right: 1px solid #343a40;
          float: left;
          width: 120px;

          background: none;
        }
        #reviewDate {
          margin-top: 0px;
          margin-bottom: 10px;
          background: none;
          text-align: center;
        }
        .sticker {
          display: block;
          margin: 0 auto 0 auto;
          background: none;
          cursor: pointer;
        }
        #reviewCheck {
          padding-top: 10px;
          padding-bottom: 10px;
          background-color: #212529;
          border-radius: 5px;
          color: #d9d9d9;
          margin-top: 5px;
          background-color: #212529;
          width: 80%;
          border-radius: 5px;
          display: inline-block;
          margin-bottom: 80px;
        }
        textarea::-webkit-scrollbar {
          width: 10px;
        }
        textarea::-webkit-scrollbar-track {
          border-radius: 100px;
        }
        textarea::-webkit-scrollbar-thumb {
          background-color: #808080;
          border-radius: 100px;
          padding-right: 20px;
        }
        textarea {
          scrollbar-color: #d9d9d9;
          white-space: pre-wrap;
          cursor: auto;
        }

        #introP {
          line-height: 30px;
          font-size: 15px;
        }
        #probIntro {
          margin-left: 100px;
          padding-left: 10px;
          color: #d9d9d9;
          width: 80%;
          border-radius: 5px;
          border: none;
          border-radius: 5px;
          resize: none;
          color: #d9d9d9;
        }
        * {
        font-family: 'Pretendard-Regular';
          background-color: #343a40;
        }
        header {
          padding: 5px;
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

        input,
        textarea {
          outline: none;
        }

        textarea {
          padding: 5px;
          box-sizing: border-box;
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
        #circle1 {
          margin-left: 5px;
          margin-top: 10px;
          display: block;
          background-color: #67d5b5;
          float: left;
          margin-right: 5px;
          width: 10px;
          height: 10px;
          border-radius: 50%;
        }
        #circle2 {
          margin-left: 5px;
          margin-top: 10px;
          display: block;
          background-color: #84b1ed;
          float: left;
          margin-right: 5px;
          width: 10px;
          height: 10px;
          border-radius: 50%;
        }
        #circle3 {
          margin-left: 5px;
          margin-top: 10px;
          display: block;
          background-color: #ee7785;
          float: left;
          margin-right: 5px;
          width: 10px;
          height: 10px;
          border-radius: 50%;
        }
        #circle4 {
          margin-left: 5px;
          margin-top: 10px;
          display: block;
          background-color: #feee7d;
          float: left;
          margin-right: 5px;
          width: 10px;
          height: 10px;
          border-radius: 50%;
        }
        #circle5 {
          margin-left: 5px;
          margin-top: 10px;
          display: block;
          background-color: #c89ec4;
          float: left;
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
          text-decoration: underline;
          text-underline-position: under;
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
          color: white;
          font-size: 23px;
          margin-top: 80px;
          line-height: 0px;
        }
        .type {
          color: #808080;
          float: left;
          margin-right: 20px;
          margin-top: 6px;
        }
        #hr2 {
          margin: 0px auto 0 auto;
          display: block;
          text-align: center;
          margin-top: 15px;
          margin-bottom: 5px;
          background-color: #808080;
          height: 0.005em;
          width: 100%;
          border: none;
        }
        #none {
          margin-top: 100px;
          text-align: center;
          color: #d9d9d9;
        }
        #c_circle {
          margin-top: 5px;
          display: block;
          background-color: #67d5b5;
          float: left;
          margin-right: 5px;
          width: 10px;
          height: 10px;
          border-radius: 50%;
        }
        .c_type {
          margin-top: 0px;
          color: #808080;
          float: left;
          margin-right: 20px;
        }
        #date {
          color: #808080;
          float: right;
          margin-top: 10px;
          margin-right: 5px;
        }
        .number {
          display: block;
          margin-top: 40px;
          margin-left: 0px;
          color: #808080;
          margin-bottom: 5px;
        }
        .title {
          color: white;
          font-size: 25px;
          margin-top: 0px;
          margin-bottom: 10px;
        }
        #main {
          width: 95%;
          margin: 30px auto 0 5%;
        }
        #add {
          width: 70%;
          margin: 0 auto 0 auto;
        }
        #subtitle {
        }
        .subtitle {
          font-size: 13px;
          display: inline-block;
          margin-right: 5px;
          width: 100px;
          color: #d9d9d9;
          margin-top: 5px;

          float: left;
        }

        #probSearch {
          display: inline-block;
          width: 80.5%;
          height: 30px;
          margin-top: 5px;
        }
        #probNum {
          display: inline-block;
          color: #808080;
          width: 100%;
          font-size: 15px;
          margin-top: 50px;
          line-height: 0px;
          margin-left: 5px;
        }
        #probName {
          display: inline-block;
          color: white;
          font-size: 28px;
          margin-top: 15px;
          font-weight: 600;
          line-height: 0px;
          margin-left: 5px;
          margin-bottom: 10px;
        }
        #wt {
          margin-top: 30px;
        }

        #wrongType {
          color: #d9d9d9;
          padding-left: 10px;
          background-color: #212529;
          width: 80%;
          border-radius: 5px;
          height: 30px;
          display: inline-block;
        }
        #alarm {
          color: #d9d9d9;
          margin-top: 5px;
          padding-left: 10px;
          background-color: #212529;
          width: 80%;
          border-radius: 5px;
          height: 30px;
          display: inline-block;
        }
        #wrongType > p {
          background: none;
          margin-top: 5px;
        }
        #probCode {
          line-height: 25px;
          font-size: 15px;
          white-space: pre-line;
          font-size: 15px;
          margin-top: 5px;
          background-color: #212529;
          margin-left: 100px;
          padding: 10px;
          color: #d9d9d9;
          width: 78%;
          border-radius: 5px;
          border: none;
          border-radius: 5px;
          resize: none;
          color: #d9d9d9;
        }
        #addCode {
          display: block;
          text-align: center;
          color: #d9d9d9;
          margin-top: 0;
          margin-bottom: 30px;
        }
        #memo {
          padding-left: 10px;
          color: #d9d9d9;
          width: 80%;
          border-radius: 5px;
          display: inline-block;
          width: 80.5%;
          border: none;
          border-radius: 5px;
          resize: none;
          color: #d9d9d9;
        }

        #delete {
          display: inline-block;
          background-color: #212529;
          border: none;
          color: #be2e22;
          border-radius: 5px;
          height: 40px;
          width: 100px;
          margin-left: 81%;
          margin-top: 50px;
          margin-bottom: 100px;
          cursor: pointer;
        }
        .when {
          color: #808080;
          display: inline;
          background: none;
          margin-right: 10px;
        }
        [type="checkbox"] {
          display: none;
        }
        .checkbox:checked + .sticker {
          display: none;
        }
      `}</style>
    </div>
  );
};

export default ReviewViewPage;
