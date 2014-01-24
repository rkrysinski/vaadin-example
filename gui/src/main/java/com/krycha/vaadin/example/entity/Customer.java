/****************************************************************************
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package com.krycha.vaadin.example.entity;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.appengine.api.datastore.Key;

/**
 * Customer entity.
 */
@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = -5814642577816427740L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Key key;

	@Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
	@NotNull
	@Size(min = 3, max = 10)
	protected String shortName;

	@Size(min = 0, max = 300)
	protected String description;

	@Version
	private long version;

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		if (shortName == null) {
			return "";
		}
		return shortName;
	}

	/**
	 * @param shortName
	 *            the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		if (description == null) {
			return "";
		}
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the version
	 */
	public long getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(long version) {
		this.version = version;
	}

	/**
	 * @return the id
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setKey(Key id) {
		this.key = id;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return shortName;
	}

}
