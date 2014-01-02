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
import com.krycha.vaadin.example.dao.impl.DbDAOImpl;

/**
 *
 */
public class DbDAOFactory extends DAOFactory {

	private DbDAOImpl daoImpl = new DbDAOImpl();

	/* (non-Javadoc)
	 * @see com.krycha.vaadin.example.dao.factory.DAOFactory#getCustomerDAO()
	 */
	public CustomerDAO getCustomerDAO() {
		return daoImpl;
	}

	/* (non-Javadoc)
	 * @see com.krycha.vaadin.example.dao.factory.DAOFactory#getIncidentDAO()
	 */
	public IncidentDAO getIncidentDAO() {
		return daoImpl;
	}

	/* (non-Javadoc)
	 * @see com.krycha.vaadin.example.dao.factory.DAOFactory#getMeasurementDAO()
	 */
	public MeasurementDAO getMeasurementDAO() {
		return daoImpl;
	}

}
