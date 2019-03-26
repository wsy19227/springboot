package com.example.springboot.repository;

import com.example.springboot.entity.Address;
import com.example.springboot.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public User getUser(int uid){
        return em.find(User.class,uid);
    }

    public User addUser(User user){
        em.persist(user);
        em.refresh(user);
        return user;
    }

    public Address addAddress(Address address, int uid) {

        User user=em.find(User.class,uid);
        address.setUser(user);
        em.persist(address);
        return address;
    }

    public User updateUser(int uid, String newName) {
        User user=em.find(User.class,uid);
        user.setName(newName);
        em.flush();
        return user;
    }

    public Address updateAddressByMerge(int aid, int uid) {

       Address a=new Address();
       a.setId(aid);
       a.setUser(em.find(User.class,uid));
       a.setDetail(em.find(Address.class,aid).getDetail());
       Address address=em.merge(a);
       return address;
    }

    public  Address updateAddressByFind(int aid,int uid){

        Address address=em.find(Address.class,aid);
        User user=em.find(User.class,uid);
        address.setUser(user);
        return address;
    }

    public List<Address> listAddresses(int uid) {
        List<Address> list=new ArrayList<Address>();
        User u=em.find(User.class,uid);
        list=u.getAddresses();
        return list;
    }

    public void removeAddress(int aid) {

        Address address=em.find(Address.class,aid);
        em.remove(address);
    }

    public void removeUser(int uid) {

        //级联删除地址
        em.remove(em.find(User.class,uid));


        //手动删除用户及地址
//        User user=em.find(User.class,uid);
//        user.getAddresses()
//                .forEach(address -> em.remove(address));
//        em.remove(user);

    }
}
