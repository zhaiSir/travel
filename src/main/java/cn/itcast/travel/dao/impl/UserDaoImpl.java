package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUserName(String username) {
        User user = null;
        try{
        //定义SQL语句
        String sql = "select * from tab_user where username = ? ";
        user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username);
        }catch(Exception e){

        }
        return user;
    }


    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code)"
                + "values(?,?,?,?,?,?,?,?,?)";
        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode());

    }

    @Override
    public void updateStatus(User user) {
        
    }

    @Override
    public User findByCode(String code) {
        return null;
    }
}
