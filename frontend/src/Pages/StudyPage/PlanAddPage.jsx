import React, { useState } from "react";
import {useNavigate } from "react-router-dom";
import Header2 from "../../Common/Header2";
import axios from "axios";
import DatePicker from "react-datepicker";
import { ko } from "date-fns/esm/locale";
import "react-datepicker/dist/react-datepicker.css";

const PlanAddPage = () => {
  const accessToken = localStorage.getItem("access_token")
  const [problemList, setProblemList] = useState([]);
  const navigate = useNavigate();

  const [id, setId] = useState("");

  //id가져오기
  axios
    .get("https://codeagain.kro.kr/api/study", {
      headers: {
        "X-AUTH-TOKEN": accessToken,
      },
    })
    .then(function (response) {
      setId(response.data.data.id);
    });

  const [problemNum, setProblemNum] = useState(2);
  const ProblemAdd = () => {
    setProblemNum(problemNum + 1);
    console.log(problemNum);
    return (
      <>
        <input
          onChange={onProblemChange}
          type="text"
          placeholder="ex)프로그래머스 1111번"
          class="problem"
          id={problemNum}
        />
      </>
    );
  };

  const addPlan = () => {
    var sp = [];
    for (var i in problem) {
      sp.push({ subject: problem[i] });
    }
    axios
      .post(
        "https://codeagain.kro.kr/api/study/plans/" + id,
        {
          location: location,
          penaltyContent: panelty,
          penaltyTF: isCheck,
          studyProblems: sp,
          targetDate: startDate,
        },
        {
          headers: {
            "X-AUTH-TOKEN": accessToken,
          },
        }
      )
      .then((res) => {
        console.log(res);
        console.log("NAVIGATE TO STUDY MAIN PAGE");
        navigate("/study/" + id);
      });
      
  };

  const clickAdd = () => {
    setProblemList(problemList.concat(<ProblemAdd key={problemList.length} />));
  };
  const [location, setLocation] = useState("");
  const onLocationChange = (e) => {
    const value = e.target.value;
    setLocation(value);
  };
  const [problem, setProblem] = useState({
    problemId: "",
    problemName: "",
  });
  const onProblemChange = (e) => {
    const problemId = e.target.id;
    const value = e.target.value;

    const nextInputs = {
      ...problem,
      [problemId]: value,
    };

    setProblem(nextInputs);
    console.log(problem);
  };
  const [panelty, setPanelty] = useState("");
  const onPaneltyChange = (e) => {
    const value = e.target.value;
    setPanelty(value);
  };
  const [startDate, setStartDate] = useState(new Date());
  const [isCheck, setIsCheck] = useState(false);
  const changeCheck = (e) => {
    if (e.target.checked) {
      setIsCheck(true);
      document.getElementById("panelty").style.display = "inline-block";
      document.getElementById("paneltyInput").style.display = "block";
      document.getElementById("br").style.display = "block";
    } else {
      setIsCheck(false);
      document.getElementById("panelty").style.display = "none";
      document.getElementById("paneltyInput").style.display = "none";
      document.getElementById("br").style.display = "none";
    }
  };

  return (
    <div className="StudyPage">
      <Header2 />
      <div id="study">
        <p id="title">스터디 계획 추가</p>
        <hr id="hr2" />
        <div id="content">
          <p className="subtitle">일정</p>

          <DatePicker
            id="datePicker"
            locale={ko}
            dateFormat="yyyy년 MM월 dd일"
            selected={startDate}
            onChange={(date) => setStartDate(date)}
          />

          <br />
          <p className="subtitle">장소</p>
          <input
            type="text"
            placeholder="입력해주세요"
            id="probName"
            onChange={onLocationChange}
          />
          <br />
          <p className="subtitle" id="a">
            패널티 여부
          </p>
          <input type="checkbox" id="switch" onClick={changeCheck}></input>
          <label for="switch" class="switch_label">
            <span class="onf_btn"></span>
          </label>
          <br id="br" />
          <p className="subtitle" id="panelty">
            패널티 내용
          </p>
          <input
            type="text"
            placeholder="ex)한 문제 안풀어 올 때 마다 3000원"
            onChange={onPaneltyChange}
            id="paneltyInput"
          />
          <br />
          <p className="subtitle">문제</p>
          <input
            type="text"
            placeholder="ex)프로그래머스 1111번"
            class="problem"
            id="1"
            onChange={onProblemChange}
          />
          {problemList}
          <br />
          <br />
          <p id="add" onClick={clickAdd}>
            + 문제 추가
          </p>
          <button id="save" onClick={addPlan}>
            추가
          </button>
        </div>
      </div>
      <style jsx>{`
   
      *:not(.react-datepicker-popper *, .react-datepicker *,.react-datepicker__month-container *){
        font-family: 'Pretendard-Regular';
          background-color: #343a40;
        }
        .react-datepicker__month-container{
          background-color:#212529;
        }
       #paneltyInput{
        display:none;
       }
       #br{
        display:none;
       }
        .react-datepicker-wrapper{
          
          width:80%;
          display:inline-block;
         height:20px;
         float:right;
          outline: none;
          background-color: #212529;
          height: 35px;
          border: none;
          border-radius: 5px;
          color: #d9d9d9;
          margin-bottom: 0px;
          margin-top: 0px;
          padding-left: 12px;
        
          cursor:pointer;
        }
  
        .react-datepicker__day{
          background-color:#212529;
          color:#d9d9d9;
        
        }
        .react-datepicker__current-month{
          background-color: #d9d9d9;
        }
        .react-datepicker__day-names{
          background-color: #d9d9d9;
        }
        .react-datepicker__day-names > *
        {background-color: #d9d9d9;

        }
        .react-datepicker__day--selected{
          color:white;
        }
        .react-datepicker-popper[data-placement^=bottom] .react-datepicker__triangle::after {
    top: 0;
   left:-30px;
   
}
.react-datepicker-popper[data-placement^=bottom] .react-datepicker__triangle::before {
    top: 0;
   left:-30px;
  
}
.react-datepicker {
    font-family: "Helvetica Neue", helvetica, arial, sans-serif;
    font-size: 0.8rem;
   
    color: #000;
    border: 1px solid #212529;;
    border-radius: 5px;
    display: inline-block;
    position: relative;
}
.react-datepicker__header {
    text-align: center;
    background-color: #d9d9d9;
    border-bottom: 1px solid #aeaeae;
    border-top-left-radius: 0.3rem;
    padding: 8px 0;
    position: relative;
}
        #datePicker{
         
          width:100%;
          height:35px;
          box-sizing:border-box;
          padding:0px;
          font-size: 13px;
cursor:pointer;
        }
        .react-datepicker__input-container{
          display:inline-block;
          width:100%;
          height:35px;
          background-color: #212529;
          border-radius: 5px;
        }
      #save{
              cursor:pointer;
            display: inline-block;
            background-color: #212529;
            border: none;
            color:#D9D9D9;
            border-radius: 5px;
            height: 40px;
            width: 100px;
            margin-left: 81%;
            margin-top: 50px;
            margin-bottom: 100px;
            float:right;
            }
        #a {
          margin-top: 0px;
        }
        #switch:checked + .switch_label {
          background: #808080;
          border: 2px solid #808080;
        }

        #switch:checked + .switch_label:hover {
          background: #808080;
        }

        /* move */
        #switch:checked + .switch_label .onf_btn {
          left: 60px;
          background: #fff;
          box-shadow: 1px 2px 3px #00000020;
        }
        #switch {
          position: absolute;
          /* hidden */
          appearance: none;
          -webkit-appearance: none;
          -moz-appearance: none;
        }
        .switch_label {
          line-height: 30px;
          margin-top: 5px;
          margin-left: 11.5%;
          position: relative;
          cursor: pointer;
          display: inline-block;
          width: 90px;
          height: 30px;
          background: #212529;
          border: 2px solid #212529;
          border-radius: 20px;
          transition: 0.2s;
        }
        .switch_label:hover {
          background: #efefef;
          border: 2px solid #efefef;
        }
        .onf_btn {
          position: absolute;
          top: 5px;
          left: 5px;
          display: inline-block;
          width: 20px;
          height: 20px;
          border-radius: 20px;
          background: white;
          transition: 0.2s;
        }
        #add {
          background: none;

          margin-left: 20%;
          text-align: center;
          font-size: 17px;
          color: white;
        }
        input[type="text"]:not(.problem) {
          float: right;
          outline: none;
          font-size: 13px;
          background-color: #212529;
          width: 80%;
          height: 30px;
          border: none;
          border-radius: 5px;
          color: #d9d9d9;
          margin-bottom: 0px;
          margin-top: 0px;
          padding-left: 10px;
        }
        .subtitle {
          font-size: 13px;
          display: inline-block;
          margin-right: 5px;
          width: 100px;
          color: white;
        }
        #planAdd {
          font-size: 18px;
          color: white;
          cursor: pointer;
        }
        #StudyPage {
        }
        #detail {
          display: none;
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
          width: 100%;
          height: 40px;
          background: #212529;
          border-radius: 3px;
        }
        #problemlist > p {
          background: none;
          line-height: 40px;
          margin-left: 10px;
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
          margin-left: 10px;
        }
        #member {
          text-align: center;
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
          font-family: 'Pretendard-Regular';
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
          margin: 40px auto 0px; auto;
          font-size: 20px;
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
        #problem {
          margin-top: 10px;
          position: relative;
        }
        #panelty{
          display:none;
        }
        .problem{
          float: right;
          outline: none;
          font-size: 13px;
          background-color: #212529;
          width: 80%;
          height: 30px;
          border: none;
          border-radius: 5px;
          color: #d9d9d9;
          margin-bottom: 0px;
        
          padding-left: 10px;
          margin-top:10px;
        }
      `}</style>
    </div>
  );
};
export default PlanAddPage;
