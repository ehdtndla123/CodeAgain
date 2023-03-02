import React from "react";
import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
const Plan = () => {
  const [plan, setPlan] = useState([]);
  const [id, setId] = useState("");
  const accessToken = localStorage.getItem("access_token")
  const navigate = useNavigate();
  //id get
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
        console.log("STUDY PLAN LIST SETTING COMPLETE");
        setPlan(response.data.data.reverse());
      });
  }, [id,accessToken]);

  const viewPlan = (e, planID) => {
    navigate("/study/view/" + planID, { state: { pid: planID } });
  };

  if (plan.length === 0) return <></>;
  else
    return (
      <div>
        {plan.map((plan) => {
          return (
            <div
              id="plan"
              key={plan.id} /*onClick={()=> viewNote(reviewList.id)}*/
            >
              <div id="planlist" onClick={(event) => viewPlan(event, plan.id)}>
                <p id="listDate">
                  {plan.targetDate.substr(0, 4)}년{" "}
                  {plan.targetDate.substr(5, 2)}월{" "}
                  {plan.targetDate.substr(8, 2)}일
                </p>
              </div>
            </div>
          );
        })}
      </div>
    );
};

export default Plan;
