package com.nameicc.listener;

import com.alibaba.fastjson.JSON;
import com.nameicc.common.CanalMessage;
import com.nameicc.entity.UserEntity;
import com.nameicc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

@Component
@Slf4j
public class CanalUserListener {

    @Resource
    private UserService userService;

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("canal_user_queue"))
    public void updateUser(String message) {
        CanalMessage canalMessage = JSON.parseObject(message, CanalMessage.class);
        log.info("Canal监听到 {} 表发生变化，详细信息为：{}", canalMessage.getTable(), message);
        if (CollectionUtils.isEmpty(canalMessage.getData())) {
            return ;
        }
        UserEntity userEntity = JSON.parseObject(canalMessage.getData().get(0), UserEntity.class);
        if ("INSERT".equals(canalMessage.getType())) {
            userService.insert(userEntity);
            log.info("新增用户");
        } else if ("UPDATE".equals(canalMessage.getType())) {
            userService.update(userEntity);
            log.info("更新用户");
        } else if ("DELETE".equals(canalMessage.getType())) {
            userService.delete(userEntity);
            log.info("删除用户");
        }
    }

}
