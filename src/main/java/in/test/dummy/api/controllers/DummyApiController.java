package in.test.dummy.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import in.test.dummy.api.requestModel.DummyAPiModel;
import in.test.dummy.api.services.DummyApiService;
import in.test.dummy.api.utils.JsonUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class DummyApiController.
 */
@RestController
public class DummyApiController {

	/** The service. */
	@Autowired
	DummyApiService service;
	
	/**
	 * Proces cron.
	 */
	@Scheduled(cron = "0 0 12 1 * ?")
	public void procesCron() {
		JSONArray jsonArray = new JsonUtil().getUsers();
		Gson gson = new Gson();
		List<DummyAPiModel> list = new ArrayList<>();
		for (Object json : jsonArray) {
			DummyAPiModel request = gson.fromJson(json.toString(), DummyAPiModel.class);
			list.add(request);
		}
		service.deleteAllUsers();
		service.post(list);
	}

	/**
	 * Post.
	 *
	 * @param dummyAPiModel the dummy A pi model
	 * @return the response entity
	 */
	@PostMapping
	public ResponseEntity<?> post(@RequestBody DummyAPiModel dummyAPiModel) {
		return service.post(dummyAPiModel);
	}

	/**
	 * Put.
	 *
	 * @param dummyAPiModel the dummy A pi model
	 * @return the response entity
	 */
	@PutMapping
	public ResponseEntity<?> put(@RequestBody DummyAPiModel dummyAPiModel) {
		return service.put(dummyAPiModel);
	}

	/**
	 * Gets the.
	 *
	 * @return the list
	 */
	@GetMapping()
	public List<DummyAPiModel> get() {
		return service.get();
	}

	/**
	 * Post.
	 *
	 * @param dummyAPiModel the dummy A pi model
	 * @return the response entity
	 */
	@PostMapping("/bulk")
	public ResponseEntity<?> post(@RequestBody List<DummyAPiModel> dummyAPiModel) {
		return service.post(dummyAPiModel);
	}

	/**
	 * Gets the.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/id/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		return service.get(id);
	}

	/**
	 * Gets the query.
	 *
	 * @param id the id
	 * @return the query
	 */
	@GetMapping("/query")
	public ResponseEntity<?> getQuery(@RequestParam Long id) {
		return service.get(id);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@DeleteMapping("/id/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
}
