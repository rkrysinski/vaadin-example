package com.krycha.vaadin.example.dao.impl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.krycha.vaadin.example.entity.Customer;
import com.krycha.vaadin.example.entity.Measurement;

public class DerbyDAOImplTest {

	private static final Logger LOG = Logger.getLogger(DerbyDAOImplTest.class);
	private static DerbyDAOImpl derbyImpl = new DerbyDAOImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		derbyImpl.wipeOut();

		for (String kpiName : Arrays.asList("kpi1","kpi2","kpi3")) {
			Measurement kpi = new Measurement();
			kpi.setShortName(kpiName);
			kpi.setDescription("Description " + kpiName);
			derbyImpl.addMeasurement(kpi);
		}
		for (String customerName : Arrays.asList("cust1")) {
			Customer cust = new Customer();
			cust.setShortName(customerName);
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

	@Test
	public void testUpdateCustomer() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateIncident() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddIncident() {
		fail("Not yet implemented");
	}

	@Test
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
