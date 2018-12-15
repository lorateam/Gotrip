package mapper;

import java.util.List;
import model.Category;
import model.CategoryExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    @Select("select * from Category")
    List<Category> selectAll();

    @Select("select * from Category limit 2")
    List<Category> selectTwo();

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    @Select("select id from category where name=#{name}")
    Integer selectNew(String name);
}