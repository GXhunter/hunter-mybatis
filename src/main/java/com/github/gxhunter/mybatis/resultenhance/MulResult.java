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
package com.github.gxhunter.mybatis.resultenhance;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/**
 * @author wanggx
 * @date 2019/7/3 15:28
 * 支持多返回值,底层由{@link ConcurrentHashMap}支持，线程安全
 */
public class MulResult<K extends Enum<K>,V>{
  private Map<K, V> resultMap;

  private MulResult(Map<K, V> resultMap){
    this.resultMap = resultMap;
  }

  /**
   * 获取返回值
   *
   * @param key
   * @return
   */
  public V get(K key){
    return resultMap.get(key);
  }

  /**
   * 遍历所有返回值
   *
   * @param consumer 执行的逻辑回调
   */
  public void foreach(BiConsumer<K, V> consumer){
    resultMap.forEach(consumer);
  }

  public static <K extends Enum<K>,V> Builder<K, V> build(){
    return new Builder<>();
  }

  /**
   * @param keyClass   key的类型
   * @param valueClass value类型
   * @return 建造者对象
   */
  public static <K extends Enum<K>,V> Builder<K, V> build(Class<K> keyClass,Class<V> valueClass){
    return new Builder<>();
  }

  /**
   * 建造者
   *
   * @param <K>
   * @param <V>
   */
  public static final class Builder<K extends Enum<K>,V>{
    private Map<K, V> resultMap;

    Builder(Map<K, V> resultMap){
      this.resultMap = resultMap;
    }

    Builder(){
      this(new ConcurrentHashMap<>());
    }

    /**
     * 生成目标类
     *
     * @return
     */
    public MulResult<K, V> build(){
      return new MulResult<>(resultMap);
    }

    /**
     * @param key
     * @param value
     * @return
     * @throws IllegalArgumentException 多次添加同一个key抛出此异常
     */
    public Builder<K, V> add(K key,V value){
      if(resultMap.get(key) != null){
        throw new IllegalArgumentException("重复添加key：" + key);
      }
      resultMap.put(key,value);
      return this;
    }
  }
}
