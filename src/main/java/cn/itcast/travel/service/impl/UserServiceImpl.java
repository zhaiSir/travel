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
            String content = "<a href='http://localhost:/travel/activeUserServlet1?code="+user.getCode()+"'>点击激活【黑马旅游网】</a>";
            MailUtils.sendMail(user.getEmail(),content,"激活邮件");
            return true;
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
