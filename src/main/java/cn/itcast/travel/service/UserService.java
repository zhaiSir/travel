package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {
    public boolean regist(User user);

    public int login(User user);

    boolean active(String code);

}
