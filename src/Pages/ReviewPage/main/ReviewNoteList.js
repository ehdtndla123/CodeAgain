import React from 'react';
import axios from 'axios';
 import { useEffect,useState } from "react";
 import { useNavigate } from "react-router-dom";
 import 'moment/locale/ko';

 const ReviewNoteList = () => {
  const CHECKED_IMG_PATH = "../../img/smile.png";
  const UNCHECKED_IMG_PATH = "../../img/darkSmile.png";
  const [reviewList,setReviewList]=useState([]);
  const [accessToken, setAccessToken] = useState(
    localStorage.getItem("access_token")
  );
  const navigate = useNavigate();

  const viewNote = (id) => {
    axios
    .get("http://13.209.48.23/api/notes/"+id, {
      params : id,
      headers: {
        "X-AUTH-TOKEN": accessToken,
      },
    })
    .then(function (response) {
      console.log("NAVIGATE TO REVIEW VIEW PAGE");
      navigate("/review/"+id, {
        state: { ID:id },
      });
      
    });
  }

  useEffect(() => {

  axios
  .get("http://13.209.48.23/api/notes", {
    headers: {
      "X-AUTH-TOKEN": accessToken,
    },
  })
  .then(function (response) {
    console.log(response)
    console.log("NOTE LIST SETTING COMPLETE");
    setReviewList((response.data.data).reverse());
  });

  },[]);
if(reviewList.length === 0) return (<p id="noNote">오답노트를 추가해주세요</p>)
else
    return (
      <div>
      {
        reviewList.map((reviewList)=>{
          return  <div id="content" key={reviewList.id} onClick={()=> viewNote(reviewList.id)}>
                    <div id={reviewList.category ==="mistake" ? "circle1" : reviewList.category ==="better" ? "circle2" : reviewList.category=="again" ? "circle4" : reviewList.category=="unsolved" ? "circle3":  "circle5"}></div>
                    <p className="c_type">{reviewList.category}</p>
                    <p className="date">{(reviewList.targetDate).split("T")[0]}</p>
                    <p className="number">프로그래머스 {reviewList.number}번</p>
                    <p className="title">{reviewList.subject}</p>
                    {(reviewList.noteReviews).reverse().map((review)=>{
                      return( <img  src={(review.repeat_complete) === true ? CHECKED_IMG_PATH : UNCHECKED_IMG_PATH} className="sticker" width="33px" />)
                      
                    })}
                  </div>;
        })
      }
      </div>
    );
};
 

export default ReviewNoteList;

