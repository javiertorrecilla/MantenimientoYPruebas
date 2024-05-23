import { browser } from 'k6/experimental/browser';
import { check, sleep } from 'k6';

export const options = {
    scenarios: {
      ui: {
        executor: 'shared-iterations', // para realizar iteraciones sin indicar el tiempo
        options: {
          browser: {
            type: 'chromium',
          },
        },
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

    //esperar a home
    page.waitForSelector("table tbody");

    //obtengo la lista de pacientes
    let paciente1 = page.$$("table tbody tr")[0];

    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), paciente1.click()]);

    //esperar a table del paciente
    page.waitForSelector("table tbody");

    //escojo el ojo
    let ojo = page.$$("table tbody tr")[0].$('button[name="view"]');

    //pagina carga
    await Promise.all([page.waitForNavigation({waitUntil: 'networkidle'}), ojo.click()]);

    //esperar a boton predecir
    let predecir = page.locator('button[name="predict"]');
        //selecciono el boton
    await Promise.all([page.waitForTimeout(5), predecir.click()]);

    //esperar a que cargue el texto
    page.waitForSelector('span[name="predict"]');

    

    check(page, {
    'Predict result': p => p.$('span[name="predict"]').textContent().startsWith("Probabilidad de cáncer")
    });
    } finally {
        page.close();
    }
}