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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.test.dummy.api.requestModel.Users;
import in.test.dummy.api.services.UserService;

@RestController
@RequestMapping("/user")
public class CustomerController {

	@Autowired
	UserService userService;

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody Users users) {
		return userService.createUser(users);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody Users users) {
		return userService.updateUser(id, users);
	}

	@GetMapping("/get/all")
	public List<Users> get() {
		return userService.getAlluser();
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable Long id) {
		return userService.getUser(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return userService.deleteUser(id);
	}

	@GetMapping("/getbyquery")
	public ResponseEntity<?> getQuery(@RequestParam Long id) {
		return userService.getUser(id);
	}

}
