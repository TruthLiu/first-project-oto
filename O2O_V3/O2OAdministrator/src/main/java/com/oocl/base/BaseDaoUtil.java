package com.oocl.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseDaoUtil {
	@PersistenceContext(name="punit")
	protected EntityManager em;
}
