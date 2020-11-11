package com.github.gxhunter.mybatis.mapperenhance;

import org.apache.ibatis.mapping.SqlCommandType;

/**
 * @author 树荫下的天空
 * @date 2020/11/11 16:21
 */
public class InsertBatch extends AbstractMapperEnhance{
  @Override
  public String getMybatisFragment(Class<?> entityClass){
    StringBuilder key = new StringBuilder();
    StringBuilder value = new StringBuilder();
    value.append("<foreach collection = 'entityList' item='entity' separator = ','>");
    value.append("(");
    getColumnMap(entityClass,true).forEach((field,col) -> {
      key.append(col).append(",");
      value.append("#{entity.").append(field).append("}").append(",");
    });
    value.deleteCharAt(value.length() - 1).append(")");
    value.append("</foreach>");
    return String.format("<script>insert into %s(%s) values %s </script>",
      getTableName(entityClass),key.deleteCharAt(key.length() - 1).toString(),value.toString());

  }

  @Override
  public SqlCommandType getCommandType(){
    return SqlCommandType.INSERT;
  }
}
