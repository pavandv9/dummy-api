package in.test.dummy.api.requestModel;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

// TODO: Auto-generated Javadoc
/**
 * The Class DummyAPiModel.
 */
@Entity
public class DummyAPiModel {

	/** The id. */
	@Id
	@GeneratedValue
	private long id;
	
	/** The userid. */
	private long userid;
	
	/** The name. */
	private String name;
	
	/** The topic. */
	private String topic;
	
	/** The description. */
	private String description;
	
	/** The created at. */
	@CreationTimestamp
	private LocalDateTime created_at;
	
	/** The updated at. */
	@UpdateTimestamp
	private LocalDateTime updated_at;

	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public long getUserid() {
		return userid;
	}

	/**
	 * Sets the userid.
	 *
	 * @param userid the new userid
	 */
	public void setUserid(long userid) {
		this.userid = userid;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the topic.
	 *
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * Sets the topic.
	 *
	 * @param topic the new topic
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public LocalDateTime getCreated_at() {
		return created_at;
	}

	/**
	 * Sets the created at.
	 *
	 * @param created_at the new created at
	 */
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	/**
	 * Gets the updated at.
	 *
	 * @return the updated at
	 */
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	/**
	 * Sets the updated at.
	 *
	 * @param updated_at the new updated at
	 */
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
