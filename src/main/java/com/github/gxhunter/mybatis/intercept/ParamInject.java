package com.github.gxhunter.mybatis.intercept;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.function.Consumer;

/**
 * @author 树荫下的天空
 * @date 2020/11/12 16:31
 * 全局参数注入，注意  只有带@Param注解参数的mapper或无参mapper才能注入
 */
@Intercepts({
  @Signature(type = Executor.class, method = "update", args = {MappedStatement.class,Object.class}),
  @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class,CacheKey.class,BoundSql.class}),
  @Signature(type = Executor.class, method = "query", args = {MappedStatement.class,Object.class,RowBounds.class,ResultHandler.class}),
  @Signature(type = Executor.class, method = "queryCursor", args = {MappedStatement.class,Object.class,RowBounds.class}),
})
public class ParamInject implements Interceptor{
  private final Consumer<MapperMethod.ParamMap> inject;

  public ParamInject(Consumer<MapperMethod.ParamMap> inject){
    this.inject = inject;
  }

  @Override
  public Object intercept(Invocation invocation) throws Throwable{
    Object arg = invocation.getArgs()[1];
    if(arg == null){
      arg = new MapperMethod.ParamMap<>();
    }else if(arg instanceof MapperMethod.ParamMap){
      inject.accept((MapperMethod.ParamMap) arg);
    }
    Object[] args = invocation.getArgs();
    args[1] = arg;
    return invocation.proceed();
  }
}
