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

import com.github.gxhunter.mybatis.toolkit.HunterUtils;
import org.apache.ibatis.mapping.SqlCommandType;

/**
 * @author 树荫下的天空
 * @date 2020/11/10 19:03
 */
public class SelectById extends AbstractMapperEnhance{
  @Override
  public String getMybatisFragment(Class<?> entityClass){
    return "select " +
      HunterUtils.join(getColumnMap(entityClass,true).values(),",") +
      " from " + getTableName(entityClass) + " where " +
      getIdColumnName(entityClass) + "=#{id}";
  }

  @Override
  public SqlCommandType getCommandType(){
    return SqlCommandType.SELECT;
  }
}
