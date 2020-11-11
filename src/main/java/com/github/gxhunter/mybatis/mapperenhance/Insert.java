package com.github.gxhunter.mybatis.mapperenhance;

import org.apache.ibatis.mapping.SqlCommandType;

/**
 * @author 树荫下的天空
 * @date 2020/11/11 16:21
 */
public class Insert extends AbstractMapperEnhance{
  @Override
  public String getMybatisFragment(Class<?> entityClass){
    StringBuilder key = new StringBuilder();
    StringBuilder value = new StringBuilder();
    getColumnMap(entityClass,true).forEach((field,col)->{
      key.append(col).append(",");
      value.append("#{entity.").append(field).append("}").append(",");
    });
    return String.format("insert into %s(%s) values(%s)",
      getTableName(entityClass),key.deleteCharAt(key.length()-1).toString(),value.deleteCharAt(value.length()-1).toString());
  }

  @Override
  public SqlCommandType getCommandType(){
    return SqlCommandType.INSERT;
  }
}
