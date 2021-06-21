package in.test.dummy.api.controllers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import in.test.dummy.api.requestModel.DummyAPiModel;
import in.test.dummy.api.services.DummyApiService;

@RestController
public class DummyApiController {

	@Autowired
	DummyApiService service;

	@PostMapping
	public ResponseEntity<?> post(@RequestBody DummyAPiModel dummyAPiModel) {
		return service.post(dummyAPiModel);
	}

	@PutMapping
	public ResponseEntity<?> put(@RequestBody DummyAPiModel dummyAPiModel) {
		return service.put(dummyAPiModel);
	}

	@GetMapping()
	public List<DummyAPiModel> get() {
		return service.get();
	}

	@PostMapping("/bulk")
	public ResponseEntity<?> post(@RequestBody List<DummyAPiModel> dummyAPiModel) {
		return service.post(dummyAPiModel);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		return service.get(id);
	}

	@GetMapping("/query")
	public ResponseEntity<?> getQuery(@RequestParam Long id) {
		return service.get(id);
	}

	@DeleteMapping("/id/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?>  getImageActa(@PathVariable(name = "id") String id)
			throws IOException, RestClientException, URISyntaxException {
		String RESULTDOSSEP_URL = "https://api.resultadossep.eleccionesgenerales2021.pe/mesas/detalle/";
		String url = String.format("%s%s", RESULTDOSSEP_URL, id);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("user-agent",
				"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.106 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		return ResponseEntity.ok().body(response.getBody());
	}

	
}
