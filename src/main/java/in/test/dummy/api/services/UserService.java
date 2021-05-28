package in.test.dummy.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.test.dummy.api.repository.UserRepository;
import in.test.dummy.api.requestModel.Users;
import in.test.dummy.api.responseModel.Error;
import in.test.dummy.api.responseModel.Message;
import in.test.dummy.api.responseModel.Success;

@Service
public class UserService {

	Message message = new Message();
	Success data = new Success();
	Error error = new Error();

	@Autowired
	UserRepository userRepository;

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;

	public ResponseEntity<?> createUser(Users users) {
		users.setId(sequenceGeneratorService.generateSequence(Users.SEQUENCE_NAME));
		Users res = userRepository.save(users);
		return ResponseEntity.ok().body(res);
	}

	public ResponseEntity<?> updateUser(Long id, Users users) {
		Users updatedRequest;
		try {
			updatedRequest = userRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			error.setError("No data found for id " + users.getId());
			return ResponseEntity.badRequest().body(error);
		}
		updatedRequest.setName(users.getName());
		updatedRequest.setEmail(users.getEmail());
		updatedRequest.setMobileNo(users.getMobileNo());
		Users res = userRepository.save(updatedRequest);
		return ResponseEntity.ok().body(res);

	}

	public ResponseEntity<?> getUser(Long id) {
		Users res = null;
		try {
			res = userRepository.findById(id).get();
			data.setData(res);
			return ResponseEntity.ok().body(data);
		} catch (NoSuchElementException e) {
			message.setMessage("No data found for id " + id);
			return ResponseEntity.badRequest().body(message);
		}
	}

	public List<Users> getAlluser() {
		return (List<Users>) userRepository.findAll();
	}

	public ResponseEntity<?> deleteUser(Long id) {
		ResponseEntity<?> user = getUser(id);
		if ((user.getStatusCode().equals(HttpStatus.OK))) {
			userRepository.deleteById(id);
			user = getUser(id);
			if (user.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
				message.setMessage("Successfully deleted user id: " + id);
				return ResponseEntity.ok().body(message);
			} else {
				error.setError("Failed to delete user id: " + id);
				return ResponseEntity.badRequest().body(message);
			}
		} else {
			message.setMessage("User not exist");
			return ResponseEntity.ok().body(message);
		}

	}

}
