package com.github.gxhunter.mybatis.service;

import com.github.gxhunter.mybatis.mapperenhance.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * @author 树荫下的天空
 * @date 2020/11/12 11:08
 */
public abstract class BaseService<T,M extends Mapper<T>> implements IService<T>{
  @Autowired
  protected M mRepository;

  @Override
  public boolean save(T entity){
    return mRepository.insert(entity) > 0;
  }

  @Override
  public boolean updateById(Serializable id,T entity){
    return mRepository.updateById(id,entity) > 0;
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
