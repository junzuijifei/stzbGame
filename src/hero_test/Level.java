package hero_test;

import java.util.ArrayList;

public class Level {
    private int number;//关卡数
    private ArrayList<Hero> enemyTeam=new ArrayList<>();//关卡敌方队伍
    private int difficultyLevel;//困难等级
    private int exp;//掉落经验值


    public Level(int number) {
            this.number = number;
            difficultyLevel =  number * 2;
            exp = 50 * number;
    }

    public Level() {
    }

    public ArrayList<Hero> choice(int number){
        switch (number){
            case 1:
                Hero c4 = new Hero("曹丕", 78,  16, 15, 1,
                        new Skill("魏武之泽",32) );
                c4.setPosition(Position.FRONT);
                Hero c5 = new Hero("张角", 67,  21, 12, 1,
                        new Skill("黄天当立",26)  );
                c5.setPosition(Position.BACK);
                Hero c6 = new Hero("董卓", 91,  17, 18, 1,
                        new Skill("逆谋",25));
                c6.setPosition(Position.MIDDLE);
                enemyTeam.add(c4);
                enemyTeam.add(c5);
                enemyTeam.add(c6);
                break;
            case 2:
                Hero c7 = new Hero("公孙瓒", 150,  30, 35, 2,
                        new Skill("动如雷震",30));
                c7.setPosition(Position.FRONT);
                Hero c8 = new Hero("马超", 155,  32, 25, 2,
                        new Skill("血溅黄沙",30));
                c8.setPosition(Position.BACK);
                Hero c9 = new Hero("诸葛亮", 155,  33, 24, 2,
                        new Skill("诸葛锦囊",35));
                c9.setPosition(Position.MIDDLE);
                enemyTeam.add(c7);
                enemyTeam.add(c8);
                enemyTeam.add(c9);
                break;
            case 3:
                Hero c10 = new Hero("甄洛", 260,  40, 35, 3,
                        new Skill("洛水佳人",40));
                c10.setPosition(Position.FRONT);
                Hero c11 = new Hero("貂蝉", 220,  45, 35, 3,
                        new Skill("白楼独舞",40));
                c11.setPosition(Position.BACK);
                Hero c12 = new Hero("祝融夫人", 200,  46, 35, 3,
                        new Skill("火兽冲锋",40));
                c12.setPosition(Position.MIDDLE);
                enemyTeam.add(c10);
                enemyTeam.add(c11);
                enemyTeam.add(c12);
                break;
            case 4:
                Hero c13 = new Hero("张机", 350,  52, 45, 4,
                        new Skill("",50));
                c13.setPosition(Position.FRONT);
                Hero c14 = new Hero("法正", 330,  55, 45, 4,
                        new Skill("难知如阴",50));
                c14.setPosition(Position.BACK);
                Hero c15 = new Hero("关银屏", 300,  58, 45, 4,
                        new Skill("巾帼战阵",50));
                c15.setPosition(Position.MIDDLE);
                enemyTeam.add(c13);
                enemyTeam.add(c14);
                enemyTeam.add(c15);
                break;
            case 5:
                Hero c16 = new Hero("吕蒙", 480,  60, 55, 5,
                        new Skill("白衣渡江",60));
                c16.setPosition(Position.FRONT);
                Hero c17 = new Hero("小乔", 440,  63, 55, 5,
                        new Skill("鸾凤和鸣",60));
                c17.setPosition(Position.BACK);
                Hero c18 = new Hero("陆抗", 400,  67, 55, 5,
                        new Skill("西陵克晋",60));
                c18.setPosition(Position.MIDDLE);
                enemyTeam.add(c16);
                enemyTeam.add(c17);
                enemyTeam.add(c18);
                break;
            default:
                System.out.println("错误！请重新选择：");
        }
        return enemyTeam;
    }

    public int getNumber() {
            return number;
    }

    public int getDifficultyLevel() {
            return difficultyLevel;
    }

    public int getExp() {
            return exp;
    }

    public boolean isCleared(){
        return false;
    }


}
