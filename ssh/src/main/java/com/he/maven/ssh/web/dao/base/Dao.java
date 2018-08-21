package com.he.maven.ssh.web.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by heyanjing on 2018/8/21 14:37.
 */
public interface Dao<T, ID extends Serializable> {

    T getById(ID id);

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
     * @param list
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


    //*********************************************************findMapBySql*******************************************************************************************************************************

    List<Map<String, Object>> findMapBySql(String sql);

    List<Map<String, Object>> findMapBySql(String sql, Object... params);

    List<Map<String, Object>> findMapBySql(String sql, Map<String, ?> params);

    //*********************************************************findBySql*******************************************************************************************************************************
    List<T> findBySql(String sql);

    List<T> findBySql(String sql, Object... params);

    List<T> findBySql(String sql, Map<String, ?> params);

    //*********************************************************findEntityBySql*******************************************************************************************************************************

    <E> List<E> findEntityBySql(String sql, Class<E> entityClass);

    <E> List<E> findEntityBySql(String sql, Class<E> entityClass, Object... params);

    <E> List<E> findEntityBySql(String sql, Class<E> entityClass, Map<String, ?> params);

    //*********************************************************findByHql*******************************************************************************************************************************
    List<T> findByHql(String hql);

    List<T> findByHql(String hql, Map<String, ?> params);

    List<T> findByHql(String hql, Object... params);
}
