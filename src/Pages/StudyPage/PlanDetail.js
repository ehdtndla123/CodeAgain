import React from "react";
import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import Member from "./Member";
import { useLocation } from "react-router-dom";

const PlanDetail = () => {
  const [plan, setPlan] = useState([]);
  const [id, setId] = useState("");
  const accessToken = localStorage.getItem("access_token")
  const navigate = useNavigate();
  const [pid, setPid] = useState(useLocation().state.pid);

  // study id get
  axios
    .get("http://13.209.48.23/api/study", {
      headers: {
        "X-AUTH-TOKEN": accessToken,
      },
    })
    .then(function (response) {
      setId(response.data.data.id);
     
    });

 
      useEffect(() => {
        axios
          .get("http://13.209.48.23/api/study/plans/" + id, {
            headers: {
              "X-AUTH-TOKEN": accessToken,
            },
          })
          .then(function (response) {
            console.log(response)
            console.log("STUDY PLAN DETAIL SETTING COMPLETE");
            setPlan(response.data.data);
          });
      }, [id]);

    
 
  return (
    <div>
      {plan
        .filter((plan) => plan.id === pid)
        .map((plan) => {
          return (
            <div
              id="plan"
              key={plan.id} 
            >
              <div id="detail">
          
                <p className="p">
                  스터디 날짜 : {plan.targetDate.substr(0, 4)}년 &nbsp;
                  {plan.targetDate.substr(5, 2)}월 &nbsp;
                  {plan.targetDate.substr(8, 2)}일
                </p>
                <p className="p">장소 : {plan.location}</p>
                <p className="p"> {plan.penaltyTF===true ? "패널티 : "+ plan.penaltyContent : ""}</p>
                <Member/>
                
                {(plan.studyProblems).filter((p)=>p.subject!=="")
                .map((p) => (
                  <div id="problemlist" key={p.id} >
                    <p>{p.subject}</p>
                    <p id="addCode"></p>
                  </div>
                ))}
              </div>
            </div>
          );
        })}
    </div>
  );
};
export default PlanDetail;
