package org.server.core.entities.address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.server.core.entities.abstracts.VersionableEntity;

/**
 * 
 * @author {@link <a href="mailto:jaykadam90@gmail.com">Dhananjay Kadam</a>}
 *
 */
@Entity
@Table(name = "master_state")
public class States  extends VersionableEntity {
	private static final long serialVersionUID = 1L;
	@Column
	private String name;
	@ManyToOne
	private Country country;
	@Column(columnDefinition = "tinyint(1) default 1")
	private Boolean enabled;

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
