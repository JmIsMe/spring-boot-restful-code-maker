package ${projectPath}.service;

import java.util.List;

/**
 * @description :
 * @author : ${author}
 * @date : ${date}
 */
public interface ServiceCommon<T> {
    /**
    * 增加实例值数据库
    * @param t
    */
    void save(T t);

    /**
    * 增加多个实例值数据库
    * @param t
    */
    void save(List<T> t);

    /**
    * 根据主键删除记录
    * @param id
    */
    void deleteById(String id);

    /**
    * 根据多个主键删除记录
    * @param ids
    */
    void deleteByIds(List<String> ids);

    /**
    * 更新记录
    * @param t
    */
    void update(T t);

    /**
    * 更新多条记录
    * @param t
    */
    void update(List<T> t);

    /**
    * 根据主键查找某条记录
    * @param id
    * @return
    */
    T findById(String id);

    /**
    * 根据多个主键查找多条记录
    * @param ids
    * @return
    */
    List<T> findByIds(List<String> ids);

    /**
    * 返回全部记录
    * @return
    */
    List<T> findAll();

    /**
    * 清空表
    */
    void clear();

}
