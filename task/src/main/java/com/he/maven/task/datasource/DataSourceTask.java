package com.he.maven.task.datasource;

import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by heyanjing on 2018/2/10 12:06.
 */
@Slf4j
public class DataSourceTask {
    private HikariDataSource dataSource;

    public void setDataSource(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void hikari() {
        HikariConfigMXBean hikariConfigMXBean = dataSource.getHikariConfigMXBean();
        log.debug("getConnectionTimeout------{}", hikariConfigMXBean.getConnectionTimeout());
        log.debug("getIdleTimeout------{}", hikariConfigMXBean.getIdleTimeout());
        log.debug("getLeakDetectionThreshold------{}", hikariConfigMXBean.getLeakDetectionThreshold());
        log.debug("getMaximumPoolSize------{}", hikariConfigMXBean.getMaximumPoolSize());
        log.debug("getMinimumIdle------{}", hikariConfigMXBean.getMinimumIdle());
        log.debug("getPoolName------{}", hikariConfigMXBean.getPoolName());
        log.debug("getValidationTimeout------{}", hikariConfigMXBean.getValidationTimeout());
        HikariPoolMXBean hikariPoolMXBean = dataSource.getHikariPoolMXBean();
        if (hikariPoolMXBean != null) {
            log.warn("getTotalConnections------{}", hikariPoolMXBean.getTotalConnections());
            log.warn("getActiveConnections------{}", hikariPoolMXBean.getActiveConnections());
            log.warn("getIdleConnections------{}", hikariPoolMXBean.getIdleConnections());
            log.debug("getThreadsAwaitingConnection------{}", hikariPoolMXBean.getThreadsAwaitingConnection());
        } else {
            log.error(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "____线程池还未初始化");
        }
    }
}
