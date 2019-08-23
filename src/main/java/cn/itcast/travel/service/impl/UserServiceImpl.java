package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    //注册用户
    @Override
    public boolean regist(User user) {
        User u = userDao.findByUserName(user.getUsername());
        if(u!=null){
            return false;
        }else{
            //设置激活码与激活状态
            user.setCode(UuidUtil.getUuid());
            user.setStatus("N");
            userDao.save(user);

            //激活邮件发送，邮件正文
            //String content = "<a href='http://localhost:/travel/activeUserServlet?code="+user.getCode()+"'>【激活黑馬教育網賬號】</a>";
            //MailUtils.sendMail(user.getEmail(),"Sb。","激活邮件");
            MailUtils.sendMail(user.getEmail(),  "<a href='http://192.168.30.1:8080/travel/activeUserServlet?code="+user.getCode()+"'>【点击激活黑马教育网账号】</a>", "激活邮件");
            return true;
        }

    }

    //用户登录
    @Override
    public int login(User user) {
        User u = userDao.findByUserName(user.getUsername());
        if (u == null) {
            return -1;//用户不存在
        } else {
            if (!u.getStatus().equals("Y")) {
                return 0;//账户未激活
            } else {
                if (user.getPassword().equals(u.getPassword())) {
                    return 1;//登录成功
                }else {
                    return -2;//用户名或密码错误
                }
            }
        }
    }

    //激活用户
    @Override
    public boolean active(String code) {
        //根据激活码查用户是否存在
        User user = userDao.findByCode(code);
        if(user !=null){
            userDao.updateStatus(user);
            return true;
        }else {
            return false;
        }
    }

}
