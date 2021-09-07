package in.test.dummy.api.services;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import in.test.dummy.api.documents.DatabaseSequence;

// TODO: Auto-generated Javadoc
/**
 * The Class SequenceGeneratorService.
 */
@Service
public class SequenceGeneratorService {

	/** The mongo operations. */
	@Autowired
	private MongoOperations mongoOperations;

	/**
	 * Generate sequence.
	 *
	 * @param seqName the seq name
	 * @return the long
	 */
	public long generateSequence(String seqName) {
		DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
				new Update().inc("seq", 1), options().returnNew(true).upsert(true), DatabaseSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;

	}
}
