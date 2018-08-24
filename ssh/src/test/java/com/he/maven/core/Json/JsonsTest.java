package com.he.maven.core.Json;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by heyanjing on 2018/8/24 22:30.
 */
@Slf4j
public class JsonsTest {

    @Test
    public void test() throws Exception {
        A a = new A(new Date(), LocalDateTime.now(), LocalDate.now(), LocalTime.now(), "何彦静abc", 12.12, 28, 123L,null);

//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(a);
//        log.info("{}", json);
//        A a1 = mapper.readValue(json, A.class);
//        log.info("{}", a1);


        String s = Jsons.toJson(a);
        log.info("{}", s);
        String json="{\"nothing\":\"nothing\",\"date\":\"2019-08-24 22:48:39\",\"localDateTime\":\"2018-08-24 22:48:40\",\"localDate\":\"2018-08-24\",\"localTime\":\"22:48:40\",\"name\":\"何彦静abc\",\"AAmoxneyAA\":12.12,\"age\":28,\"second\":123}";
        A a1 = Jsons.toBean(json, A.class);
        log.info("{}", a1);

    }


}