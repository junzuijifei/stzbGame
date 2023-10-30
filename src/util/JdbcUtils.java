package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtils {
    //构造方法私有化----工具类 不需要new出来，通过类名称，方法名称访问
    private JdbcUtils(){

    }
    //定义工具 需要 声明 变量
    private static String driverClass;
    private static String url;
    private static String user;
    private static String password;
    //使用静态代码块， 声明好jdbc变量赋值（jdbc.properties）
    static {
        try {
            //读取jdbc.properties  IO 路径 相随路径
            InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //赋值声明好的变量
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            driverClass= properties.getProperty("driverClass");//静态
            url= properties.getProperty("url");//静态
            user= properties.getProperty("user");//静态
            password= properties.getProperty("password");//静态
            //注册驱动
            Class.forName(driverClass);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //封装连接方法

    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //封装释放连接方法
    public static void closeConnection(ResultSet resultSet, Statement statement,Connection connection){
        //查询 释放链接 connection statement resultSet
        try {
            if (resultSet!=null)
                resultSet.close();
            if (statement!=null)
                statement.close();
            if (connection!=null)
                connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        //增删改 释放连接 statement connection
    }

    public static void closeConnection(Statement statement,Connection connection){//重载
        //增删改 释放连接 statement connection
        closeConnection(null,statement,connection);
    }
}
