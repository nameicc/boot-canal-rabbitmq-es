package com.nameicc.listener;

import com.alibaba.fastjson.JSON;
import com.nameicc.common.CanalMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CanalUserListener {

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("canal_user_queue"))
    public void updateUser(String message) {
        CanalMessage canalMessage = JSON.parseObject(message, CanalMessage.class);
        log.info("canal 监听到 {} 发生变化，详细信息为：{}", canalMessage.getTable(), message);
    }

}
