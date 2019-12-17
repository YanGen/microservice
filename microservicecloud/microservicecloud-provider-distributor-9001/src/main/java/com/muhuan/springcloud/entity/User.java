package com.muhuan.springcloud.entity;

import com.atguigu.springcloud.entities.entity.BaseEntityAndDelete;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @ClassName User
 * @Description TODO
 * @Author geng <yg.db@uniteddata.com>
 * @Date 2019/11/14
 * @Version 1.0.0
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@TableName("t_user")
public class User extends BaseEntityAndDelete<Long> {

    private String userAccount; //账号
    private String userPassword; //密码
    private String userName; //用户名
    private String userPhone; // 手机号
    @JsonSerialize(using = ToStringSerializer.class)
    private Long corporationId; // 企业Id


    public User cloneUser(){
        ByteArrayOutputStream bos  = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        User user = null;
        try {
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            user = (User)ois.readObject();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                if (ois!=null)
                    ois.close();
                if (bis!=null)
                    bis.close();
                if (oos!=null)
                    oos.close();
                if (bos!=null)
                    bos.close();
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }
}
