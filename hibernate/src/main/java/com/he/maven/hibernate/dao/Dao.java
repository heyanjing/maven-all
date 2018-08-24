package com.he.maven.hibernate.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by heyanjing on 2018/8/21 14:37.
 * mysql和oracle支持自动求数据条数
 * sqlserver不支持自动求数据条数，需要用户自己提供查询条数sql
 *
 * @param <T>  实体
 * @param <ID> id
 * @author heyanjing
 */
public interface Dao<T, ID extends Serializable> {

    /**
     * @param id 实体id
     * @return null 或 实体
     */
    T getById(ID id);

    /**
     * @param id 实体id
     * @param entity 实体类
     * @return null 或 实体
     */
    <E> E getEntityById(ID id, Class<E> entity);

    /**
     * 保存实体
     *
     * @param entity 实体
     * @return 实体Id
     */
    ID save(Object entity);

    /**
     * 批量保存实体
     *
     * @param list 实体集合
     * @return 实体对应的id集合
     */
    List<ID> saveForBatch(List<?> list);

    /**
     * 保存或更新实体
     *
     * @param entity 实体
     */
    void saveOrUpdate(Object entity);

    /**
     * 批量保存或更新实体
     *
     * @param list 实体集合
     */
    void saveOrUpdateForBatch(List<?> list);

    /**
     * 更新实体
     *
     * @param entity 实体
     */
    void update(Object entity);

    /**
     * 批量更新实体
     *
     * @param list 实体集合
     */
    void updateForBatch(List<?> list);

    /**
     * 删除实体
     *
     * @param entity 实体
     */
    void delete(Object entity);

    /**
     * 批量删除实体
     *
     * @param list 实体集合
     */
    void deleteForBatch(List<?> list);

    ////*********************************************************getBySql*******************************************************************************************************************************
    //
    //T getBySql(String sql);
    //
    //T getBySql(String sql, Object... params);
    //
    //T getBySql(String sql, Map<String, ?> params);
    //
    ////*********************************************************pageBySql*******************************************************************************************************************************
    //
    //PageObject<T> pageBySql(String sql, Integer pageSize, Integer pageIndex);
    //
    //PageObject<T> pageBySql(String sql, String countSql, Integer pageSize, Integer pageIndex);
    //
    //PageObject<T> pageBySql(String sql, Integer pageSize, Integer pageIndex, Object... params);
    //
    //PageObject<T> pageBySql(String sql, String countSql, Integer pageSize, Integer pageIndex, Object... params);
    //
    //PageObject<T> pageBySql(String sql, Integer pageSize, Integer pageIndex, Map<String, ?> params);
    //
    //PageObject<T> pageBySql(String sql, String countSql, Integer pageSize, Integer pageIndex, Map<String, ?> params);
    ////*********************************************************findBySql*******************************************************************************************************************************
    //
    //List<T> findBySql(String sql);
    //
    //List<T> findBySql(String sql, Object... params);
    //
    //List<T> findBySql(String sql, Map<String, ?> params);
    //
    ////*********************************************************getMapBySql*******************************************************************************************************************************
    //
    //Map<String, Object> getMapBySql(String sql);
    //
    //Map<String, Object> getMapBySql(String sql, Object... params);
    //
    //Map<String, Object> getMapBySql(String sql, Map<String, ?> params);
    ////*********************************************************pageMapBySql*******************************************************************************************************************************
    //
    //PageObject<Map<String, Object>> pageMapBySql(String sql, Integer pageSize, Integer pageIndex);
    //
    //PageObject<Map<String, Object>> pageMapBySql(String sql, String countSql, Integer pageSize, Integer pageIndex);
    //
    //PageObject<Map<String, Object>> pageMapBySql(String sql, Integer pageSize, Integer pageIndex, Object... params);
    //
    //PageObject<Map<String, Object>> pageMapBySql(String sql, String countSql, Integer pageSize, Integer pageIndex, Object... params);
    //
    //PageObject<Map<String, Object>> pageMapBySql(String sql, Integer pageSize, Integer pageIndex, Map<String, ?> params);
    //
    //PageObject<Map<String, Object>> pageMapBySql(String sql, String countSql, Integer pageSize, Integer pageIndex, Map<String, ?> params);
    ////*********************************************************findMapBySql*******************************************************************************************************************************
    //
    //List<Map<String, Object>> findMapBySql(String sql);
    //
    //List<Map<String, Object>> findMapBySql(String sql, Object... params);
    //
    //List<Map<String, Object>> findMapBySql(String sql, Map<String, ?> params);
    ////*********************************************************findStringBySql*******************************************************************************************************************************
    //
    //List<String> findStringBySql(String sql);
    //
    //List<String> findStringBySql(String sql, Object... params);
    //
    //List<String> findStringBySql(String sql, Map<String, ?> params);
    ////*********************************************************pageEntityBySql*******************************************************************************************************************************
    //
    //<E> PageObject<E> pageEntityBySql(String sql, Class<E> entityClass, Integer pageSize, Integer pageIndex);
    //
    //<E> PageObject<E> pageEntityBySql(String sql, String countSql, Class<E> entityClass, Integer pageSize, Integer pageIndex);
    //
    //<E> PageObject<E> pageEntityBySql(String sql, Class<E> entityClass, Integer pageSize, Integer pageIndex, Object... params);
    //
    //<E> PageObject<E> pageEntityBySql(String sql, String countSql, Class<E> entityClass, Integer pageSize, Integer pageIndex, Object... params);
    //
    //<E> PageObject<E> pageEntityBySql(String sql, Class<E> entityClass, Integer pageSize, Integer pageIndex, Map<String, ?> params);
    //
    //<E> PageObject<E> pageEntityBySql(String sql, String countSql, Class<E> entityClass, Integer pageSize, Integer pageIndex, Map<String, ?> params);
    ////*********************************************************findEntityBySql*******************************************************************************************************************************
    //
    //<E> List<E> findEntityBySql(String sql, Class<E> entityClass);
    //
    //<E> List<E> findEntityBySql(String sql, Class<E> entityClass, Object... params);
    //
    //<E> List<E> findEntityBySql(String sql, Class<E> entityClass, Map<String, ?> params);
    //
    ////*********************************************************getByHql*******************************************************************************************************************************
    //
    //T getByHql(String hql);
    //
    //T getByHql(String hql, Map<String, ?> params);
    //
    //T getByHql(String hql, Object... params);
    ////*********************************************************findByHql*******************************************************************************************************************************
    //
    //List<T> findByHql(String hql);
    //
    //List<T> findByHql(String hql, Map<String, ?> params);
    //
    //List<T> findByHql(String hql, Object... params);
    //
    ////*********************************************************count*******************************************************************************************************************************
    //
    //long count(String sql);
    //
    //long count(String sql, Map<String, ?> params);
    //
    //long count(String sql, Object... params);
    //
    ////*********************************************************executeBySql*******************************************************************************************************************************
    //
    //int executeBySql(String sql);
    //
    //int executeBySql(String sql, Map<String, ?> params);
    //
    //int executeBySql(String sql, Object... params);
    //
    //void temp();
}
