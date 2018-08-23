package com.he.maven.ssh.task;

import com.he.maven.ssh.core.Statics;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by heyanjing on 2018/2/10 12:06.
 */
@Slf4j
public class SelfTask {
    //@Scheduled(cron = "0/5 * * * * ? ")
    public void test1() {
        //        try {
        //            TimeUnit.SECONDS.sleep(20);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        log.info(Statics.now() + "       test1每5s执行一次");
    }

    public void test2() {
        //        try {
        //            TimeUnit.SECONDS.sleep(20);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        log.info(Statics.now()+ "       test2每8s执行一次");
    }
}
