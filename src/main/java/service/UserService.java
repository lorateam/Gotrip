package service;

import mapper.*;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserInt {

    @Autowired()
    ProductMapper productMapper;

    @Autowired
    ProductimageMapper productimageMapper;
    @Autowired
    OrderitemMapper orderitemMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserMapper userMapper;

    //根据用户名查询用户
    @Override
    public User getUser(String name){
        return userMapper.selectByName(name);
    }

    //根据用户id查询用户
    @Override
    public User getUser(int id){
        return userMapper.selectByPrimaryKey(id);
    }

    //查询所有用户
    @Override
    public List<User> listAll(){
        return userMapper.selectAll();
    }

    //通过id删除用户
    @Override
    public void deleteByPrimaryKey(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    //新增用户
    @Override
    public void insert(User user) {
        userMapper.insert(user);
    }

    //更新用户信息
    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }
}
