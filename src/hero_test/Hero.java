package hero_test;

import javax.swing.*;
import java.awt.*;

public class Hero {
    private String name;
    private int level;
    private int blood;
    private int maxBlood;
    private int atk;
    private int defense;
    private int anger;
    private int maxAnger;
    private int experience; // 英雄当前经验值
    private int maxExperience; // 英雄最大经验值
    private Skill skill;
    private Position position;//站位
    private Gem gem; //宝石


    public Hero(String name, int blood, int ATK, int defense, int level,Skill skill) {
        this.name = name;
        this.level = 1;
        this.blood = blood;
        this.maxBlood = blood;
        this.atk = ATK;
        this.defense = defense;
        this.level=level;
        this.skill = skill;
        this.experience = 0;
        this.maxExperience = 100;
        this.anger = 0;
        this.maxAnger = 3;
        this.gem=null;

    }

    public static void add(Hero hero) {

    }


    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getBlood() {
        return blood;
    }

    public int getMaxBlood() {
        return maxBlood;
    }

    public int getAtk() {
        return atk;
    }

    public int getDefense() {
        return defense;
    }

    public int getAnger() {
        return anger;
    }

    public int getMaxAnger() {
        return maxAnger;
    }

    public Skill getSkill() {
        return skill;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getMaxExperience() {
        return maxExperience;
    }

    public Gem getGem() {
        return gem;
    }

    public void setGem(Gem gem) {
        this.gem = gem;
    }

    @Override
    public String toString() {
        return "英雄：" + name +
                "  |  等级：" + level +
                "  |  血量：" + blood +
                "  |  最大血量：" + maxBlood +
                "  |  攻击力：" + atk +
                "  |  防御力：" + defense +"\n"+
                "      技能" + skill +
                "  |  宝石：" + gem ;
    }

    public void addGemStone(Gem gem) {// 添加/替换宝石
       this.gem=gem;
    }

    public void replaceGemstone( Gem gem) {// 替换宝石

    }

    public String normalAttack(Hero target) {//普通攻击
        int damage = this.atk - target.getDefense();
        if (damage < 1) {
            damage = 1;
        }
        target.takeDamage(damage);
        return this.name + "使用普通攻击对" + target.getName() + "造成了" + damage + "点伤害";
    }

    public String skillAttack(Hero target) {//技能攻击
        String use=null;
        if (anger >= maxAnger) {
             use = skill.use(this, target);
            anger = 0;
        }
        return use;
    }

    public void takeDamage(int damage) {//获得的伤害
        blood -= damage;
        if (blood < 0) {
            blood = 0;
        }
    }

    public boolean isDead() {
        if (blood==0){
            return true;
        }
        return false;
    }

    public void increaseAnger() {//怒气
        anger++;
    }

    public void gainExp(int exp, JTextArea textArea) {//获得的经验
        this.setExperience(exp+this.getExperience());
        textArea.append(this.name+"获得经验："+exp+"\n");;
        if (this.getExperience()>=100){
            int oldLevel = level;
            this.level += this.getExperience() / 100;
            textArea.append(this.name + "升级了，等级提升到了" + level+"\n");
            this.maxBlood += 20 * (level - oldLevel);
            this.blood = maxBlood;
            this.atk += 10 * (level - oldLevel);
            this.defense += 10*(level - oldLevel);
            textArea.append(this.name+"生命值增加到 " + maxBlood + "，攻击力增加到 " + atk + "，防御力增加到 " + defense+"\n");
            textArea.setFont(new Font("宋体",Font.BOLD,15));
            this.setExperience(this.getMaxExperience()-100);
        }
    }
}
