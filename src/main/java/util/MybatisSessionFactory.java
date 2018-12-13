package util;
//创建这个类的目的是为了使用单例模式，确保不重复建立SqlSessionFactory
//简单来说，这样做可以加快数据库的连接速度

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class MybatisSessionFactory {
    private static Logger logger = Logger.getLogger(MybatisSessionFactory.class);

    private static SqlSessionFactory instance = null;

    private MybatisSessionFactory(){
        String path = "mybatis-config.xml";
        InputStream in = null;
        try{
            in = Resources.getResourceAsStream(path);
            instance = new SqlSessionFactoryBuilder().build(in);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getFactory(){
        if(instance == null){
            new MybatisSessionFactory();
        }
        return instance;
    }

    public static SqlSession getSession(){
        logger.debug("打开sqlSession");
        if(instance == null){
            new MybatisSessionFactory();
        }
        new MybatisSessionFactory();
        return instance.openSession(true);
    }
}
