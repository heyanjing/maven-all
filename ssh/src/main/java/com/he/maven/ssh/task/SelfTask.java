package com.he.maven.ssh.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by heyanjing on 2018/2/10 12:06.
 */
@Slf4j
public class SelfTask {
    @Scheduled(cron = "0/5 * * * * ? ")
    public void test() {
//        try {
//            TimeUnit.SECONDS.sleep(20);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+"       SelTask每5s执行一次");
    }
}
