package in.test.dummy.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import in.test.dummy.api.repository.DummyApiRepository;
import in.test.dummy.api.requestModel.DummyAPiModel;
import in.test.dummy.api.responseModel.Error;
import in.test.dummy.api.responseModel.Message;
import in.test.dummy.api.responseModel.Success;

@Service
public class DummyApiService {

	Message message = new Message();
	Success data = new Success();
	Error error = new Error();

	@Autowired
	DummyApiRepository repository;

	@Scheduled(cron = "0 0 23 * * *")
	public void procesCron() {}
	
	public DummyAPiModel post(DummyAPiModel dummyAPiModel) {
		return repository.save(dummyAPiModel);
	}

	public ResponseEntity<?> put(DummyAPiModel dummyAPiModel) {
		DummyAPiModel updatedRequest;
		try {
			updatedRequest = repository.findById(dummyAPiModel.getId()).get();
		} catch (NoSuchElementException e) {
			error.setError("No data found for id " + dummyAPiModel.getId());
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
}
