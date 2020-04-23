package in.test.dummy.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import in.test.dummy.api.repository.DummyApiRepository;
import in.test.dummy.api.requestModel.DummyAPiModel;
import in.test.dummy.api.responseModel.Error;
import in.test.dummy.api.responseModel.Message;
import in.test.dummy.api.responseModel.Success;
import in.test.dummy.api.utils.JsonUtil;


@Service
public class DummyApiService {

	Message message = new Message();
	Success data = new Success();
	Error error = new Error();

	@Autowired
	DummyApiRepository repository;
	

	@Scheduled(cron = "0 0 0 * * *")
	public void procesCron() {
		JSONArray jsonArray = new JsonUtil().getUsers();
		Gson gson = new Gson();
		List<DummyAPiModel> list = new ArrayList<>();
		for (Object json : jsonArray) {
			DummyAPiModel request = gson.fromJson(json.toString(), DummyAPiModel.class);
			list.add(request);
		}
		deleteAllData();
		post(list);
	}

	public ResponseEntity<?> post(List<DummyAPiModel> listDummyAPiModel) {
		List<DummyAPiModel> list = new ArrayList<>();
		for (DummyAPiModel request : listDummyAPiModel) {
			DummyAPiModel res = repository.save(request);
			list.add(res);
		}
		return (list != null || !list.isEmpty()) ? ResponseEntity.ok().body(list)
				: ResponseEntity.badRequest().body(list);
	}

	public DummyAPiModel post(DummyAPiModel dummyAPiModel) {
		return repository.save(dummyAPiModel);
	}

	public ResponseEntity<?> put(DummyAPiModel dummyAPiModel) {
		DummyAPiModel updatedRequest;
		try {
			updatedRequest = repository.findById(dummyAPiModel.getUserid()).get();
		} catch (NoSuchElementException e) {
			error.setError("No data found for id " + dummyAPiModel.getUserid());
			return ResponseEntity.badRequest().body(error);
		}
		updatedRequest.setName(dummyAPiModel.getName());
		updatedRequest.setTopic(dummyAPiModel.getTopic());
		updatedRequest.setDescription(dummyAPiModel.getDescription());
		DummyAPiModel res = repository.save(updatedRequest);
		return ResponseEntity.ok().body(res);

	}

	public ResponseEntity<?> get(Long id) {
		DummyAPiModel res = null;
		try {
			res = repository.findById(id).get();
			data.setData(res);
			return ResponseEntity.ok().body(data);
		} catch (NoSuchElementException e) {
			message.setMessage("No data found for id " + id);
			return ResponseEntity.ok().body(message);
		}
	}

	public List<DummyAPiModel> get() {
		return (List<DummyAPiModel>) repository.findAll();
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	private void deleteAllData() {
		repository.deleteAll();
	}
}
