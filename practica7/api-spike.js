import http from "k6/http";
import { check } from "k6";
import { sleep } from 'k6';

export const options = {
    stages: [
        { duration: '2m', target: 3783 }, 
        {duration: '1m', target: 0},
                ],
    
    thresholds: {
    http_req_failed: [{
    threshold: 'rate<=0.005',
    abortOnFail: true,
        }]}
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