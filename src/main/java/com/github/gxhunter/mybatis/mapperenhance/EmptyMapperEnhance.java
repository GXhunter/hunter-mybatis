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
