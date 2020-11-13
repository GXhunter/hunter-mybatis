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
      value.append("#{").append(field).append("}").append(",");
    });
    return String.format("insert into %s(%s) values(%s)",
      getTableName(entityClass),key.deleteCharAt(key.length()-1).toString(),value.deleteCharAt(value.length()-1).toString());
  }

  @Override
  public SqlCommandType getCommandType(){
    return SqlCommandType.INSERT;
  }
}
