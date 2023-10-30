package hero_test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Battle {
    private ArrayList<Hero> myHeroes;
    private int turn;//回合数
    private Level level;




    public Battle(ArrayList<Hero> myHeroes, Level level) {
        this.myHeroes = myHeroes;
        turn=1;
       this.level=level;
    }


    public ArrayList<Hero>  start( JTextArea textArea) {

        ArrayList<Hero> enemyHeroes=level.choice(level.getNumber());
        textArea.append("----------------战斗开始！----------------\n");
        while (isOver(myHeroes,enemyHeroes)) {
            while (true){
                textArea.append("----------------第" + turn + "回合----------------\n");
            for (Hero hero : myHeroes) {
                if (!hero.isDead() && isAllDead(enemyHeroes)) {
                    Hero target = selectTarget(enemyHeroes);
                    if (hero.getAnger()>=hero.getMaxAnger()){
                        String s = hero.skillAttack(target);
                        textArea.append(s+"\n");
                        textArea.setFont(new Font("宋体",Font.BOLD,15));
                        continue;
                    }else {
                        String s = hero.normalAttack(target);
                        hero.increaseAnger();
                        textArea.append(s+"\n");
                        textArea.setFont(new Font("宋体",Font.BOLD,15));
                    }
                    System.out.println();
                }
            }
            for (Hero hero : enemyHeroes) {
                if (!hero.isDead() && isAllDead(myHeroes)) {
                    Hero target = selectTarget(myHeroes);
                    if (hero.getAnger()>=hero.getMaxAnger()){
                        String s = hero.skillAttack(target);
                        textArea.append(s+"\n");
                        textArea.setFont(new Font("宋体",Font.BOLD,15));
                        continue;
                    }else {
                        String s = hero.normalAttack(target);
                        hero.increaseAnger();
                        textArea.append(s+"\n");
                        textArea.setFont(new Font("宋体",Font.BOLD,15));
                    }
                }
            }

            turn++;
            if (!judge(enemyHeroes,myHeroes,textArea)){
                return myHeroes;
            }
            }
        }
        return myHeroes;
    }

    private boolean judge(ArrayList<Hero> enemyHeroes, ArrayList<Hero> myHeroes, JTextArea textArea){
        DatabaseReader databaseReader = new DatabaseReader();
        Random random=new Random();
        int lie=random.nextInt(101);
        if (isMyWin(enemyHeroes)) {
            textArea.append("----------------战斗胜利！----------------\n");
            textArea.setFont(new Font("宋体",Font.BOLD,15));
            for (Hero hero : myHeroes) {
                    hero.gainExp(100*level.getNumber(),textArea);
            }
            textArea.append("----------------掉落宝石："+"----------------\n");
            textArea.setFont(new Font("宋体",Font.BOLD,15));
            Gem gem = new Gem("红宝石"+lie, "ATTACK", 8, level.getNumber());
            textArea.append(gem.toString());
            textArea.setFont(new Font("宋体",Font.BOLD,15));
            databaseReader.insertGem(gem);
            return false;
        }
        if (isMyFail(myHeroes)){
            textArea.append("----------------战斗失败！"+"----------------\n");
            textArea.setFont(new Font("宋体",Font.BOLD,15));
            for (Hero hero : myHeroes) {
                    hero.gainExp(60*level.getNumber(), textArea);
            }
            textArea.append("----------------掉落宝石："+"----------------\n");
            textArea.setFont(new Font("宋体",Font.BOLD,15));
            Gem gem = new Gem("绿宝石"+lie, "HP",7,  level.getNumber());
            textArea.append(gem.toString());
            textArea.setFont(new Font("宋体",Font.BOLD,15));
            databaseReader.insertGem(gem);
            return false;
        }
        return true;
    }

    private boolean isOver(ArrayList<Hero> myHeroes,ArrayList<Hero> enemyHeroes) {
        //英雄全部阵亡返回false，否则返回ture
        return isAllDead(myHeroes) || isAllDead(enemyHeroes);
    }

    private boolean isAllDead(ArrayList<Hero> heroes) {//判断英雄是否全部阵亡
        //返回true没有全部阵亡
        //返回false全部阵亡
        for (Hero hero : heroes) {
            if (!hero.isDead()) {
                return true;
            }
        }
        return false;
    }

    private boolean isMyWin(ArrayList<Hero> enemyHeroes) {//敌方英雄全部阵亡
        return !isAllDead(enemyHeroes);
    }

    private boolean isMyFail(ArrayList<Hero> myHeroes) {//我方英雄全部阵亡
        return !isAllDead(myHeroes);
    }

    private Hero selectTarget(ArrayList<Hero> heroes) {//攻击敌方英雄
        for (Hero hero : heroes) {
            if (!hero.isDead()) {
                return hero;
            }
        }
        return null;
       /* int index = (int) (Math.random() * aliveHeroes.size());
        return aliveHeroes.get(index);*/
    }
}
