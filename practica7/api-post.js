// import necessary module
import http from "k6/http";
import { check } from "k6";
import { sleep } from 'k6';


/**
 * @type {import("k6/options").Options}
 */
export let options = {
    max_vus: 10,
    vus: 10,
    stages: [
      { duration: "30s", target: 10 },
      { duration: "1m", target: 10 },
      { duration: "30s", target: 0 }
    ]
  }

export default function () {

    const url = "http://localhost:8080/persona";
    const payload = JSON.stringify({
        nombre: "Pepe Botella",
        dni: "12"+Math.random(1000),
        edad : 22
    });

    const params = {
        headers: {
            "Content-Type": "application/json",
        },
    };
    
    // send a post request and save response as a variable
    const res = http.post(url, payload, params);

    // Log the request body
    console.log(res.body);

    // check that response is 200
    check(res, {
        "response code was 200": (res) => res.status == 200,
    });
    sleep(1);
}
