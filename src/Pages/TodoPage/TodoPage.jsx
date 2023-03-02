import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import Header2 from "../../Common/Header2";
import Todo from "./Todo";
const TodoPage = () => {
  const [probNum, setProbNum] = useState("문제 번호");
  const [probName, setProbName] = useState("문제 제목");
  const [type, setType] = useState("type");
  const [dDay, setdDay] = useState("D-1");
  const [date, setdate] = useState("YYYY.MM.DD (M)");
  return (
    <div className="PlannerPage">
      <Header2 />
      <div id="main">
        <p id="title">TODO</p>
        <hr id="hr2" />
        <div id="list">
          <Todo />
        </div>
      </div>
      <style jsx>{`
        * {
      font-family: 'Pretendard-Regular';
          background-color: #343a40;
        }
        #noNote {
          font-size: 20px;
          margin-top: 200px;
          text-align: center;
          color: #d9d9d9;
        }
        #date {
          color: #808080;
          margin-bottom: 0px;
          margin-top: 15px;
        }
        #dDay {
          letter-spacing: 2px;
          color: #d9d9d9;
          font-size: 30px;
          margin-top: 15px;
          margin-bottom: 5px;
          float: right;
          font-weight: 400;
        }
        #content {
          padding-left: 20px;
          padding-right: 20px;
        }
        #circle1 {
          margin-top: 10px;
          display: block;
          background-color: #67d5b5;
          float: left;
          margin-right: 5px;
          margin-left: 2px;
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
        #probNum {
          color: #808080;
          margin-bottom: 0px;
          margin-top: 10px;
        }
        #probName {
          color: #d9d9d9;
          margin-top: 3px;
          font-size: 20px;
          font-weight: 600;
          margin-bottom: 0px;
        }
        #type {
          color: #808080;
          margin-top: 5px;
        }
        #left {
          float: left;
        }
        #right {
          float: right;
        }
        #list {
          width: 95%;
          margin: 0 auto 0 auto;
        }
        #hr2 {
          margin-top: 5px;
          background-color: #808080;
          height: 0.005em;
          width: 95%;
          border: none;
        }
        #hr3 {
          margin-top: 5px;
          background-color: #808080;
          height: 0.005em;
          width: 100%;
          border: none;
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
          text-decoration: underline;
          text-underline-position: under;
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
          font-weight: 400;
          font-size: 25px;
          margin-left: 50px;
          margin-top: 50px;
          line-height: 0px;
        }
        #main {
          width: 90%;
          margin: 0 auto 0 auto;
        }
      `}</style>
    </div>
  );
};

export default TodoPage;
