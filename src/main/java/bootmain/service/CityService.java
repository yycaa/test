package bootmain.service;

import bootmain.domain.City;
import bootmain.mybatis.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    CityMapper cMaper;
    public City findCityById(int id){
        return cMaper.selectCityById(id);
    };

}
