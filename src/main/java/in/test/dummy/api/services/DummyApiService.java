package in.test.dummy.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.test.dummy.api.repository.DummyApiRepository;
import in.test.dummy.api.requestModel.DummyAPiModel;
import in.test.dummy.api.responseModel.Error;
import in.test.dummy.api.responseModel.Message;
import in.test.dummy.api.responseModel.Success;

// TODO: Auto-generated Javadoc
/**
 * The Class DummyApiService.
 */
@Service
public class DummyApiService {

	/** The message. */
	Message message = new Message();
	
	/** The data. */
	Success data = new Success();
	
	/** The error. */
	Error error = new Error();

	/** The repository. */
	@Autowired
	DummyApiRepository repository;

	/**
	 * Post.
	 *
	 * @param listDummyAPiModel the list dummy A pi model
	 * @return the response entity
	 */
	public ResponseEntity<?> post(List<DummyAPiModel> listDummyAPiModel) {
		List<DummyAPiModel> list = new ArrayList<>();
		for (DummyAPiModel request : listDummyAPiModel) {
			DummyAPiModel res = repository.save(request);
			list.add(res);
		}
		return (list != null || !list.isEmpty()) ? ResponseEntity.ok().body(list)
				: ResponseEntity.badRequest().body(list);
	}

	/**
	 * Post.
	 *
	 * @param dummyAPiModel the dummy A pi model
	 * @return the response entity
	 */
	public ResponseEntity<?> post(DummyAPiModel dummyAPiModel) {
		DummyAPiModel res = repository.save(dummyAPiModel);
		return ResponseEntity.ok().body(res);
	}

	/**
	 * Put.
	 *
	 * @param dummyAPiModel the dummy A pi model
	 * @return the response entity
	 */
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

	/**
	 * Gets the.
	 *
	 * @param id the id
	 * @return the response entity
	 */
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

	/**
	 * Gets the.
	 *
	 * @return the list
	 */
	public List<DummyAPiModel> get() {
		return (List<DummyAPiModel>) repository.findAll();
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}

	/**
	 * Delete all users.
	 */
	public void deleteAllUsers() {
		repository.deleteAll();
	}

}
