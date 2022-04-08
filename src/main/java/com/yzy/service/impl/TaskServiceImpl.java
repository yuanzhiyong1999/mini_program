package com.yzy.service.impl;

import com.yzy.entity.Task;
import com.yzy.mapper.TaskMapper;
import com.yzy.service.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 任务 服务实现类
 * </p>
 *
 * @author yuanzhiyong
 * @since 2022-04-05
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

}
