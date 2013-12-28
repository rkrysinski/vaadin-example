package com.krycha.vaadin.example.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.krycha.vaadin.example.entity.Customer;
import com.krycha.vaadin.example.entity.Measurement;

public class DerbyDAOImplTest {

	private static final Logger LOG = Logger.getLogger(DerbyDAOImplTest.class);
	private static DerbyDAOImpl derbyImpl = new DerbyDAOImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		derbyImpl.wipeOut();

		for (int i = 0; i < 100; i++) {
			String kpiName = "kpi" + i;
			Measurement kpi = new Measurement();
			kpi.setShortName(kpiName);
			kpi.setDescription("Description " + kpiName);
			derbyImpl.addMeasurement(kpi);

			String customerName = "customer" + i;
			Customer cust = new Customer();
			cust.setShortName(customerName);
			cust.setDescription("Description: " + customerName);
			derbyImpl.updateCustomer(cust);
		}

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllCustomers() {
		List<Customer> customers = derbyImpl.getAllCustomers();
		for (Customer cust : customers) {
			LOG.debug(cust);
		}
	}

	@Ignore
	public void testUpdateCustomer() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testUpdateIncident() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testAddIncident() {
		fail("Not yet implemented");
	}

	@Ignore
	public void testAddMeasurement() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllMeasurements() {
		for (Measurement kpi : derbyImpl.getAllMeasurements()) {
			LOG.debug(kpi);
		}
	}

}
