package bootmain.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

@Configuration

public class DruidConfiguration  {

//   DruidDataSource的属性名跟配置文件中spring.datasource的属性名一样，为了能够使用配置文件中的属性设置，可以使用ConfigurationProperties注解
   @ConfigurationProperties(prefix = "spring.datasource")
   @Bean
    public DataSource dataSource() throws SQLException {
       DruidDataSource ds = new DruidDataSource();
       ds.setFilters("stat");
       return ds;
   }
   @Bean
   public ServletRegistrationBean statViewServlet(){
      StatViewServlet statViewServlet = new StatViewServlet();
      ServletRegistrationBean<StatViewServlet> statViewServletServletRegistrationBean = new ServletRegistrationBean<>(statViewServlet,"/druid/*");
      return statViewServletServletRegistrationBean;

   }
   @Bean
   public FilterRegistrationBean webStatFilter(){
      FilterRegistrationBean<WebStatFilter> webStatFilterFilterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
      webStatFilterFilterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
      Map map  = new LinkedHashMap<String,String>();
      map.put("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
      webStatFilterFilterRegistrationBean.setInitParameters(map);
      return webStatFilterFilterRegistrationBean;
   }
}
