package service;

import java.util.List;

import javax.annotation.Resource;

import dao.MapperCommon;

/**
 * @description :
 * @author : ${author}
 * @date : ${date}
 */
public class ServiceCommonImpl<T> implements ServiceCommon<T> {

    @Resource
    private MapperCommon mapperCommon;

    public void save(T t) {
    mapperCommon.save(t);
    }

    public void save(List<T> t) {
    mapperCommon.save(t);
    }

    public void deleteById(String id) {
    mapperCommon.deleteById(id);
    }

    public void deleteByIds(List<String> ids) {
    mapperCommon.deleteByIds(ids);
    }

    public void update(T t) {
    mapperCommon.update(t);
    }

    public void update(List<T> t) {
    mapperCommon.update(t);
    }

    public T findById(String id) {
    return (T)mapperCommon.findById(id);
    }

    public List<T> findByIds(List<String> ids) {
    return mapperCommon.findByIds(ids);
    }

    public List<T> findAll() {
    return mapperCommon.findAll();
    }

    public void clear() {
    mapperCommon.clear();
    }
}
