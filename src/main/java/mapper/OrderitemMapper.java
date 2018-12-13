package mapper;

import java.util.List;
import model.Orderitem;
import model.OrderitemExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderitemMapper {
    long countByExample(OrderitemExample example);

    int deleteByExample(OrderitemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Orderitem record);

    int insertSelective(Orderitem record);

    List<Orderitem> selectByExample(OrderitemExample example);

    @Select("select * from OrderItem where oid=#{orderId}")
    List<Orderitem> selectByOrder(Integer orderId);

    @Select("select sum(number) from OrderItem where oid=#{orderId}")
    Integer countNumber(Integer orderId);

    @Select("select count(*) from OrderItem where pid = #{pid}")
    int countByPid(Integer pid);

    Orderitem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Orderitem record, @Param("example") OrderitemExample example);

    int updateByExample(@Param("record") Orderitem record, @Param("example") OrderitemExample example);

    int updateByPrimaryKeySelective(Orderitem record);

    int updateByPrimaryKey(Orderitem record);
}