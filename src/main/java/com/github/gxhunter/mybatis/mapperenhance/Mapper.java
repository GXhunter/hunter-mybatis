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
package com.github.gxhunter.mybatis.mapperenhance;
import com.github.gxhunter.mybatis.annotation.CommonMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @author 树荫下的天空
 * @date 2020/11/10 16:02
 */
@CommonMapper
public interface Mapper<T>{
  /**
   * 通过id查询
   * @param id  主键
   * @return 实体
   */
  T selectById(@Param("id") Serializable id);

  /**
   * 查询所有
   * @return 所有
   */
  List<T> selectAll();

  /**
   * 通过主键删除
   * @param id  主键
   * @return 影响条目
   */
  int deleteById(@Param("id") Serializable id);

  /**
   * 更新不为null的字段
   *
   * @param entity 实体
   * @return 影响条目
   */
  int updateByIdSelective(@Param("entity") T entity);

  /**
   * 更新所有字段，包括null
   *
   * @param entity 实体
   * @return 影响条目
   */
  int updateById(@Param("entity") T entity);

  /**
   * 插入一条数据
   * @param entity 实体
   * @return 影响条目
   */
  int insert(T entity);


  /**
   * 批量插入
   * @param entityList 实体
   * @return 影响条目
   */
  int insertBatch(List<T> entityList);
}
