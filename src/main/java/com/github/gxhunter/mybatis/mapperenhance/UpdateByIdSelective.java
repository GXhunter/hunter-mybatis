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

import com.github.gxhunter.mybatis.resultenhance.IdResultEnum;
import com.github.gxhunter.mybatis.resultenhance.MulResult;
import com.github.gxhunter.mybatis.toolkit.HunterUtils;
import org.apache.ibatis.mapping.SqlCommandType;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author 树荫下的天空
 * @date 2020/11/10 20:17
 * 只修改非 null字段
 */
public class UpdateByIdSelective extends AbstractMapperEnhance{
  @Override
  public String getMybatisFragment(Class<?> entityClass){
    List<MulResult<IdResultEnum, String>> idColMap = HunterUtils.getIdColMap(entityClass);
    if(CollectionUtils.isEmpty(idColMap)){
      throw new IllegalStateException("没有找到主键字段");
    }
    StringBuilder sb = new StringBuilder("<script>");
    sb.append("update ");
    sb.append(getTableName(entityClass))
      .append(" <set> ");
    getColumnMap(entityClass,false).forEach((property,column) -> {
      sb.append("<if test='").append("entity.").append(property).append("!=null").append("'>");
      sb.append(column).append("=").append("#{entity.").append(property).append("}").append(",");
      sb.append("</if>");
    });
    sb.append("</set>");
    sb.append(" <where> ");
    idColMap.forEach(idCol -> {
      sb.append(" and ").append(idCol.get(IdResultEnum.KEY_COLUMN)).append("= #{entity.")
        .append(idCol.get(IdResultEnum.KEY_PROPERTY)).append("}");
    });
    sb.append("</where>");
    sb.append("</script>");
    return sb.toString();
  }

  @Override
  public SqlCommandType getCommandType(){
    return SqlCommandType.UPDATE;
  }
}
