package com.oocl.base;

import java.util.List;

public interface BaseDao<T> {
	
	/**
	 * add
	 * @param t
	 * @return
	 */
	public T add(T t);
	
	/**
	 * update
	 * @param t
	 * @return
	 */
	public T update(T t);
	/**
	 * delete by id
	 * @param id
	 * @return
	 */
	public T delete(String id);
	/**
	 * findAll
	 * @return
	 */
	public List<T> findAll();
}
