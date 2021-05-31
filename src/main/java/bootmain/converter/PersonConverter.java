package bootmain.converter;

import bootmain.module.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonConverter implements HttpMessageConverter {

    @Override
    public boolean canRead(Class aClass, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class aClass, MediaType mediaType) {
        //只要是person对象就返回true
        return aClass.isAssignableFrom(Person.class);
    }

    @Override
    //获取服务器可以支持的媒体类型时会调用该方法，返回本消息转换器支持的媒体类型
    public List<MediaType> getSupportedMediaTypes() {

        return  MediaType.parseMediaTypes("application/x-person");
    }

    @Override
    public Object read(Class aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(Object o, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String data;
        if (mediaType.equalsTypeAndSubtype(MediaType.parseMediaType("application/x-person"))) {
            if(o instanceof Person){
                //自定义application/x-person数据格式
                data= ((Person) o).getName()+"-"+((Person) o).getID();
                List <Charset> l= new ArrayList<Charset>();
                l.add(Charset.forName("UTF-8"));
                httpOutputMessage.getHeaders().setAcceptCharset(l);
                //将数据写出去
                httpOutputMessage.getBody().write(data.getBytes());
            }
        }
    }
}
