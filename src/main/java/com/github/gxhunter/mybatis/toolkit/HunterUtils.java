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
package com.github.gxhunter.mybatis.toolkit;

import com.github.gxhunter.mybatis.annotation.Column;
import com.github.gxhunter.mybatis.annotation.CommonMapper;
import com.github.gxhunter.mybatis.mapperenhance.Mapper;
import com.github.gxhunter.mybatis.resultenhance.IdResultEnum;
import com.github.gxhunter.mybatis.resultenhance.MulResult;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author 树荫下的天空
 * @date 2020/11/10 19:15
 */
public class HunterUtils{

  public static boolean isPrimaryKey(Field field){
    if(field.isAnnotationPresent(Column.class)){
      return field.getAnnotation(Column.class).id();
    }
    return false;
  }

  /**
   * 通过通用mapper获取泛型实体类 类型
   * @param mapperClass
   * @return
   */
  public static Class getEntityClass(Class<Mapper> mapperClass){
    Type entityType;
    Type superClass = mapperClass.getGenericInterfaces()[0];
    if (superClass instanceof Class<?>) {
      entityType = Object.class;
    } else {
      entityType = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }
    return (Class) entityType;
  }

  public static String join(Collection<String> join,String split){
    return join.stream().reduce((a,b) -> a + split + b).orElse(null);
  }

  public static String join(String[] join,String split){
    return Arrays.stream(join).reduce((a,b) -> a + split + b).orElse(null);
  }
  public static final String getColumnName(Field field){
    if(field.isAnnotationPresent(Column.class)){
      Column column = field.getAnnotation(Column.class);
      return column.value().isEmpty() ? field.getName() : column.value();
    }else{
      return field.getName();
    }
  }

  public static MulResult<IdResultEnum, String> getIdColMap(Class entityClass){
      for(Field field : entityClass.getDeclaredFields()){
        if(isPrimaryKey(field)){
          return MulResult.build(IdResultEnum.class,String.class)
            .add(IdResultEnum.KEY_PROPERTY,field.getName())
            .add(IdResultEnum.KEY_COLUMN,getColumnName(field)).build()
          ;
        }
      }
      throw new IllegalStateException("未找到id字段，请检查实体" + entityClass + "是否带有id注解");
  }


  public static boolean isCommonMapperMethod(Method method){
    if(method.getDeclaringClass().isAnnotationPresent(CommonMapper.class)){
      return true;
    }
    return false;
  }

  public static Field getIdField(Class entityClass){
    for(Field field : entityClass.getDeclaredFields()){
      if(isPrimaryKey(field)){
        return field;
      }
    }
    return null;
  }
}
