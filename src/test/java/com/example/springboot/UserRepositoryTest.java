package com.example.springboot;

import com.example.springboot.entity.Address;
import com.example.springboot.entity.User;
import com.example.springboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserRepositoryTest {



    @Autowired
    private UserRepository ur;

    @Test
    public void addUserTest(){
        User user=new User("吴用");
        log.debug("添加了一位新用户："+ur.addUser(user).toString());
        User user1=new User("潘金莲");
        log.debug("添加了一位新用户："+ur.addUser(user1).toString());
        User user2=new User("鲁智深");
        log.debug("添加了一位新用户："+ur.addUser(user2).toString());
        User user3=new User("李逵");
        log.debug("添加了一位新用户："+ur.addUser(user3).toString());
    }

    @Test
    public void addAddressTest(){
        Address address=new Address("杭州");
        Address address1=new Address("苏州");
        Address address2=new Address("株洲");
        Address address3=new Address("云南");
        address=ur.addAddress(address,10);
        log.debug("为用户"+address.getUser().getId()+"添加了一个新地址："+address.getDetail());
        address1=ur.addAddress(address1,10);
        log.debug("为用户"+address1.getUser().getId()+"添加了一个新地址："+address1.getDetail());
        address2=ur.addAddress(address2,11);
        log.debug("为用户"+address2.getUser().getId()+"添加了一个新地址："+address2.getDetail());
        address3=ur.addAddress(address3,11);
        log.debug("为用户"+address3.getUser().getId()+"添加了一个新地址："+address3.getDetail());

    }

    @Test
    public void updateUserTest(){

        log.debug("用户李白的新名字为："+ur.updateUser(3,"关羽").getName());

    }

    @Test
    public void updateAddress(){
      ur.updateAddressByFind(7, 9);

      ur.updateAddressByMerge(8,11);

    }

    @Test
    public void listAddressesTest(){
        User user=ur.getUser(10);
        user.getAddresses().
                forEach(address -> {
            log.debug(address.getDetail());
        });
    }

    @Test
    public void removeAddressTest(){
        ur.removeAddress(2);
    }

    @Test
    public void removeUserTest(){
        //级联删除地址(在one端配置：cascade = CascadeType.REMOVE)
         ur. removeUser(4);

        //手动删除地址(取消上述配置)
        //ur.removeUser(3);

    }
}
