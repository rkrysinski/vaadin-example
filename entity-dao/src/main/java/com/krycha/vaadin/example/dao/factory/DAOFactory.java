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

package com.krycha.vaadin.example.dao.factory;

import com.krycha.vaadin.example.dao.CustomerDAO;
import com.krycha.vaadin.example.dao.IncidentDAO;
import com.krycha.vaadin.example.dao.MeasurementDAO;

/**
 * Abstract class DAOFactory that provides DAO for Entities.
 */
public abstract class DAOFactory {

	/**
	 * Type of DAO Factory.
	 */
	public enum Type {
		DERBY, MY_SQL, ORACLE,
	}

	/**
	 * Get the CustomerDAO implemented by specific factory.
	 *
	 * @return the CustomerDAO
	 */
	public abstract CustomerDAO getCustomerDAO();

	/**
	 * Get the IncidentDAO implemented by specific factory.
	 *
	 * @return the IncidentDAO
	 */
	public abstract IncidentDAO getIncidentDAO();

	/**
	 * Get the MeasurementDAO implemented by specific factory.
	 *
	 * @return the MeasurementDAO
	 */
	public abstract MeasurementDAO getMeasurementDAO();

	/**
	 * Get the default DAO factory.
	 *
	 * @return default DAO factory (simple storage)
	 */
	public static DAOFactory getDefaultFactory() {
		return getDAOFactory(Type.DERBY);
	}

	/**
	 * Get the DAO factory.
	 *
	 * @param type
	 *            type of the DAO
	 * @return specific DAO factory
	 */
	public static DAOFactory getDAOFactory(Type type) {
		switch (type) {
		case DERBY:
			return new DbDAOFactory();
		default:
			throw new IllegalArgumentException(type + " not implemented yet!");
		}
	}
}
