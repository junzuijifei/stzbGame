package hero_test;

public  class Skill {
    DatabaseReader databaseReader=new DatabaseReader();
    private String name;
    private int cost;//技能值



    public Skill(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public Skill(String name) {
        this.name=name;
        this.cost=databaseReader.getBySkillName(name).cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
    public String use(Hero source,Hero target) {

        /*if (source.getSkill().name=="九锡黄龙"){
            source.takeDamage(-cost);//回复类技能
            str=source.getName() + "使用了" + source.getSkill().name + "，为自身恢复了" + cost + "点生命值";
            return str;
        }
        if (source.getSkill().name=="谋议宏图"){
            target.takeDamage(cost);//群体伤害
            str=source.getName() + " 对 " + target.getName() + " 使用了群体技能攻击，造成了 " + cost + " 点伤害";
            return str;
        }*/
        target.takeDamage(source.getSkill().cost);
        String str=source.getName() + "使用了" + source.getSkill().name + "，对" + target.getName() + "造成了" + source.getSkill().cost + "点伤害";
        return str;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String toString() {
        return "名字 ： " + this.name + "  |  属性值 ： " + cost ;
    }
}
