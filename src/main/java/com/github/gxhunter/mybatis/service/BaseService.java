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
package com.github.gxhunter.mybatis.service;

import com.github.gxhunter.mybatis.mapperenhance.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @author 树荫下的天空
 * @date 2020/11/12 11:08
 */
public abstract class BaseService<M extends Mapper<T>,T> implements IService<T>{
  @Autowired
  protected M mRepository;

  @Override
  public boolean save(T entity){
    return mRepository.insert(entity) > 0;
  }

  @Override
  public boolean updateById(T entity){
    return mRepository.updateById(entity) > 0;
  }

  @Override
  public boolean removeById(Serializable id){
    return mRepository.deleteById(id) > 0;
  }

  @Override
  public T getById(Serializable id){
    return mRepository.selectById(id);
  }

  @Override
  public List<T> listAll(){
    return mRepository.selectAll();
  }

  @Override
  public int batchSave(List<T> entityList){
    return mRepository.insertBatch(entityList);
  }

}
