package com.oocl.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.oocl.dto.OrderBy;
import com.oocl.dto.QueryParam;

public class QueryCriteriaUtil {
	
	public static <T> TypedQuery<T> search(Class<T> clazz,EntityManager em,QueryParam queryParam){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> criteria = cb.createQuery(clazz);
		Root<T> from = criteria.from(clazz);
		List<Predicate> predicates = new ArrayList<>();
		if(null!=queryParam.getAndParams() && !queryParam.getAndParams().isEmpty()){		//and
			for(String key:queryParam.getAndParams().keySet()){
				Object object = queryParam.getAndParams().get(key);
				Predicate predicate = cb.equal(from.get(key), object);
				predicates.add(predicate);
			}
		}
		if(null!=queryParam.getOrderParams() && !queryParam.getOrderParams().isEmpty()){		//orderby
			for(OrderBy oderBy:queryParam.getOrderParams()){
				if(oderBy.isAsc()){
					criteria.orderBy(cb.asc(from.get(oderBy.getField())));
				}else {
					criteria.orderBy(cb.desc(from.get(oderBy.getField())));
				}
			}
		}
		criteria.where(predicates.toArray(new Predicate[0]));
		TypedQuery<T> query = em.createQuery(criteria);
		if(queryParam.getStartIndex()>0){		//分页
			query.setFirstResult(queryParam.getStartIndex());
		}
		if(queryParam.getMaxCounts()>0){
			query.setMaxResults(queryParam.getMaxCounts());
		}
		return query;
	}
}
