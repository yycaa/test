package bootmain.config;

import bootmain.converter.PersonConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Configuration
public class WebConfiger {
    @Bean
    //WebMvcConfigurer+@Bean可以实现webmvc的自定义开发
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            //使用extendMessageConverters不会覆盖Srpingboot自带的messageconverter
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new PersonConverter());
            }

        };

    }
}
