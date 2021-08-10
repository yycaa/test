package bootmain.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class City
{
    private Long id;
    private String name;
    private String state;
    private String country;
}
