import http from 'k6/http';
import { check } from 'k6';


export default function () {
  let response = http.get('http://localhost:8080/medico/1');

  // check that response code is 200
  // and check that response body contains Contact us
  check(response, {
    'response code was 200': (res) => res.status == 200,
    'Body contains Contact us': (r) => r.body.includes('Contact us')
  });
}