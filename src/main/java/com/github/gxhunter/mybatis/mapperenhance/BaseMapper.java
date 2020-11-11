package com.github.gxhunter.mybatis.mapperenhance;
import com.github.gxhunter.mybatis.annotation.CommonMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;
import java.util.List;

/**
 * @author 树荫下的天空
 * @date 2020/11/10 16:02
 */
@CommonMapper
public interface BaseMapper<T>{
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
   * @param id     主键
   * @param entity 实体
   * @return 影响条目
   */
  int updateNonNullById(@Param("id") Serializable id,@Param("entity") T entity);

  /**
   * 更新所有字段，包括null
   *
   * @param id     主键
   * @param entity 实体
   * @return 影响条目
   */
  int updateById(@Param("id") Serializable id,@Param("entity") T entity);


}
