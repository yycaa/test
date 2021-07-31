package bootmain;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Demo {
        @Autowired
        JdbcTemplate jdbcTemplate;
        @Autowired
        DruidDataSource ds;


        @Test
        public void contextLoads() throws SQLException {

//        jdbcTemplate.queryForObject("select * from account_tbl")
//        jdbcTemplate.queryForList("select * from account_tbl",)
          Long aLong = jdbcTemplate.queryForObject("select count(*) from book", Long.class);

            log.info("记录总数：{}",aLong);
            log.info("data source type:{}",ds.getClass());

        }


}
