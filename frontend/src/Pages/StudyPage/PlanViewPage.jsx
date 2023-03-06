import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Header2 from "../../Common/Header2";
import axios from "axios";
import PlanDetail from "./PlanDetail";
const PlanViewPage = () => {
  const accessToken = localStorage.getItem("access_token")
  const navigate = useNavigate();
 
  const studyMain = (e) => {
    console.log(e);
    navigate("/study/" + id);
  };
  const PlanAdd = () => {
    navigate("/study/add");
  };
  const [id, setId] = useState("");
  //id가져오기
  axios
    .get("http://13.209.48.23/api/study", {
      headers: {
        "X-AUTH-TOKEN": accessToken,
      },
    })
    .then(function (response) {
      setId(response.data.data.id);
    });

  const deleteGroup = () => {
    axios
      .delete("http://13.209.48.23/api/study/" + id, {
        headers: {
          "X-AUTH-TOKEN": accessToken,
        },
      })
      .then(function (response) {
        console.log(response);
        navigate("/study");
      });
  };
  return (
    <div className="StudyPage">
      <Header2 />
      <div id="study">
        <img id="listIcon" src="../../img/list.png" width="15px" alt ="list"/>
        <p id="title" onClick={studyMain}>
          목록으로
        </p>
        <p id="groupAdd" onClick={deleteGroup}>
          그룹 나가기
        </p>
        <img id="searchIcon" src="../../img/enter.png" width="15px" alt ="search" />
        <p id="groupFind" onClick={PlanAdd}>
          계획 추가
        </p>
        <img
          id="plus"
          onClick={PlanAdd}
          src="../../img/plus.png"
          width="14px"
          alt ="plus"
        />
        <hr id="hr2" />
        <div id="content">
          <PlanDetail />
        </div>
      </div>
      <style jsx>{`
        #listIcon {
          display: inline-block;
          float: left;
          margin-top: 42px;
          margin-left: 50px;
          cursor: pointer;
        }
        #memberList {
          width: 100%;
          height: 150px;
          text-align: center;
          margin: 20px auto 20px auto;
        }
        #member {
          display: inline-block;
          text-align: center;
          width: 100px;
          float: left;
        }
        #planAdd {
          font-size: 18px;
          color: white;
          cursor: pointer;
          text-align: center;
          margin-top: 30px;
        }
        #StudyPage {
        }
        #detail > p {
          color: white;
          margin-bottom: 0px;
          margin-top: 7px;
        }
        .p {
          margin-left: 20px;
          color: white;
        }
        .back {
          margin-left: 20px;
          font-size: 18px;
          cursor: pointer;
        }
        #problemlist {
          color: white;
          width: 98%;
          margin: 0 auto 0 auto;
          height: 40px;
          background: #212529;
          border-radius: 3px;
        }
        #problemlist > p {
          background: none;
          line-height: 40px;
          margin-left: 10px;
          margin-bottom: 12px;
          margin-top: 12px;
        }
        #listDate {
          cursor: pointer;
        }
        #planlist {
          color: white;
          width: 100%;
          height: 40px;
          background: #212529;
          border-radius: 3px;
        }
        #planlist > p {
          background: none;
          line-height: 40px;
        }

        #tag {
          margin-left: 10px;
          display: inline-block;
          color: white;
        }
        #study {
          display: block;
          width: 90%;
          margin: 0 auto 0 auto;
        }
        .name {
          color: white;
          margin-top: 0px;
          margin-left: 0px;
        }
        .profile {
          margin-bottom: 0px;
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
          font-size: 30px;
          color: white;
          background: none;
          line-height: 0px;
          cursor: pointer;
          display: inline-block;
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
          width: 90%;
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
          float: left;
          color: white;
          margin-top: 40px;
          font-size: 15px;
          margin-left: 10px;
          cursor: pointer;
        }
        #main {
          width: 90%;
          margin: 0 auto 0 auto;
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
export default PlanViewPage;
