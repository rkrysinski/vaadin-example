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
import javax.persistence.NoResultException;
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
public class DerbyDAOImpl implements CustomerDAO, IncidentDAO, MeasurementDAO {

	private static final String PERSISTENCE_UNIT_NAME = "derbydao";
	private static final EntityManagerFactory EMF_OBJ = Persistence
			.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	public DerbyDAOImpl() {
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
		store(customer);

	}

	@Override
	public void updateIncident(Incident incident) {
		store(incident);

	}

	@Override
	public void addIncident(Incident incident) {
		store(incident);

	}

	@Override
	public void addMeasurement(Measurement kpi) {
		store(kpi);
	}

	@Override
	public List<Measurement> getAllMeasurements() {
		return findAll(Measurement.class);
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

	private <T> void store(T obj) {
		EntityManager em = get();
		em.getTransaction().begin();
		try {
			em.merge(obj);
		} finally {
			em.getTransaction().commit();
			em.close();
		}
	}

	private <T> void remove(Class<T> c, Object id) {
		EntityManager em = get();
		em.getTransaction().begin();
		try {
			T toRemove = (T) em.find(c, id);
			// System.out.println("remove: " + toRemove.toString());
			em.remove(toRemove);
		} catch (NoResultException e) {
			// fall through, return null
		} finally {
			em.getTransaction().commit();
			em.close();
		}
	}

	public void wipeOut() {
		List<Customer> customers = getAllCustomers();
		for (Customer cust : customers) {
			remove(Customer.class, cust.getShortName());
		}
		for (Measurement kpi : getAllMeasurements()) {
			remove(Measurement.class, kpi.getShortName());
		}
	}
}
