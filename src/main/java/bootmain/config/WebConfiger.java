package bootmain.config;

import bootmain.converter.PersonConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class WebConfiger {
    @Bean
    //WebMvcConfigurer+@Bean可以实现webmvc的自定义开发
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
               // ParameterContentNegotiationStrategy(Map<String, MediaType> mediaTypes)
                Map<String, MediaType> mediaTypes;
                mediaTypes = new HashMap<>();
                mediaTypes.put("person",MediaType.parseMediaType("application/x-person"));
                mediaTypes.put("json",MediaType.parseMediaType("application/json"));
                mediaTypes.put("xml",MediaType.parseMediaType("application/xml"));
                ParameterContentNegotiationStrategy ps=new ParameterContentNegotiationStrategy(mediaTypes);
                HeaderContentNegotiationStrategy  hs = new HeaderContentNegotiationStrategy();
                List<ContentNegotiationStrategy> strategies = new ArrayList<>();
                //先添加的策略在strategies列表中排序靠前，会先命中
                strategies.add(ps);
                strategies.add(hs);

                //void strategies(@Nullable List<ContentNegotiationStrategy> strategies)
                configurer.strategies(strategies);
            }

            @Override
            //使用extendMessageConverters不会覆盖Srpingboot自带的messageconverter
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new PersonConverter());
            }

        };

    }
}
