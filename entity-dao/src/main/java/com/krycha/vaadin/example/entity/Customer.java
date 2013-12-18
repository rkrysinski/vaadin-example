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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 * Customer entity.
 */
@Entity
public class Customer {

	@Id
	@Column(name = "CUSTOMER_ID")
	protected String shortName;

	protected String description;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval=true)
	protected List<Incident> incidents =  new ArrayList<Incident>();

	@Version
	private long version;

}