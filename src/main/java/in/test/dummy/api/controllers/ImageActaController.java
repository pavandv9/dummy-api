package in.test.dummy.api.controllers;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import io.github.bonigarcia.wdm.WebDriverManager;

@RestController
public class ImageActaController {

	private static final String RESULTDOSSEP_URL = "http://api.resultadossep.eleccionesgenerales2021.pe/mesas/detalle/";

	private WebDriver driver;

	@Autowired
	public ImageActaController() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
	}

	@GetMapping("/imageacta/{id}")
	public ResponseEntity<?> getImageActa(@PathVariable(name = "id") String id)
			throws IOException, RestClientException, URISyntaxException {
		String url = String.format("%s%s", RESULTDOSSEP_URL, id);
		driver.get(url);
		String jsonString = driver.findElement(By.tagName("body")).getText();
		String imageActa;
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			imageActa = jsonObject.getJSONObject("procesos").getJSONObject("generalPre").get("imageActa").toString();
		} catch (Exception e) {
			imageActa = "Image acta not found";
		}
		return ResponseEntity.ok().body(new JSONObject().put("imagActUrl", imageActa).toString());
	}

}
