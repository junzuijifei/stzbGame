package hero_test;

import util.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseReader {


    public ArrayList<Hero> readHero()  {//查看所有英雄
        Connection connection=null;
        Statement statement=null;
        ResultSet rs=null;
        try {
            //java连接MySQL数据库查询所有数据
            connection = JdbcUtils.getConnection();
            //4 获取执行者对象
            statement = connection.createStatement();
            //5执行sql语句并获取返回结果
            rs = statement.executeQuery("SELECT * FROM game.hero");
            ArrayList<Hero> heroes=new ArrayList<>();
            while (rs.next()) {
                // ...读取其他属性
                String name = rs.getString("name");
                int blood = rs.getInt("blood");
                int attack = rs.getInt("atk");
                int defense = rs.getInt("def");
                int level = rs.getInt("level");
                String skill=rs.getString("skill");
                    Hero hero = new Hero(name, blood, attack, defense, level,
                            new Skill(skill));
                    heroes.add(hero);
            }
            return heroes;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            //7释放jdbc资源
            JdbcUtils.closeConnection(rs,statement,connection);
        }
    }


    private ArrayList<Skill> readSkill()  {//英雄技能
        Connection connection=null;
        Statement statement=null;
        ResultSet rs=null;
        ResultSet rs1=null;
        try {
            //java连接MySQL数据库查询所有数据
            connection = JdbcUtils.getConnection();
            //4 获取执行者对象
            statement = connection.createStatement();
            //5执行sql语句并获取返回结果
            rs1 = statement.executeQuery("SELECT * FROM game.skill");
            ArrayList<Skill> skills=new ArrayList<>();
            while (rs.next()) {
                String name = rs.getString("name");
                int cost = rs.getInt("cost");
                Skill skill = new Skill(name, cost);
                // ...将新的技能添加到某个英雄的技能列表中，或者保存在一个技能数组里
                skills.add(skill);
            }
            return skills;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            //7释放jdbc资源
            JdbcUtils.closeConnection(rs1,statement,connection);
        }
    }

    public Skill getBySkillName(String name){//根据名字选择英雄
        if (name == null){
            return null;
        }
        Connection connection=null;
        PreparedStatement PreparedStatement=null;
        ResultSet resultSet=null;
        try {
            //java连接MySQL数据库查询所有数据
            connection = JdbcUtils.getConnection();
            String sql="select * from game.skill where name =?";
            //4 获取执行者对象
            PreparedStatement = connection.prepareStatement(sql);
            PreparedStatement.setString(1,name);
            //5执行sql语句并获取返回结果
            resultSet = PreparedStatement.executeQuery();
            boolean next = resultSet.next();
            if (!next){
                return null;
            }
            //6对结果进行处理
            String skillName = resultSet.getString("name");//第一列
            int cost = resultSet.getInt("damage");//第二列

            //将查询的数据封装成java对象
            Skill skill = new Skill(skillName, cost);
            return skill;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            //7释放jdbc资源
            JdbcUtils.closeConnection(PreparedStatement,connection);
        }
    }

    public Hero getByName(String name){//根据名字选择英雄
        if (name == null){
            return null;
        }
        Connection connection=null;
        PreparedStatement PreparedStatement=null;
        ResultSet resultSet=null;
        try {
            //java连接MySQL数据库查询所有数据
            connection = JdbcUtils.getConnection();
            String sql="select * from game.hero where name =?";
            //4 获取执行者对象
            PreparedStatement = connection.prepareStatement(sql);
            PreparedStatement.setString(1,name);
            //5执行sql语句并获取返回结果
            resultSet = PreparedStatement.executeQuery();
            boolean next = resultSet.next();
            if (!next){
                return null;
            }
            //6对结果进行处理
            String heroName = resultSet.getString("name");//第一列
            int blood = resultSet.getInt("blood");//第二列
            int atk = resultSet.getInt("atk");//第三列
            int def = resultSet.getInt("def");//第四列
            int level = resultSet.getInt("level");//第五列
            String skill = resultSet.getString("skill");//第六列
            //将查询的数据封装成java学生对象
            Hero hero = new Hero(heroName, blood, atk, def, level, new Skill(skill));
            return hero;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            //7释放jdbc资源
            JdbcUtils.closeConnection(PreparedStatement,connection);
        }
    }

    public int insertGem(Gem gem){//添加宝石
        Connection connection=null;
        Statement statement=null;
        try {
            //java连接MySQL数据库查询所有数据
            connection = JdbcUtils.getConnection();
            //4 获取执行者对象
            statement = connection.createStatement();
            //5执行sql语句并获取返回结果
            String sql="INSERT  INTO game.gemstone(name,type,value,level)VALUES('"+gem.getName()+"','"+gem.getType()+"','"+gem.getValue()+"','"+gem.getLevel()+"')";
            //System.out.println("sql"+sql);
            int i = statement.executeUpdate(sql);//影响行数
            return i;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            //7释放jdbc资源
            JdbcUtils.closeConnection(statement,connection);
        }
    }

    public ArrayList<Gem> getByTypeGem(String type){//根据宝石类型挑选宝石
        if (type == null){
            return null;
        }
        Connection connection=null;
        PreparedStatement PreparedStatement=null;
        ResultSet resultSet=null;
        try {
            //java连接MySQL数据库查询所有数据
            connection = JdbcUtils.getConnection();
            String sql="select * from game.gemstone where name =?";
            //4 获取执行者对象
            PreparedStatement = connection.prepareStatement(sql);
            PreparedStatement.setString(1,type);
            //5执行sql语句并获取返回结果
            resultSet = PreparedStatement.executeQuery();
            ArrayList<Gem> gems=new ArrayList<>();
            while ( resultSet.next()){
                //6对结果进行处理
                String name = resultSet.getString("name");//第一列
                String gemType = resultSet.getString("type");//第二列
                int value = resultSet.getInt("value");//第三列
                int level = resultSet.getInt("level");//第四列
                //将查询的数据封装成java宝石对象
                Gem gem=new Gem(name, gemType,value,level);
                gem.toString();
                gems.add(gem);
            }
            return gems;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            //7释放jdbc资源
            JdbcUtils.closeConnection(PreparedStatement,connection);
        }

    }

    public void showGem(){//查询所有宝石
        Connection connection=null;
        Statement statement=null;
        ResultSet rs=null;
        try {
            //java连接MySQL数据库查询所有数据
            connection = JdbcUtils.getConnection();
            //4 获取执行者对象
            statement = connection.createStatement();
            //5执行sql语句并获取返回结果
            rs = statement.executeQuery("SELECT * FROM game.gemstone");
            ArrayList<Gem> gems=new ArrayList<>();
            while ( rs.next()){
                //6对结果进行处理
                String name = rs.getString("name");//第一列
                String gemType = rs.getString("type");//第二列
                int value = rs.getInt("value");//第三列
                int level = rs.getInt("level");//第四列
                //将查询的数据封装成java宝石对象
                Gem gem=new Gem(name, gemType,value,level);
                System.out.println(gem.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //7释放jdbc资源
            JdbcUtils.closeConnection(rs,statement,connection);
        }

    }
}
