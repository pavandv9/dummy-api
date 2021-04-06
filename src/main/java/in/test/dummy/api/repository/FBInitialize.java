package in.test.dummy.api.repository;

import java.io.FileInputStream;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import in.test.dummy.api.requestModel.DummyAPiModel;

@Service
public class FBInitialize implements CrudRepository<DummyAPiModel, Long> {

	@PostConstruct
	public void initialize() {

		try {
			FileInputStream serviceAccount = new FileInputStream("./src/main/resources/serviceaccount.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://dummy-api-db-308413-default-rtdb.firebaseio.com").build();
			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public <S extends DummyAPiModel> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends DummyAPiModel> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<DummyAPiModel> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<DummyAPiModel> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<DummyAPiModel> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(DummyAPiModel entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends DummyAPiModel> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

}
