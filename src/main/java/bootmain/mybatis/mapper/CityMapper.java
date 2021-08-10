package bootmain.mybatis.mapper;

import bootmain.domain.City;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityMapper {
    City selectCityById(int id);
}
