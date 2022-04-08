package com.yzy.service.impl;

import com.yzy.entity.User;
import com.yzy.mapper.UserMapper;
import com.yzy.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author yuanzhiyong
 * @since 2022-04-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
