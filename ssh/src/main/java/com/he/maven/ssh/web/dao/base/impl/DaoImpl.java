package com.he.maven.ssh.web.dao.base.impl;

import com.he.maven.ssh.web.dao.base.Dao;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by heyanjing on 2018/8/21 14:37.
 * // HETODO: 2018/8/21 18:30 sql分页，hql分页，sql Map<String,Object>， sql List<Map<String,Object>>
 * // HETODO: 2018/8/21 18:30
 */
@Repository
@Transactional
@Slf4j
public class DaoImpl<T, ID extends Serializable> implements Dao<T, ID> {
    private Class<T> entityClass;
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public DaoImpl() {
        this.entityClass = (Class<T>) getClassGenricType(this.getClass(), 0);
    }

    public static Class<?> getClassGenricType(Class<?> clazz, int index) {
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            log.debug(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        } else {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            if (index < params.length && index >= 0) {
                if (!(params[index] instanceof Class)) {
                    log.debug(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
                    return Object.class;
                } else {
                    return (Class) params[index];
                }
            } else {
                log.debug("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
                return Object.class;
            }
        }
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public T getById(ID id) {
        return this.getCurrentSession().get(this.entityClass, id);
    }

    @Override
    public <E> E getEntityById(ID id, Class<E> entity) {
        return this.getCurrentSession().get(entity, id);
    }

    @Override
    public ID save(Object entity) {
        return (ID) this.getCurrentSession().save(entity);
    }

    @Override
    public List<ID> saveForBatch(List<?> list) {
        List<ID> result = new ArrayList<>();
        for (Object entity : list) {
            result.add(this.save(entity));
        }
        return result;
    }

    @Override
    public void saveOrUpdate(Object entity) {
        this.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void saveOrUpdateForBatch(List<?> list) {
        for (Object t : list) {
            this.saveOrUpdate(t);
        }
    }

    @Override
    public void update(Object entity) {
        this.getCurrentSession().update(entity);
    }

    @Override
    public void updateForBatch(List<?> list) {
        for (Object t : list) {
            this.update(t);
        }
    }

    @Override
    public void delete(Object entity) {
        this.getCurrentSession().delete(entity);
    }

    @Override
    public void deleteForBatch(List<?> list) {
        for (Object t : list) {
            this.delete(t);
        }
    }
    //*********************************************************findMapBySql*******************************************************************************************************************************

    @Override
    public List<Map<String,Object>> findMapBySql(String sql) {
        return (List<Map<String, Object>>) this.createSqlQuery(sql, null, null).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
    }

    @Override
    public List<Map<String,Object>> findMapBySql(String sql, Object... params) {
        return (List<Map<String, Object>>) this.createSqlQuery(sql, null, params).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
    }

    @Override
    public List<Map<String,Object>> findMapBySql(String sql, Map<String, ?> params) {
        return (List<Map<String, Object>>) this.createSqlQuery(sql, null, params).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).getResultList();
    }
    //*********************************************************findBySql*******************************************************************************************************************************

    @Override
    public List<T> findBySql(String sql) {
        return (List<T>) this.createSqlQuery(sql, this.entityClass, null).getResultList();
    }

    @Override
    public List<T> findBySql(String sql, Object... params) {
        return (List<T>) this.createSqlQuery(sql, this.entityClass, params).getResultList();
    }

    @Override
    public List<T> findBySql(String sql, Map<String, ?> params) {
        return (List<T>) this.createSqlQuery(sql, this.entityClass, params).getResultList();
    }

    private NativeQuery<?> createSqlQuery(String sql, Class<?> clazz, Object params) {
        return (NativeQuery<?>) this.createQuery(SqlType.SQL, sql, clazz, params);
    }

    //*********************************************************findEntityBySql*******************************************************************************************************************************
    @Override
    public <E> List<E> findEntityBySql(String sql, Class<E> entityClass) {
        return (List<E>) this.createSqlQuery(sql, null).addEntity(entityClass).getResultList();
    }

    @Override
    public <E> List<E> findEntityBySql(String sql, Class<E> entityClass, Object... params) {
        return (List<E>) this.createSqlQuery(sql, params).addEntity(entityClass).getResultList();
    }

    @Override
    public <E> List<E> findEntityBySql(String sql, Class<E> entityClass, Map<String, ?> params) {
        return (List<E>) this.createSqlQuery(sql, params).addEntity(entityClass).getResultList();
    }

    private NativeQuery<?> createSqlQuery(String sql, Object params) {
        return (NativeQuery<?>) this.createQuery(SqlType.SQL, sql, null, params);
    }

    //*********************************************************findByHql*******************************************************************************************************************************
    @Override
    public List<T> findByHql(String hql) {
        return ((Query<T>) this.createHqlQuery(hql, this.entityClass, null)).getResultList();
    }

    @Override
    public List<T> findByHql(String hql, Map<String, ?> params) {
        return ((Query<T>) this.createHqlQuery(hql, this.entityClass, params)).getResultList();
    }

    @Override
    public List<T> findByHql(String hql, Object... params) {
        return ((Query<T>) this.createHqlQuery(hql, this.entityClass, params)).getResultList();
    }

    private Query<?> createHqlQuery(String hql, Class<?> clazz, Object params) {
        return this.createQuery(SqlType.HQL, hql, clazz, params);
    }

    private Query<?> createQuery(SqlType type, String sql, Class<?> clazz, Object params) {
        Query<?> query;
        if (type.equals(SqlType.SQL)) {
            if (clazz != null) {
                query = this.getCurrentSession().createNativeQuery(sql, clazz);
            } else {
                query = this.getCurrentSession().createNativeQuery(sql);
            }
        } else {
            query = this.getCurrentSession().createQuery(sql, clazz);
        }
        if (params != null) {
            if (params instanceof Map) {
                log.debug("Map型参数");
                Map<String, Object> map = (Map<String, Object>) params;
                for (Map.Entry<String, Object> param : map.entrySet()) {
                    query.setParameter(param.getKey(), param.getValue());
                }
            } else {
                Object[] args = (Object[]) params;
                if (args[0] instanceof List) {
                    log.debug("List型参数");
                    List<Object> list = (List<Object>) args[0];
                    for (int i = 0; i < list.size(); i++) {
                        query.setParameter(i + 1, list.get(i));
                    }
                } else {
                    log.debug("多个对象");
                    for (int i = 0; i < args.length; i++) {
                        query.setParameter(i + 1, args[i]);
                    }
                }
            }
        }
        return query;
    }

    enum SqlType {
        SQL, HQL, JPQL;
    }
}
