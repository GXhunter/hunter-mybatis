
package com.github.gxhunter.mybatis.mapperenhance;

import com.github.gxhunter.mybatis.toolkit.HunterUtils;
import com.github.gxhunter.mybatis.annotation.Column;
import com.github.gxhunter.mybatis.annotation.TableName;
import org.apache.ibatis.mapping.SqlCommandType;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 树荫下的天空
 * @date 2020/11/10 19:02
 */
public abstract class AbstractMapperEnhance{
  /**
   * 动态生成对应的mybatis脚本片段，注意并不是sql语句，只会在启动时加载一次
   * @param entityClass  实体类
   * @return 模板语法片段
   */
  public abstract String getMybatisFragment(Class<?> entityClass);

  public abstract SqlCommandType getCommandType();

  /**
   * @param entityClass    实体类
   * @param withPrimaryKey 是否包含主键
   * @return java属性名->数据库列名
   */
  protected Map<String, String> getColumnMap(Class entityClass,boolean withPrimaryKey){
    Map<String, String> map = new HashMap<>();
    Arrays.stream(entityClass.getDeclaredFields()).forEach(field -> {
      if(withPrimaryKey || !HunterUtils.isPrimaryKey(field)){
        map.put(field.getName(),getColumnName(field));
      }
    });
    return map;
  }

  /**
   * 获取id列
   *
   * @param entityClass
   * @return
   */
  protected String getIdColumnName(Class entityClass){
    for(Field field : entityClass.getDeclaredFields()){
      if(HunterUtils.isPrimaryKey(field)){
        return getColumnName(field);
      }
    }
    throw new IllegalStateException("未找到id字段，请检查实体" + entityClass + "是否带有id注解");
  }

  protected String getTableName(Class clazz){
    if(clazz.isAnnotationPresent(TableName.class)){
      return ((TableName) clazz.getAnnotation(TableName.class)).value();
    }
    return clazz.getSimpleName();
  }

  protected final String getColumnName(Field field){
    if(field.isAnnotationPresent(Column.class)){
      Column column = field.getAnnotation(Column.class);
      return column.value().isEmpty() ? field.getName() : column.value();
    }else{
      return field.getName();
    }
  }
}
