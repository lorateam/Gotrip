package mapper;

import java.util.List;
import model.Productimage;
import model.ProductimageExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductimageMapper {
    long countByExample(ProductimageExample example);

    int deleteByExample(ProductimageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Productimage record);

    int insertSelective(Productimage record);

    List<Productimage> selectByExample(ProductimageExample example);

    @Select("select * from ProductImage where pid = #{pid}")
    List<Productimage> selectByPid(Integer pid);

    @Select("select * from ProductImage where pid = #{pid} limit 1")
    Productimage selectOneByPid(Integer pid);

    @Select("select * from ProductImage where pid = #{pid} and type = #{type} order by id desc limit 10")
    List<Productimage> list(@Param("pid") Integer pid, @Param("type")String type);

    Productimage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Productimage record, @Param("example") ProductimageExample example);

    int updateByExample(@Param("record") Productimage record, @Param("example") ProductimageExample example);

    int updateByPrimaryKeySelective(Productimage record);

    int updateByPrimaryKey(Productimage record);
}