package com.github.gxhunter.mybatis.mapperenhance;

import org.apache.ibatis.mapping.SqlCommandType;

/**
 * @author 树荫下的天空
 * @date 2020/11/11 15:58
 */
public class EmptyMapperEnhance extends AbstractMapperEnhance{
  @Override
  public String getMybatisFragment(Class<?> entityClass){
    return "";
  }

  @Override
  public SqlCommandType getCommandType(){
    return SqlCommandType.UNKNOWN;
  }
}
