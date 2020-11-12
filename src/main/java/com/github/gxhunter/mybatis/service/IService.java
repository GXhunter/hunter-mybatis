package com.github.gxhunter.mybatis.service;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T> 实体类
 * @author 树荫下的天空
 */
public interface IService<T>{
  /**
   * 插入数据
   *
   * @param entity
   * @return
   */
  boolean save(T entity);

  /**
   * 通过id修改
   *
   * @param id
   * @param entity
   * @return
   */
  boolean updateById(Serializable id,T entity);

  /**
   * 通过id删除
   *
   * @param id
   * @return
   */
  boolean removeById(Serializable id);

  /**
   * 通过id获取
   *
   * @param id
   * @return
   */
  T getById(Serializable id);

  /**
   * 获取所有
   *
   * @return
   */
  List<T> listAll();

  /**
   * 批量保存
   *
   * @param entityList
   * @return
   */
  int batchSave(List<T> entityList);
}
