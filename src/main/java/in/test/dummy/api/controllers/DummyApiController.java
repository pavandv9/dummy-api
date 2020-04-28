package in.test.dummy.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
