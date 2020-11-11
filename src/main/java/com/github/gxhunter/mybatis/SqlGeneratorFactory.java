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
package com.github.gxhunter.mybatis;

import com.github.gxhunter.mybatis.sqlgenerator.*;
import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 树荫下的天空
 * @date 2020/11/10 19:25
 */
public class SqlGeneratorFactory{
  public static final Map<String, ISqlGenerator> SQL_GENERATOR_MAP = new HashMap<>();
  private static final ISqlGenerator SELECT_BY_ID = new SelectByIdSqlGenerator();
  private static final ISqlGenerator SELECT_ALL = new SelectAllGenerator();
  private static final ISqlGenerator UPDATE_BYID = new UpdateByIdGenerator();
  private static final ISqlGenerator UPDATE_NON_NULL = new UpdateNonNullGenerator();
  private static final ISqlGenerator DELETE_BYID = new DeleteByIdGenerator();
  static {
    try{
//      内置的五个方法
      registerCommonMapper(BaseMapper.class.getMethod("selectById",Serializable.class),SELECT_BY_ID,true);
      registerCommonMapper(BaseMapper.class.getMethod("selectAll"),SELECT_ALL,true);
      registerCommonMapper(BaseMapper.class.getMethod("deleteById", Serializable.class),DELETE_BYID,false);
      registerCommonMapper(BaseMapper.class.getMethod("updateById", Serializable.class, Object.class),UPDATE_BYID,false);
      registerCommonMapper(BaseMapper.class.getMethod("updateNonNullById", Serializable.class, Object.class),UPDATE_NON_NULL,false);
    }catch(NoSuchMethodException e){
      e.printStackTrace();
    }
  }


  public static ISqlGenerator getByMethodName(String name){
    return SQL_GENERATOR_MAP.getOrDefault(name,SELECT_BY_ID);
  }

  /**
   * 注册通用方法
   * @param method 方法，用于做key
   * @param sqlGenerator 对应的生成器
   */
  public static void registerCommonMapper(Method method,ISqlGenerator sqlGenerator,boolean isQuery){
    SQL_GENERATOR_MAP.put(method.toGenericString(),sqlGenerator);
    if(isQuery){
      MapperAnnotationBuilder.needResultMap.add(method.toGenericString());
    }
  }

}
