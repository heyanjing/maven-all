package com.he.maven.ssh;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by heyanjing on 2018/8/21 12:50.
 */
@Slf4j
public class SimpleTest {
    String orderBy = "order by";
    String bracket = ")";

    @Test
    public void test1() {

        String sql1 = "select * from product where 1=1 ORDER BY showOrder";
        String sql2 = "select count(*) from ( select * from product where 1=1 ORDER BY showOrder ) temp";
        String sql3 = "select count(*) from ( select * from product where 1=1 and personId in ( select id from person order by state limit 5) ORDER BY showOrder ) temp";

        String s1 = xx(sql1);
        log.warn("{}",s1);
        String s2 = xx(sql2);
        log.warn("{}",s2);
        String s3 = xx(sql3);
        log.warn("{}",s3);

    }

    private String xx(String sql) {
        StringBuilder sb = new StringBuilder();
        boolean b = StringUtils.containsIgnoreCase(sql, orderBy);
        log.info("{}", b);
        if (b) {
            int i = StringUtils.indexOfIgnoreCase(sql, orderBy);
            String front = StringUtils.substring(sql, 0, i);
            log.info("{}", front);
            sb.append(front);
            String back = StringUtils.substring(sql, i, sql.length());
            log.info("{}", back);
            boolean b1 = StringUtils.containsIgnoreCase(back, bracket);
            if (b1) {
                sb.append(bracket);
                String fixBack = StringUtils.substringAfter(back, bracket);
                log.info("{}", fixBack);
                sb.append(fixBack);
                return xx(sb.toString());
            } else {
                return sb.toString();
            }
        } else {
            return sql;
        }
    }

    @Test
    public void test() {
        log.debug("{}", "aa");
        log.info("{}", "aa");
        log.warn("{}", "aa");
        log.error("{}", "aa");
    }
}
