import React from 'react';
import axios from 'axios';
 import { useEffect,useState } from "react";
 import { Link, useNavigate } from "react-router-dom";
 import dayjs from 'dayjs';
 import moment from 'moment';
 import 'moment/locale/ko';

 const TodoList = (value) => {
  const [reviewList,setReviewList]=useState([]);
  const [accessToken, setAccessToken] = useState(
    localStorage.getItem("access_token")
  );
  const navigate = useNavigate();

  const [today,setToday]=useState(new Date());


  useEffect(() => {

  axios
  .get("http://13.209.48.23/api/notes", {
    headers: {
      "X-AUTH-TOKEN": accessToken,
    },
  })
  .then(function (response) {
    console.log("NOTE LIST SETTING COMPLETE");
    setReviewList((response.data.data).reverse());
    setToday (moment(today).format('YYYY-MM-DD'));
  });
  },[]);
if(reviewList.length==0) return (<p id="noNote">오답노트를 추가해주세요</p>)
else
    return (
      <div>
      {
   
        reviewList.sort((a,b)=>new Date(a.noteReviews[0].repeat_time)-new Date(b.noteReviews[0].repeat_time))
        .filter((reviewList) =>moment(reviewList.noteReviews[0].repeat_time).diff(today, 'days') >0)
        .map((reviewList)=>{
          return     <div key={reviewList.id} ><div id="content"  key={reviewList}>
          <div id="left">
            <p id="probNum">프로그래머스 {reviewList.number}번</p>
            <p id="probName">{reviewList.subject}</p>
            <div id={reviewList.category=="mistake" ? "circle1" : reviewList.category=="better" ? "circle2" : reviewList.category=="again" ? "circle4" : reviewList.category=="unsolved" ? "circle3":  "circle5"}></div>   
            <p id="type">{reviewList.category}</p>
          </div>
          <div id="right">
            <p id="dDay">D-{moment(reviewList.noteReviews[0].repeat_time).diff(today, 'days')}</p>
            <p id="date">{reviewList.noteReviews[0].repeat_time}</p>
          </div>
        </div>
        <hr id="hr3" />
        </div>
        })
      }
      </div>
    );
};
 

export default TodoList;

