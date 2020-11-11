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

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 树荫下的天空
 * @date 2020/11/10 19:25
 */
public class MapperEnhanceFactory{
  public static final Map<String, AbstractMapperEnhance> SQL_GENERATOR_MAP = new HashMap<>();
  private static final AbstractMapperEnhance EMPTY = new EmptyMapperEnhance();
  static {
    try{
//      内置的五个方法
      registerCommonMapper(
        new SelectById(),
        BaseMapper.class.getMethod("selectById",Serializable.class));

      registerCommonMapper(
        new SelectAll(),
        BaseMapper.class.getMethod("selectAll"));

      registerCommonMapper(
        new DeleteById(),
        BaseMapper.class.getMethod("deleteById", Serializable.class));

      registerCommonMapper(
        new UpdateById(),
        BaseMapper.class.getMethod("updateById", Serializable.class, Object.class));

      registerCommonMapper(
        new UpdateByIdSelective(),
        BaseMapper.class.getMethod("updateByIdSelective", Serializable.class, Object.class));

      registerCommonMapper(
        new Insert()
        ,BaseMapper.class.getMethod("insert", Object.class));

      registerCommonMapper(
        new InsertBatch()
        ,BaseMapper.class.getMethod("insertBatch", List.class));


    }catch(NoSuchMethodException e){
      e.printStackTrace();
    }
  }


  public static AbstractMapperEnhance getByMethodName(String name){
    return SQL_GENERATOR_MAP.getOrDefault(name,EMPTY);
  }

  public static AbstractMapperEnhance getByMethod(Method method){
    return SQL_GENERATOR_MAP.getOrDefault(method.toGenericString(),EMPTY);
  }

  /**
   * 注册通用方法
   * @param method 方法，用于做key
   * @param sqlGenerator 对应的生成器
   */
  public static void registerCommonMapper(AbstractMapperEnhance sqlGenerator,Method method){
    SQL_GENERATOR_MAP.put(method.toGenericString(),sqlGenerator);
  }

}
