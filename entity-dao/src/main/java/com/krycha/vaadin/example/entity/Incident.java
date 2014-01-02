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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;

/**
 * Incident entity.
 */
@Entity
@Table(uniqueConstraints={
		@UniqueConstraint(columnNames={"CUSTOMER_ID", "MEASUREMENT_ID", "DATE"})
})
public class Incident {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	protected Customer customer;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "MEASUREMENT_ID")
	protected Measurement measurement;

	@NotNull
	@Column(name = "DATE", columnDefinition = "TIMESTAMP")
	protected DateTime date = new DateTime().withTimeAtStartOfDay().withDayOfMonth(1);

	@Min(0)
	protected Integer count = new Integer(0);

	@Version
	private long version;

	@Transient
	public static final String DATE_FORMAT = "MMM yyyy";

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the measurement
	 */
	public Measurement getMeasurement() {
		return measurement;
	}

	/**
	 * @param measurement
	 *            the measurement to set
	 */
	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}

	/**
	 * @return the date
	 */
	public DateTime getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(DateTime date) {
		this.date = date.withTimeAtStartOfDay().withDayOfMonth(1);
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(Integer count) {
		this.count = count;
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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Incident [id=" + id + ", customer=" + customer.getShortName() + ", measurement="
				+ measurement + ", date=" + date + ", count=" + count + ", version="
				+ version + "]";
	}

}
