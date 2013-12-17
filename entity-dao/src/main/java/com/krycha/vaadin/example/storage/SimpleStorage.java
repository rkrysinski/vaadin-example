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

package com.krycha.vaadin.example.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.krycha.vaadin.example.entity.Item;

/**
 * Singleton class that implements simple Item(s) storage.
 */
public final class SimpleStorage {

	private static final int DEFAULT_COUNT_FOR_ITEM_A = 20;
	private static final int DEFAULT_COUNT_FOR_ITEM_B = 10;
	private Map<String, Item> items;
	private static SimpleStorage instance = null;

	/**
	 * Private C-tor.
	 */
	private SimpleStorage() {
		items = new HashMap<String, Item>();
		Item item = new Item("Item A", DEFAULT_COUNT_FOR_ITEM_A);
		items.put(item.getName(), item);
		item = new Item("Item B", DEFAULT_COUNT_FOR_ITEM_B);
		items.put(item.getName(), item);
	}

	/**
	 * Get the object instance.
	 * 
	 * @return the SimpleStorage instance
	 */
	public static synchronized SimpleStorage getInstance() {
		if (instance == null) {
			instance = new SimpleStorage();
		}
		return instance;
	}

	/**
	 * Get all Items.
	 * 
	 * @return list of all items.
	 */
	public synchronized List<Item> getAllItems() {
		// deep copy
		List<Item> retVal = new ArrayList<Item>();
		for (Item item : items.values()) {
			retVal.add(new Item(item));
		}
		Collections.sort(retVal);
		return retVal;
	}

	/**
	 * Update specific item.
	 * 
	 * @param item
	 *            the item to update
	 */
	public synchronized void updateItem(Item item) {
		for (Item dbItem : items.values()) {
			if (dbItem.equals(item)) {
				dbItem.set(item);
				return;
			}
		}

		/*
		 * If we are here it means no item in the db, nothing to update.
		 */
		// TODO: Possible behavior: throw an exception or simply add item to
		// storage or ignore.
	}

}
