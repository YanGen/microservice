package com.muhuan.springcloud.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.muhuan.api.bean.entity.BaseEntityAndDelete;
import com.muhuan.api.bean.entity.EntityStatus;
import com.muhuan.api.bean.file.FileSuffixConstant;
import com.muhuan.api.bean.file.FileUploadStandard;
import com.muhuan.api.util.StringUtil;
import com.muhuan.select.build.config.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @ClassName BaseService
 * @Description TODO 继承该类并覆盖方法实现即可
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/10/22
 * @Version 1.0.0
 **/
@RequiredArgsConstructor
public class BaseService<T extends BaseEntityAndDelete> {

    //操作mysql的mapper
    protected final BaseMapper<T> mapper;
    /**
     * 添加数据
     * @param e
     * @return
     */
    public T  add(T e){
        long time = Timestamp.valueOf(LocalDateTime.now()).getTime();
        e.setInsertTime(time);
        e.setUpdateTime(time);
        e.setDeleteFlag(EntityStatus.EXIST.getFlag());
        int insert = mapper.insert(e);
        return insert>0?e:null;
    }

    /**
     * （模拟删除，可恢复，包含级联删除）
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteById(int id){
        //1.如果虚表不存在，创建虚表实例
        //2.获取当前id实例
        T entityAndDelete = getById(id);
        if (entityAndDelete == null) return false;
        //3.查看该实例的是否存在子表数据
        List<Class<T>> foreignKeys = entityAndDelete.getForeignKeys();
        if (foreignKeys != null && foreignKeys.size() > 0) {
            //4.1存在子表，则动态调用子表删除子表中数据
            for (Class<T> foreignKey : foreignKeys) {
                //4.1.1动态获取业务类（利用spring的IOC）
                String beanLowerCase = StringUtil.toFirstLowerCase(foreignKey.getSimpleName());
                String serviceBeanName = beanLowerCase + "Service";
                BaseService service = (BaseService) BeanUtil.getBean(serviceBeanName);
                if (service == null) {
                    System.out.println("不存在该业务 " + serviceBeanName + "实例！");
                    continue;
                }
                HashMap<String, Object> query = new HashMap<>(8);
                String underLine = StringUtil.toUnderLine(entityAndDelete.getClass().getSimpleName());
                System.out.println("->underline:" + underLine);
                query.put(underLine + "_id",id);
                //4.1.2通过外键查询记录是否存在
                List<T> list = service.getByMap(query);
                if (list != null && list.size()>0){
                    System.out.println("size:" + list.size());
                    for (T t : list) {
                        int pkid = (int) t.getId();
                        //4.1.3删除子表中存在该父表的主键的外键记录
                        service.deleteById(pkid);
                    }
                }
            }
        }
        //删除本身
        entityAndDelete.setDeleteFlag(EntityStatus.DELETE.getFlag());
        return update(entityAndDelete);

    }

    /**
     * 删除多条数据（模拟删除，可恢复，不删除子表）
     * @param ts
     * @return
     */
    @Transactional
    public boolean deleteByList(List<T> ts){
        if (ts == null || ts.size() == 0) return true;
        long time = Timestamp.valueOf(LocalDateTime.now()).getTime();
        AtomicBoolean updateFlag = new AtomicBoolean(true);
        ts.stream().forEach(t->{
            t.setUpdateTime(time);
            t.setDeleteFlag(EntityStatus.DELETE.getFlag());
            updateFlag.set(updateFlag.get() && mapper.updateById(t) > 0);
        });
        return updateFlag.get();
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

    /**
     * 单个更新数据
     * @param t
     * @return
     */
    @Transactional
    public boolean update(T t){
        long time = Timestamp.valueOf(LocalDateTime.now()).getTime();
        t.setUpdateTime(time);
        int update = mapper.updateById(t);
        return update>0;
    }

    /**
     * 批量更新
     * @param ts
     * @return
     */
    @Transactional
    public boolean update(List<T> ts){
        long time = Timestamp.valueOf(LocalDateTime.now()).getTime();
        AtomicBoolean flag = new AtomicBoolean(true);
        ts.stream().forEach(t->{
            t.setUpdateTime(time);
            boolean b = flag.compareAndSet(true, mapper.updateById(t) > 0);
            if (!b) throw new RuntimeException("删除失败！");
        });
        return flag.get();
    }

    /**
     * 根据id查询单条数据
     * @param id
     * @return
     */
    public T getById(long id){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_flag", EntityStatus.EXIST.getFlag());
        queryWrapper.eq("id",id);
        return mapper.selectOne(queryWrapper);
    }

    /**
     * 查询所有数据
     * @return
     */
    public List<T> getAll(){
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("delete_flag", EntityStatus.EXIST.getFlag());
        return mapper.selectList(queryWrapper);
    }

    /**
     * 查询符合条件的数据
     * @param query
     * @return
     */
    public List<T> getByMap(Map<String,Object> query){
        if (!query.containsKey("delete_flag")){
            query.put("delete_flag", EntityStatus.EXIST.getFlag());
        }

        return mapper.selectByMap(query);
    }

    /**
     * 上传文件到指定目录
     * @param uploadField
     * @return
     */
    public FileUploadStandard uploadFile(FileUploadStandard uploadField){
        //判断本地保存文件夹是否存在
        File folder = new File(uploadField.getLocalPath());
        if (!folder.exists()){
            folder.mkdirs();
        }
        //判断文件合法性（后缀名支持，文件大小）
        /* 验证文件后缀名是否合法 */
        String originalFilename = uploadField.getFile().getOriginalFilename();
        int begin = originalFilename.lastIndexOf(".");
        int last = originalFilename.length();
        String fileSuffix = originalFilename.substring(begin, last);
        FileSuffixConstant[] fileSuffixConstants = FileSuffixConstant.values();
        boolean hasLegal = false;
        for (FileSuffixConstant fileSuffixConstant : fileSuffixConstants) {
            System.out.print(fileSuffixConstant.getFileSuffix() + ",");
            if (fileSuffixConstant.getFileSuffix().equals(fileSuffix)){
                hasLegal = true;
                uploadField.setFileSuffix(fileSuffixConstant);
                break;
            }
        }
        if (!hasLegal) throw new RuntimeException("文件格式不支持，请上传正确的格式！");
        /* 验证文件大小是否合法 */
        if (uploadField.getFileSize().longValue()>uploadField.getLimited().longValue()) throw new RuntimeException("文件过大，上传失败！");
        //保存文件到本地
        try {
            uploadField.getFile().transferTo(new File(uploadField.getLocalPath() + "/" + uploadField.getFileName()));
        }catch (IOException io){
        }
        return uploadField;
    }

    public IPage<T> queryByPage(Page<T> pager, Wrapper<T> entityWrapper) {

        return mapper.selectPage(pager,entityWrapper);
    }
}
