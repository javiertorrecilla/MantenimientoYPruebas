// import necessary module
import http from "k6/http";
import { check } from "k6";
import { sleep } from 'k6';

//  @{types/k6}
export const options  = {
  vus:5,
  duration:'1m',
  threshold:{
    http_req_duration:[{
        threshold: 'p(100)<=100',
        abortOnFail: true,
    }],
    http_get_failed:[{
        threshold: 'rate==0.0',
        abortOnFail: true,
    }],
  }
};

export default function () {

    // define URL and payload
    const url = "http://localhost:8080/medico/1";

    // send a post request and save response as a variable
    const res = http.get(url);

    // Log the request body
    console.log(res.body);

    // check that response is 200
    check(res, {
        "response code was 200": (res) => res.status == 200,
    });
    sleep(1);
}