package mapper;

import java.util.List;
import model.Propertyvalue;
import model.PropertyvalueExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyvalueMapper {
    long countByExample(PropertyvalueExample example);

    int deleteByExample(PropertyvalueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Propertyvalue record);

    int insertSelective(Propertyvalue record);

    List<Propertyvalue> selectByExample(PropertyvalueExample example);

    @Select("select * from PropertyValue where id = #{pid}")
    List<Propertyvalue> selectByPid(Integer pid);

    @Select("update PropertyValue set value = #{value} where id = #{id}")
    List<Propertyvalue> updateValue(@Param("id") int id,@Param("value") String value);

    @Select("select * from PropertyValue where pid =#{pid} order by ptid desc")
    List<Propertyvalue> list(int pid);

    Propertyvalue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Propertyvalue record, @Param("example") PropertyvalueExample example);

    int updateByExample(@Param("record") Propertyvalue record, @Param("example") PropertyvalueExample example);

    int updateByPrimaryKeySelective(Propertyvalue record);

    int updateByPrimaryKey(Propertyvalue record);
}