package com.kun.hello.dao.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.*;

import com.google.common.base.Strings;

public class MyQuery implements Serializable {
    private static final long serialVersionUID = -1L;

    private static Logger log = LoggerFactory.getLogger(Query.class);

    private EntityManager entityManager;

    /**  */
    private Class clazz;

    /** 查询条件列表 */
    private Root from;

    private List<Predicate> predicates;

    private CriteriaQuery criteriaQuery;

    private CriteriaBuilder criteriaBuilder;

    /** 关联模式 */
    private Map<String, Query> subQuery;

    private Map<String, Query> linkQuery;

    private MyQuery() {
    }

    private MyQuery(Class clazz, EntityManager entityManager) {
        this.clazz = clazz;
        this.entityManager = entityManager;
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
        this.criteriaQuery = criteriaBuilder.createQuery(this.clazz);
        this.from = criteriaQuery.from(this.clazz);
        this.predicates = new ArrayList();
    }

    /** 通过类创建查询条件 */
    public static MyQuery forClass(Class clazz, EntityManager entityManager) {
        return new MyQuery(clazz, entityManager);
    }

    private boolean isNullOrEmpty(Object value) {
        if (value instanceof String) {
            return value == null || "".equals(value);
        }
        return value == null;
    }

    private boolean hasNotFound (String propertyName) {
        return false;
    }

    // equal
    public void eq(String propertyName, Object value) {
        if (hasNotFound(propertyName))
            return;
        if (isNullOrEmpty(value))
            return;
        this.predicates.add(criteriaBuilder.equal(from.get(propertyName), value));
    }

    // not equal
    public void notEq(String propertyName, Object value) {
        if (hasNotFound(propertyName))
            return;
        if (isNullOrEmpty(value)) {
            return;
        }
        this.predicates.add(criteriaBuilder.notEqual(from.get(propertyName), value));
    }

    // like
    public void like(String propertyName, String value) {
        if (hasNotFound(propertyName))
            return;
        if (isNullOrEmpty(value))
            return;
        if (value.indexOf("%") < 0)
            value = "%" + value + "%";
        this.predicates.add(criteriaBuilder.like(from.get(propertyName), value));
    }

    public void in(String propertyName, Collection value) {
        if ((value == null) || (value.size() == 0)) {
            return;
        }
        Iterator iterator = value.iterator();
        CriteriaBuilder.In in = criteriaBuilder.in(from.get(propertyName));
        while (iterator.hasNext()) {
            in.value(iterator.next());
        }
        this.predicates.add(in);
    }

    public void or(List<String> propertyName, String value) {
        if (isNullOrEmpty(value))
            return;
        if ((propertyName == null) || (propertyName.size() == 0))
            return;
        Predicate predicate = criteriaBuilder.or(criteriaBuilder.equal(from.get(propertyName.get(0)), value));
        for (int i = 1; i < propertyName.size(); ++i)
            predicate = criteriaBuilder.or(predicate, criteriaBuilder.equal(from.get(propertyName.get(i)), value));
        this.predicates.add(predicate);
    }

    public void addCriterions(Predicate predicate) {
        this.predicates.add(predicate);
    }

    public CriteriaQuery newCriteriaQuery() {
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return criteriaQuery;
    }

    public List<Predicate> getPredicates() {
        return predicates;
    }
    public void setPredicates(List<Predicate> predicates) {
        this.predicates = predicates;
    }


}
