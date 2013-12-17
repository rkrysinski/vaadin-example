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

import java.io.Serializable;

/**
 * Item entity.
 */
public class Item implements Serializable, Comparable<Item> {

	private static final long serialVersionUID = -2075276379669561005L;

	protected String name;
	protected int count;

	/**
	 * C-tor.
	 * 
	 * @param name
	 *            item name
	 * @param count
	 *            number of items available in the shop
	 */
	public Item(String name, int count) {
		if (name == null) {
			throw new IllegalArgumentException("'name' must not be null");
		}
		this.name = name;
		this.count = count;
	}

	/**
	 * C-tor.
	 * 
	 * @param item
	 *            item to create from
	 */
	public Item(Item item) {
		this.name = item.getName();
		this.count = item.getCount();
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(Object item) {
		if (this == item) {
			return true;
		}
		if (item instanceof Item) {
			return name.equals(((Item) item).getName());
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return name + ": " + count;
	}

	/**
	 * Set the current item from the given one.
	 * 
	 * @param item
	 *            the item to get settings
	 */
	public void set(Item item) {
		name = item.getName();
		count = item.getCount();
	}

	/**
	 * Get Item name.
	 * 
	 * @return the item name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the Item name.
	 * 
	 * @param name
	 *            the new Item name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the item count.
	 * 
	 * @return the item count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Set the item count.
	 * 
	 * @param count
	 *            the new item count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	
	/**
	 * {@inheritDoc}
	 */
	public int compareTo(Item o) {
		return name.compareTo(o.getName());
	}
}
