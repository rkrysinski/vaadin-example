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

import com.krycha.vaadin.example.dao.ItemDAO;
import com.krycha.vaadin.example.entity.Item;
import com.krycha.vaadin.example.storage.SimpleStorage;

/**
 * Simple Storage implementation of ItemDAO.
 */
public class SimpleStorageItemDAOImpl implements ItemDAO {

	private SimpleStorage storage = SimpleStorage.getInstance();

	/**
	 * {@inheritDoc}
	 */
	public List<Item> getAllItems() {
		return storage.getAllItems();
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateItem(Item item) {
		storage.updateItem(item);
	}

}
