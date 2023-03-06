import axios from "axios";

const BaseUrl = 'http://localhost:3000';


export async function login(id, pwd) {
    
    var res = await axios.post(BaseUrl + "/login", {
        id: id,
        pwd: pwd,
    });
    return res;
}

export default async function checkEmail(email) {
    var res = await axios
    .post(
      BaseUrl +"check/email",
      { email: email },
      {
        headers: {
          "Content-Type": `application/json`,
        },
      }
    )
    return res;
}