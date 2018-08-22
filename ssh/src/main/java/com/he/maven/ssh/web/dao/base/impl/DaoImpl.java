package com.he.maven.ssh.web.dao.base.impl;

import com.he.maven.ssh.bean.PageObject;
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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by heyanjing on 2018/8/21 14:37.
 * // HEINFO:2018/8/22 17:29  mysql和oracle 分页时不需要传countSql；sqlserverx需要传 countSql
 * // HEINFO:2018/8/22 17:29  单个对象的方法没有数据时返回null；多个对象的方法没有数据时返回[]
 *
 * @param <T>  实体
 * @param <ID> id
 * @author heyanjing
 */
@Repository
@Transactional(rollbackFor = RuntimeException.class)
@Slf4j
@SuppressWarnings("ALL")
public class DaoImpl<T, ID extends Serializable> implements Dao<T, ID> {
    private Class<T> entityClass;
    private SessionFactory sessionFactory;
    private Integer defaultPageIndex = 0;
    private Integer defaultPageSize = 20;

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

    //*********************************************************getBySql*******************************************************************************************************************************

    @Override
    public T getBySql(String sql) {
        return (T) this.createSqlQuery(sql, this.entityClass).uniqueResult();
    }

    @Override
    public T getBySql(String sql, Object... params) {
        return (T) this.createSqlQuery(sql, this.entityClass, params).uniqueResult();
    }

    @Override
    public T getBySql(String sql, Map<String, ?> params) {
        return (T) this.createSqlQuery(sql, this.entityClass, params).uniqueResult();
    }
    //*********************************************************pageBySql*******************************************************************************************************************************

    @Override
    public PageObject<T> pageBySql(String sql, Integer pageSize, Integer pageIndex) {
        String countSql = this.generateCountSql(sql);
        return this.pageBySql(sql, countSql, pageSize, pageIndex);
    }

    @Override
    public PageObject<T> pageBySql(String sql, String countSql, Integer pageSize, Integer pageIndex) {
        NativeQuery<T> sqlQuery = (NativeQuery<T>) this.createSqlQuery(sql, this.entityClass);
        long count = this.count(countSql);
        List<T> list = (List<T>) this.pageData(sqlQuery, pageSize, pageIndex);
        return new PageObject<>(count, list);
    }

    @Override
    public PageObject<T> pageBySql(String sql, Integer pageSize, Integer pageIndex, Object... params) {
        String countSql = this.generateCountSql(sql);
        return this.pageBySql(sql, countSql, pageSize, pageIndex, params);
    }

    @Override
    public PageObject<T> pageBySql(String sql, String countSql, Integer pageSize, Integer pageIndex, Object... params) {
        NativeQuery<T> sqlQuery = (NativeQuery<T>) this.createSqlQuery(sql, this.entityClass, params);
        long count = this.count(countSql, params);
        List<T> list = (List<T>) this.pageData(sqlQuery, pageSize, pageIndex);
        return new PageObject<>(count, list);
    }

    @Override
    public PageObject<T> pageBySql(String sql, Integer pageSize, Integer pageIndex, Map<String, ?> params) {
        String countSql = this.generateCountSql(sql);
        return this.pageBySql(sql, countSql, pageSize, pageIndex, params);
    }

    @Override
    public PageObject<T> pageBySql(String sql, String countSql, Integer pageSize, Integer pageIndex, Map<String, ?> params) {
        NativeQuery<T> sqlQuery = (NativeQuery<T>) this.createSqlQuery(sql, this.entityClass, params);
        long count = this.count(countSql, params);
        List<T> list = (List<T>) this.pageData(sqlQuery, pageSize, pageIndex);
        return new PageObject<>(count, list);
    }

    private String generateCountSql(String sql) {
        return "select count(*) from (" + sql + ") temp";
    }

    private List<?> pageData(Query<?> sqlQuery, Integer pageSize, Integer pageIndex) {
        if (pageIndex < defaultPageIndex) {
            pageIndex = defaultPageIndex;
        }
        if (pageSize < 1) {
            pageSize = defaultPageSize;
        }
        Integer first;
        if (defaultPageIndex.equals(0)) {
            first = pageIndex * pageSize;
        } else {
            first = (pageIndex - 1) * pageSize;
        }
        return sqlQuery.setFirstResult(first).setMaxResults(pageSize).list();
    }
    //*********************************************************findBySql*******************************************************************************************************************************

    @Override
    public List<T> findBySql(String sql) {
        return (List<T>) this.createSqlQuery(sql, this.entityClass).list();
    }

    @Override
    public List<T> findBySql(String sql, Object... params) {
        return (List<T>) this.createSqlQuery(sql, this.entityClass, params).list();
    }

    @Override
    public List<T> findBySql(String sql, Map<String, ?> params) {
        return (List<T>) this.createSqlQuery(sql, this.entityClass, params).list();
    }

    private Query<?> createSqlQuery(String sql, Class<?> clazz, Object params) {
        return this.createQuery(SqlType.SQL, sql, clazz, params);
    }

    private Query<?> createSqlQuery(String sql, Class<?> clazz) {
        return this.createQuery(SqlType.SQL, sql, clazz, null);
    }

    //*********************************************************getMapBySql*******************************************************************************************************************************

    @Override
    public Map<String, Object> getMapBySql(String sql) {
        return (Map<String, Object>) this.createSqlQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
    }

    @Override
    public Map<String, Object> getMapBySql(String sql, Object... params) {
        return (Map<String, Object>) this.createSqlQuery(sql, params).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
    }

    @Override
    public Map<String, Object> getMapBySql(String sql, Map<String, ?> params) {
        return (Map<String, Object>) this.createSqlQuery(sql, params).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
    }
    //*********************************************************pageMapBySql*******************************************************************************************************************************

    @Override
    public PageObject<Map<String, Object>> pageMapBySql(String sql, Integer pageSize, Integer pageIndex) {
        String countSql = this.generateCountSql(sql);
        return this.pageMapBySql(sql, countSql, pageSize, pageIndex);
    }

    @Override
    public PageObject<Map<String, Object>> pageMapBySql(String sql, String countSql, Integer pageSize, Integer pageIndex) {
        Query<?> sqlQuery = this.createSqlQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        long count = this.count(countSql);
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.pageData(sqlQuery, pageSize, pageIndex);
        return new PageObject<>(count, list);
    }

    @Override
    public PageObject<Map<String, Object>> pageMapBySql(String sql, Integer pageSize, Integer pageIndex, Object... params) {
        String countSql = this.generateCountSql(sql);
        return this.pageMapBySql(sql, countSql, pageSize, pageIndex, params);
    }

    @Override
    public PageObject<Map<String, Object>> pageMapBySql(String sql, String countSql, Integer pageSize, Integer pageIndex, Object... params) {
        Query<?> sqlQuery = this.createSqlQuery(sql, params).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        long count = this.count(countSql, params);
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.pageData(sqlQuery, pageSize, pageIndex);
        return new PageObject<>(count, list);
    }

    @Override
    public PageObject<Map<String, Object>> pageMapBySql(String sql, Integer pageSize, Integer pageIndex, Map<String, ?> params) {
        String countSql = this.generateCountSql(sql);
        return this.pageMapBySql(sql, countSql, pageSize, pageIndex, params);
    }

    @Override
    public PageObject<Map<String, Object>> pageMapBySql(String sql, String countSql, Integer pageSize, Integer pageIndex, Map<String, ?> params) {
        Query<?> sqlQuery = this.createSqlQuery(sql, params).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        long count = this.count(countSql, params);
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.pageData(sqlQuery, pageSize, pageIndex);
        return new PageObject<>(count, list);
    }

    //*********************************************************findMapBySql*******************************************************************************************************************************

    @Override
    public List<Map<String, Object>> findMapBySql(String sql) {
        return (List<Map<String, Object>>) this.createSqlQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Override
    public List<Map<String, Object>> findMapBySql(String sql, Object... params) {
        return (List<Map<String, Object>>) this.createSqlQuery(sql, params).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Override
    public List<Map<String, Object>> findMapBySql(String sql, Map<String, ?> params) {
        return (List<Map<String, Object>>) this.createSqlQuery(sql, params).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }
    //*********************************************************findStringBySql*******************************************************************************************************************************

    @Override
    public List<String> findStringBySql(String sql) {
        return ((NativeQuery<String>) this.createSqlQuery(sql)).list();
    }

    @Override
    public List<String> findStringBySql(String sql, Object... params) {
        return ((NativeQuery<String>) this.createSqlQuery(sql, params)).list();
    }

    @Override
    public List<String> findStringBySql(String sql, Map<String, ?> params) {
        return ((NativeQuery<String>) this.createSqlQuery(sql, params)).list();
    }
    //*********************************************************pageEntityBySql*******************************************************************************************************************************

    @Override
    public <E> PageObject<E> pageEntityBySql(String sql, Class<E> entityClass, Integer pageSize, Integer pageIndex) {
        String countSql = this.generateCountSql(sql);
        return this.pageEntityBySql(sql, countSql, entityClass, pageSize, pageIndex);
    }

    @Override
    public <E> PageObject<E> pageEntityBySql(String sql, String countSql, Class<E> entityClass, Integer pageSize, Integer pageIndex) {
        Query<?> sqlQuery = this.createSqlQuery(sql).addEntity(entityClass);
        long count = this.count(countSql);
        List<E> list = (List<E>) this.pageData(sqlQuery, pageSize, pageIndex);
        return new PageObject<>(count, list);
    }

    @Override
    public <E> PageObject<E> pageEntityBySql(String sql, Class<E> entityClass, Integer pageSize, Integer pageIndex, Object... params) {
        String countSql = this.generateCountSql(sql);
        return this.pageEntityBySql(sql, countSql, entityClass, pageSize, pageIndex, params);
    }

    @Override
    public <E> PageObject<E> pageEntityBySql(String sql, String countSql, Class<E> entityClass, Integer pageSize, Integer pageIndex, Object... params) {
        Query<?> sqlQuery = this.createSqlQuery(sql, params).addEntity(entityClass);
        long count = this.count(countSql, params);
        List<E> list = (List<E>) this.pageData(sqlQuery, pageSize, pageIndex);
        return new PageObject<>(count, list);
    }

    @Override
    public <E> PageObject<E> pageEntityBySql(String sql, Class<E> entityClass, Integer pageSize, Integer pageIndex, Map<String, ?> params) {
        String countSql = this.generateCountSql(sql);
        return this.pageEntityBySql(sql, countSql, entityClass, pageSize, pageIndex, params);
    }

    @Override
    public <E> PageObject<E> pageEntityBySql(String sql, String countSql, Class<E> entityClass, Integer pageSize, Integer pageIndex, Map<String, ?> params) {
        Query<?> sqlQuery = this.createSqlQuery(sql, params).addEntity(entityClass);
        long count = this.count(countSql, params);
        List<E> list = (List<E>) this.pageData(sqlQuery, pageSize, pageIndex);
        return new PageObject<>(count, list);
    }

    //*********************************************************findEntityBySql*******************************************************************************************************************************

    @Override
    public <E> List<E> findEntityBySql(String sql, Class<E> entityClass) {
        return (List<E>) this.createSqlQuery(sql).addEntity(entityClass).list();
    }

    @Override
    public <E> List<E> findEntityBySql(String sql, Class<E> entityClass, Object... params) {
        return (List<E>) this.createSqlQuery(sql, params).addEntity(entityClass).list();
    }

    @Override
    public <E> List<E> findEntityBySql(String sql, Class<E> entityClass, Map<String, ?> params) {
        return (List<E>) this.createSqlQuery(sql, params).addEntity(entityClass).list();
    }

    private NativeQuery<?> createSqlQuery(String sql, Object params) {
        return (NativeQuery<?>) this.createQuery(SqlType.SQL, sql, null, params);
    }

    private NativeQuery<?> createSqlQuery(String sql) {
        return (NativeQuery<?>) this.createQuery(SqlType.SQL, sql, null, null);
    }

    //*********************************************************getByHql*******************************************************************************************************************************

    @Override
    public T getByHql(String hql) {
        return ((Query<T>) this.createHqlQuery(hql, this.entityClass)).uniqueResult();
    }

    @Override
    public T getByHql(String hql, Map<String, ?> params) {
        return ((Query<T>) this.createHqlQuery(hql, this.entityClass, params)).uniqueResult();
    }

    @Override
    public T getByHql(String hql, Object... params) {
        return ((Query<T>) this.createHqlQuery(hql, this.entityClass, params)).uniqueResult();
    }
    //*********************************************************findByHql*******************************************************************************************************************************

    @Override
    public List<T> findByHql(String hql) {
        return ((Query<T>) this.createHqlQuery(hql, this.entityClass)).list();
    }

    @Override
    public List<T> findByHql(String hql, Map<String, ?> params) {
        return ((Query<T>) this.createHqlQuery(hql, this.entityClass, params)).list();
    }

    @Override
    public List<T> findByHql(String hql, Object... params) {
        return ((Query<T>) this.createHqlQuery(hql, this.entityClass, params)).list();
    }

    private Query<?> createHqlQuery(String hql, Class<?> clazz, Object params) {
        return this.createQuery(SqlType.HQL, hql, clazz, params);
    }

    private Query<?> createHqlQuery(String hql, Class<?> clazz) {
        return this.createQuery(SqlType.HQL, hql, clazz, null);
    }
    //*********************************************************createQuery*******************************************************************************************************************************

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
        /**
         * sql标识
         */
        SQL, /**
         * hql标识
         */
        HQL
    }

    //*********************************************************count*******************************************************************************************************************************

    @Override
    public long count(String sql) {
        return ((NativeQuery<BigInteger>) this.createSqlQuery(sql)).uniqueResult().longValue();
    }

    @Override
    public long count(String sql, Map<String, ?> params) {
        return ((NativeQuery<BigInteger>) this.createSqlQuery(sql, params)).uniqueResult().longValue();
    }

    @Override
    public long count(String sql, Object... params) {
        return ((NativeQuery<BigInteger>) this.createSqlQuery(sql, params)).uniqueResult().longValue();
    }
    //*********************************************************executeBySql*******************************************************************************************************************************

    @Override
    public int executeBySql(String sql) {
        return this.createSqlQuery(sql).executeUpdate();
    }

    @Override
    public int executeBySql(String sql, Map<String, ?> params) {
        return this.createSqlQuery(sql, params).executeUpdate();
    }

    @Override
    public int executeBySql(String sql, Object... params) {
        return this.createSqlQuery(sql, params).executeUpdate();
    }

    @Override
    public void temp() {
        //String sql = " delete from product where state=2";
        String sql = "select * from  product  ORDER BY showOrder";

        //String countSql = generateCountSql(sql);
        //long count = this.count(countSql);
        //log.error("{}", count);
        //Integer pageSize = 2;
        //Integer pageIndex = 1;
        //NativeQuery<T> sqlQuery = (NativeQuery<T>) this.createSqlQuery(sql, this.entityClass);
        //List<T> list = pageData(pageSize, pageIndex, sqlQuery);
        //log.info("{}", list);
    }
}
