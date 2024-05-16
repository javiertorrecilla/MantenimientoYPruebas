// import necessary module
import http from "k6/http";
import { check } from "k6";
import { sleep } from 'k6';

//  @{types/k6}
export const options  = {
  scenarios: {
    scenario: {
      executor: 'constant-vus',
      vus: 10,
      duration: '30s',
    },
  },
  thresholds: {
    checks: ["rate==1.0"]
  }
};

export default function () {

    // define URL and payload
    const url = "http://localhost:8080/hello";

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
