package co.com.scheme.v1.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "persons")
public class Person implements Serializable {

	private static final long serialVersionUID = 8213094534732077567L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "names", length = 50, nullable = true)
	private String names;
	
	@Column(name = "last_names", length = 50, nullable = false)
	private String lastNames;
	
	@Column(name = "secret", nullable = true)
	private String secret;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createAt;
	
	@Column(name = "update_at", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updateAt;
	
	@PrePersist
	public void prePersist() {
		this.secret = UUID.randomUUID() + "-" + (new Date().getTime());
	}

	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getLastNames() {
		return lastNames;
	}

	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", names=" + names + ", lastNames=" + lastNames + ", secret=" + secret
				+ ", createAt=" + createAt + ", updateAt=" + updateAt + "]";
	}
}
