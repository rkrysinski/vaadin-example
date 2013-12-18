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

package com.krycha.vaadin.example.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.krycha.vaadin.example.dao.CustomerDAO;
import com.krycha.vaadin.example.dao.IncidentDAO;
import com.krycha.vaadin.example.dao.MeasurementDAO;
import com.krycha.vaadin.example.entity.Customer;
import com.krycha.vaadin.example.entity.Incident;
import com.krycha.vaadin.example.entity.Measurement;

/**
 *
 */
public class TestDAOImpl implements CustomerDAO, IncidentDAO, MeasurementDAO {
	private static final String PERSISTENCE_UNIT_NAME = "testdao";
	private static final EntityManagerFactory EMF_OBJ = Persistence
			.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	public TestDAOImpl() {
	}

	public EntityManager get() {
		return EMF_OBJ.createEntityManager();
	}

	@Override
	public List<Customer> getAllCustomers() {
		return findAll(Customer.class);
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateIncident(Incident incident) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addIncident(Incident incident) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addMeasurement(Measurement kpi) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Measurement> getAllMeasurements() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	private <T> List<T> findAll(Class<T> c) {
		List<T> retList = null;
		EntityManager em = get();
		em.getTransaction().begin();
		try {
			Query q = em.createQuery("select f from " + c.getSimpleName()
					+ " f");
			retList = (List<T>) q.getResultList();
		} finally {
			em.getTransaction().rollback();
			em.close();
		}
		return retList;
	}

}
