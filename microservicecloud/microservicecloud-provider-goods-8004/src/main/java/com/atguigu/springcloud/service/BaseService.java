package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.entity.BaseEntityAndDelete;
import com.atguigu.springcloud.entities.entity.EntityStatus;
import com.atguigu.springcloud.util.IdGenetatorUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseService
 * @Description TODO 通用的业务查询
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/10/18
 * @Version 1.0.0
 **/
public class BaseService<T extends BaseEntityAndDelete> {
    @Value(value = "${file.basePath}")
    private String baseUrl = "";
    protected final BaseMapper<T> mapper;

    public BaseService(BaseMapper<T> mapper){
        this.mapper = mapper;
    }

    public T add(T e){
        long snowFlakeId = IdGenetatorUtil.getSnowFlakeId();
        long time = Timestamp.valueOf(LocalDateTime.now()).getTime();
        e.setId(snowFlakeId);
        e.setInsertTime(time);
        e.setUpdateTime(time);
        e.setDeleteFlag(EntityStatus.EXIST.getFlag());
        int insert = mapper.insert(e);
        return insert>0?e:null;
    }

    /**
     * （模拟删除，可恢复）
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteById(int id){
        T entityAndDelete = getById(id);
        entityAndDelete.setDeleteFlag(EntityStatus.DELETE.getFlag());
        long time = Timestamp.valueOf(LocalDateTime.now()).getTime();
        entityAndDelete.setUpdateTime(time);
        int i = mapper.updateById(entityAndDelete);
        return i > 0;
    }

    /**
     * 删除数据库中delete_flag为1的数据(真实删除)
     * @return
     */
    @Transactional
    public boolean deleteAll(){
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        queryWrapper.eq("delete_flag", EntityStatus.DELETE.getFlag());
        int delete = mapper.delete(queryWrapper);
        return delete>0;
    }

    @Transactional
    public boolean update(T t){
        long time = Timestamp.valueOf(LocalDateTime.now()).getTime();
        t.setUpdateTime(time);
        UpdateWrapper<T> updateWrapper = new UpdateWrapper<T>();
        HashMap<String, Object> map = new HashMap<>(8);
        map.put("id", t.getId());
        map.put("delete_flag", EntityStatus.EXIST.getFlag());
        updateWrapper.allEq(map);
        int update = mapper.update(t, updateWrapper);
        return update>0;
    }

    public <T> T getById(long id){
        return mapper.selectById(id)!=null?(T) mapper.selectById(id):null;
    }

    public List<T> getAll(){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_flag", EntityStatus.EXIST.getFlag());
        return mapper.selectList(queryWrapper);
    }

    public List<T> getByMap(Map<String,Object> query){
        if (!query.containsKey("delete_flag")){
            query.put("delete_flag", EntityStatus.EXIST.getFlag());
        }
        return mapper.selectByMap(query);
    }



}
