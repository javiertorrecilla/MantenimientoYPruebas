import {browser} from 'k6/experimental/browser';
import {check} from 'k6'; 

export const options = {
    scenarios: {
        ui: {
            executor: 'shared-iterations', // para realizar iteraciones sin indicar el tiempo
            options: {
                browser: {
                    type: 'chromium',
                }
            }
        }
    },
    thresholds: {
    checks: ["rate==1.0"]
    }
}

export default async function () {
    const page = browser.newPage();
    try {
        await page.goto('http://localhost:4200/');

        page.locator('input[name="nombre"]').type('Javier');
        page.locator('input[name="DNI"]').type('11111111X');
    
        const submitButton = page.locator('button[name="login"]');
    
        await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), submitButton.click()]);
    
        check(page, {
        'header': p => p.locator('h2').textContent() == 'Listado de pacientes',
        });
    
        page.waitForSelector('button[name="add"]');
        let createButton = page.locator('button[name="add"]');
        await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), createButton.click()]);

        let id = Math.round(Math.random()*15);
        page.locator('input[name="dni"]').type(id);
        page.locator('input[name="nombre"]').type("Marta");
        page.locator('input[name="edad"]').type(28);
        page.locator('input[name="cita"]').type("Revisión");

        page.waitForSelector('button[type="submit"]');
        const submitButtonPage = page.locator('button[type="submit"]');
        await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), submitButtonPage.click()]);

        page.waitForSelector("table tbody");
        let len = page.$$("table tbody tr").length;

        check(page , {
            'Paciente created' : p => parseInt(p.$$("table tbody tr")[len-1].$('td[name="dni"]').textContent())==id
        });
  
    } finally {
      page.close();
    }
  }
