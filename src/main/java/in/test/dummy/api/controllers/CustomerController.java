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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import in.test.dummy.api.requestModel.Users;
import in.test.dummy.api.services.UserService;
import in.test.dummy.api.utils.JsonUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerController.
 */
@RestController
@RequestMapping("/user")
public class CustomerController {

	/** The user service. */
	@Autowired
	UserService userService;
	
	/**
	 * Reset users.
	 */
	@Scheduled(cron = "0 0 12 1 * ?")
	public void resetUsers() {
		JSONArray jsonArray = new JsonUtil().getUsersFromMongo();
		Gson gson = new Gson();
		List<Users> usersList = new ArrayList<>();
		for (Object json : jsonArray) {
			Users users = gson.fromJson(json.toString(), Users.class);
			usersList.add(users);
		}
		deleteAllUsers();
		createMultiUsers(usersList);
	}

	/**
	 * Creates the user.
	 *
	 * @param users the users
	 * @return the response entity
	 */
	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody Users users) {
		return userService.createUser(users);
	}

	/**
	 * Update user.
	 *
	 * @param id the id
	 * @param users the users
	 * @return the response entity
	 */
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody Users users) {
		return userService.updateUser(id, users);
	}

	/**
	 * Gets the.
	 *
	 * @return the list
	 */
	@GetMapping("/get/all")
	public List<Users> get() {
		return userService.getAlluser();
	}
	
	/**
	 * Gets the.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return userService.deleteUser(id);
	}

	/**
	 * Gets the query.
	 *
	 * @param id the id
	 * @return the query
	 */
	@GetMapping("/getbyquery")
	public ResponseEntity<?> getQuery(@RequestParam Long id) {
		return userService.getUser(id);
	}

	/**
	 * Delete all users.
	 */
	private void deleteAllUsers() {
		userService.deleteAllUsers();
	}
	
	/**
	 * Creates the multi users.
	 *
	 * @param users the users
	 */
	private void createMultiUsers(List<Users> users) {
		userService.createMultiUsers(users);
	}
}
