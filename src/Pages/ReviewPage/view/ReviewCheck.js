import React from 'react';
import axios from 'axios';
import { useEffect,useState } from "react";
import { useLocation } from "react-router-dom";

const ReviewCheck = () => {
    const [id, setId] = useState(useLocation().state.ID);
    const accessToken = localStorage.getItem("access_token")
    const CHECKED_IMG_PATH = "../../img/smile.png";
    const UNCHECKED_IMG_PATH = "../../img/darkSmile.png";
    const [data,setData]=useState([]);
    
    useEffect(() => {
    axios
    .get("http://13.209.48.23/api/notes/" + id, {
      params: id,
      headers: {
        "X-AUTH-TOKEN": accessToken,
      },
    })
    .then(function (response) {
      console.log("REVIEW DEATIL SETTING COMPLETE");
     
     setData((response.data.data.noteReviews));
    });
}, []);

       const stickerChange = (e, reviewId) => {
        console.log(reviewId)
       if (e.target.value===false) {
        e.target.src=CHECKED_IMG_PATH;
        e.target.value=true;
        
        axios
        .put(
          "http://13.209.48.23/api/notes/review/"+reviewId,
      
          {
            repeat_complete:true,
          },
          {
            headers: {
              "X-AUTH-TOKEN": accessToken,
            },
          }
        )
          .then((res) => {
            console.log(res);
          
          });
     
        }
        else if (e.target.value===true) {
          console.log("이거왜안돼")
          e.target.src=UNCHECKED_IMG_PATH;
          e.target.value=false;
          axios
          .put(
            "http://13.209.48.23/api/notes/review/"+reviewId,
            {
              repeat_complete:false,
            },
            {
              headers: {
                "X-AUTH-TOKEN": accessToken,
              },
            }
          )
            .then((res) => {
              console.log(res);
            
            });
      
        }else{
          e.target.src=CHECKED_IMG_PATH;
          e.target.value=true;
          axios
          .put(
            "http://13.209.48.23/api/notes/review/"+reviewId,
        
            {
              repeat_complete:true,
            },
            {
              headers: {
                "X-AUTH-TOKEN": accessToken,
              },
            }
          )
            .then((res) => {
              console.log(res);
            
            });
        }
      }

      
return(
<>
        {(data.sort((a,b)=>new Date(a.repeat_time)-new Date(b.repeat_time)))
        .map((data) => {
          return (
            <div key={data.id}>
            <input type="checkbox" id={data.id}  />
            
            <div id="reviewCheckContent">
                <p id="reviewDate">{data.repeat_time}</p>
                <label htmlFor={data.id}>
                <img
                    className="sticker"
                    id={data.id}
                    value={data.repeat_complete}
                    src={(data.repeat_complete)===true ? CHECKED_IMG_PATH : UNCHECKED_IMG_PATH}
                    width="50px"
                    onClick={(event) => stickerChange(event,data.id)} 
                ></img>
                </label>
           
         </div>
        </div>
        )})}
</>
)
 }
export default ReviewCheck;