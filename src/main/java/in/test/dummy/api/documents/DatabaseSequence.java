package in.test.dummy.api.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// TODO: Auto-generated Javadoc
/**
 * The Class DatabaseSequence.
 */
@Document(collection = "database_sequences")
public class DatabaseSequence {

	/** The id. */
	@Id
    private String id;
    
    /** The seq. */
    private long seq;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the seq.
	 *
	 * @return the seq
	 */
	public long getSeq() {
		return seq;
	}

	/**
	 * Sets the seq.
	 *
	 * @param seq the new seq
	 */
	public void setSeq(long seq) {
		this.seq = seq;
	}
}
