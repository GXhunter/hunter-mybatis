/**
 *    Copyright 2009-2020 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
   * @param entity
   * @return
   */
  boolean updateById(T entity);

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
   * @param entityList 实体类
   * @return
   */
  int batchSave(List<T> entityList);
}
