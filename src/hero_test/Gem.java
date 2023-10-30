package hero_test;

//enum GemstoneType {HP, ATTACK, DEFENSE} // 宝石类型：血量，攻击，防御

import java.util.ArrayList;

public class Gem {
    private String name;
    private String type; //类型，"血量"，"攻击"，"防御"
    int level; //等级
    int value; //加成数值


    public void upgrade() {// 升级增加
        this.value += (int)(this.value * 0.1);//增加10%的属性值
        this.level++;
    }
    //构造函数
    public Gem(String name, String type, int value, int level) {
            this.name = name;
            this.type = type;
            this.level = level;
            this.value = value;
    }

    public Gem() {
    }

    public Gem combine(ArrayList<Gem> gems) {// 宝石合成：两个等级相同、类型相同的宝石可以合成一个等级+1的宝石
        for (Gem gem1 : gems) {
            for (Gem gem2 : gems) {
                if (gem1.getLevel() == gem2.getLevel() && gem1.getType() == gem2.getType() && gem1 != gem2) {
                    gems.set(gems.indexOf(gem1),
                            new Gem("", gem1.getType(),gem1.getLevel() + 1 ,gem1.getValue() +
                                    gem2.getValue() ));
                    gem1.upgrade();
                    gems.remove(gem2);
                    return gem1;
                }
            }
        }
        return null;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * 设置
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * 设置
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * 获取
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**
     * 设置
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return "宝石:名字 = " + name + "  |  "+"类型 = " + type + "  |  等级 = " + level + "  |  属性值 = " + value ;
    }



}
