package mapper;

import java.util.List;

import com.sun.tools.corba.se.idl.constExpr.Or;
import model.Order;
import model.OrderExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    @Select("select * from Order_")
    List<Order> selectAll();

    @Select("select * from Order_ where uid=#{uid}")
    List<Order> selectByUid(Integer uid);

    @Select("select * from Order_ where payDate < date_sub(CURDATE(),INTERVAL 3 DAY);")
    List<Order> outDateOrder();

    @Select("select * from Order_ where status like #{status} and total>=#{min} and total<=#{max}")
    List<Order> selectOrder(@Param("status") String status, @Param("min") Integer min,@Param("max") Integer max);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}