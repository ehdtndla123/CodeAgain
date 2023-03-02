import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Header2 from "../../../Common/Header2";
import { useLocation } from "react-router-dom";
import moment from "moment";
import "moment/locale/ko";

import axios from "axios";
const ReviewUpdatePage = () => {
  const navigate = useNavigate();
  const [checked, setChecked] = useState([]);
  const [type, setType] = useState("");
  const [date, setDate] = useState("");
  const [problemNum, setProblemNum] = useState("");
  const [problemName, setProblemName] = useState("");
  const [description, setDescription] = useState(
    useLocation().state.Description
  );
  const [id, setId] = useState(useLocation().state.ID);
  const [code, setCode] = useState(useLocation().state.Code);
  const [memo, setMemo] = useState(useLocation().state.Memo);
  const [accessToken, setAccessToken] = useState(
    localStorage.getItem("access_token")
  );
  const [checkedList, setCheckedList] = useState([]);

  //오답노트 리스트 불러오기
  const ReviewViewPage = (e) => {
    var sp = [];
    for (var i in checkedList) {
      sp.push({ repeat_time: checkedList[i], repeat_complete: false });
    }

    var today = new Date();
    today = moment(today).format("YYYY-MM-DD");

    axios
      .put(
        "http://13.209.48.23/api/notes/" + id,
        {
          category: type,
          code: code,
          description: description,
          memo: memo,
          number: problemNum,
          subject: problemName,
          targetDate: today,
          noteReviews: sp,
        },
        {
          headers: {
            "X-AUTH-TOKEN": accessToken,
          },
        }
      )
      .then((res) => {
        console.log(res);
        navigate("/review/" + id, {
          state: { ID: id },
        });
      });
  };

  const handleChecked = (e) => {
    setType(e.target.value);
    setChecked(e.target.value);
  };
  useEffect(() => {
    axios
      .get("http://13.209.48.23/api/notes/" + id, {
        params: id,
        headers: {
          "X-AUTH-TOKEN": accessToken,
        },
      })
      .then(function (response) {
        console.log(response.data);
        setDate(response.data.data.targetDate);
        setCode(response.data.data.code);
        setDescription(response.data.data.description);
        setProblemNum(response.data.data.number);
        setProblemName(response.data.data.subject);
        setType(response.data.data.category);
        setChecked(response.data.data.category);
      });
  }, []);
  //코드 상태관리
  const onChangeCode = (e) => {
    setCode(e.target.value);
  };
  //메모 상태관리
  const onChangeMemo = (e) => {
    setMemo(e.target.value);
  };
  //문제설명 상태관리
  const onIntroChange = (e) => {
    setDescription(e.target.value);
  };

  return (
    <div className="ReviewAddPage">
      <Header2 />
      <div id="add">
        <p id="probNum">프로그래머스 {problemNum}번</p>
        <p id="probName">{problemName}</p>
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
        <p class="type">{type}</p>
        <p id="date">{date.split("T")[0]}</p>
        <br />
        <br />
        <div id="main">
          <p id="subtitle" class="subtitle">
            문제설명
          </p>
          <textarea spellcheck="false" id="probIntro" onChange={onIntroChange}>
            {description}
          </textarea>
          <br />
          <p id="wt" class="subtitle">
            오답유형
          </p>
          <br />
          <div id="wrongType">
            <div id="circle1"></div>
            <input
              type="radio"
              value="mistake"
              id="mistake"
              checked={checked === "mistake"}
              onChange={handleChecked}
            />
            <label htmlFor="mistake" class="type">
              mistake
            </label>
            <div id="circle2"></div>
            <input
              type="radio"
              value="better"
              id="better"
              checked={checked === "better"}
              onChange={handleChecked}
            />
            <label htmlFor="better" class="type" id="better">
              better
            </label>
            <div id="circle3"></div>
            <input
              type="radio"
              value="unsolved"
              id="unsolved"
              checked={checked === "unsolved"}
              onChange={handleChecked}
            />
            <label htmlFor="unsolved" class="type" id="unsolved">
              unsolved
            </label>
            <div id="circle4"></div>
            <input
              type="radio"
              value="again"
              id="again"
              checked={checked === "again"}
              onChange={handleChecked}
            />
            <label htmlFor="again" class="type" id="again">
              again
            </label>
            <div id="circle5"></div>
            <input
              type="radio"
              value="etc"
              id="etc"
              checked={checked === "etc"}
              onChange={handleChecked}
            />
            <label htmlFor="etc" class="type" id="etc">
              etc
            </label>
          </div>
          <br />
          <br />
          <p class="subtitle">코드</p>
          <textarea spellcheck="false" id="probCode" onChange={onChangeCode}>
            {code}
          </textarea>
          <br />
          <br />
          <p id="memoTitle" class="subtitle">
            메모
          </p>
          <textarea spellcheck="false" id="memo" onChange={onChangeMemo}>
            {memo}
          </textarea>
          <br />
          <br />
          <br />
          <br />
          <button onClick={ReviewViewPage} id="save">
            저장
          </button>
        </div>
      </div>
      <style jsx>{`
        input[type="radio"] {
          display: none;
        }
        input[type="radio"]:checked + label {
          color: #d9d9d9;
        }
        #circle1 {
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
          margin-top: 10px;
          display: block;
          background-color: #c89ec4;
          float: left;
          margin-right: 5px;
          width: 10px;
          height: 10px;
          border-radius: 50%;
        }
        #circle6 {
          margin-top: 10px;
          display: block;
          background-color: #67d5b5;
          float: left;
          margin-right: 5px;
          width: 10px;
          height: 10px;
          border-radius: 50%;
        }
        #none {
          margin-top: 100px;
          text-align: center;
          color: #d9d9d9;
        }
        #update {
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
        #sticker {
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
          cursor: auto;
          font-size: 15px;
        }

        #introP {
          line-height: 30px;
          font-size: 15px;
        }
        #probIntro {
          font-size: 15px;
          margin-top: 5px;
          background-color: #212529;
          width: 80.5%;
          height: 250px;
          border: none;
          border-radius: 5px;
          resize: none;
          color: #d9d9d9;
          line-height: 25px;
        }
        * {
          font-family: "GothicA1-Light";
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
          cursor: pointer;
          color: #fff;
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
          font-family: "GothicA1-Bold";
          font-size: 23px;
          margin-top: 80px;
          line-height: 0px;
        }
        .type {
          color: #808080;
          float: left;
          margin-right: 20px;
          margin-top: 6px;
          background: none;
          font-size: 16px;
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
          font-family: "GothicA1-Bold";
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
          font-family: "GothicA1-Light";
          color: #d9d9d9;
          margin-top: 5px;
          font-family: "GothicA1-Light";

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
          font-family: "GothicA1-Bold";
          width: 100%;
          font-size: 15px;
          margin-top: 50px;
          line-height: 0px;
          margin-left: 5px;
        }
        #probName {
          display: inline-block;
          color: white;
          font-family: "GothicA1-Bold";
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
          font-size: 15px;
          margin-top: 5px;
          background-color: #212529;
          width: 80.5%;
          height: 250px;
          border: none;
          border-radius: 5px;
          resize: none;
          color: #d9d9d9;
          line-height: 25px;
        }
        #addCode {
          display: block;
          text-align: center;
          color: #d9d9d9;
          margin-top: 0;
          margin-bottom: 30px;
        }
        #memo {
          background-color: #212529;
          width: 80.5%;
          height: 100px;
          border: none;
          border-radius: 5px;
          resize: none;
          color: #d9d9d9;
        }

        #save {
          display: inline-block;
          background-color: #212529;
          border: none;
          color: #d9d9d9;
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
          margin-top: 0px;
          background: none;
        }
        [type="checkbox"] {
          width: 10px;
          height: 10px;
          float: left;
          margin-right: 7px;
          border-radius: 50%;
          margin-top: 10px;
          border-radius: 50%;
          border: 1px solid #999;
          appearance: none;
        }
        input[type="checkbox"]:checked {
          background: #ffffff;
          border: none;
        }
        input[type="checkbox"]:checked + label {
          color: #d9d9d9;
        }
      `}</style>
    </div>
  );
};

export default ReviewUpdatePage;
