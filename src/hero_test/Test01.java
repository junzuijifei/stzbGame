package hero_test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Test01 {
    private static Scanner sc=new Scanner(System.in);
    private static ArrayList<Hero> myHeroes = new ArrayList<>();
    public static void main(String[] args) {
        gameMenu();
        // 创建阵容
    }

    public static void gameMenu(){
        while (true){
            System.out.println("1、开始游戏");
            System.out.println("2、查看英雄");
            System.out.println("3、退出游戏");
            System.out.println("请选择你的操作：");
            int i = sc.nextInt();
            switch (i){
                case 1:
                    startGame();
                    break;
                case 2:
                    show();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("选择错误，请重新选择：");
                    sc.nextInt();
            }
        }

    }

    private static void  show() {
        DatabaseReader databaseReader = new DatabaseReader();
        ArrayList<Hero> heroes = databaseReader.readHero();
        Iterator<Hero> iterator = heroes.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private static void startGame() {
        while (true) {
            System.out.println("1、选择英雄以及关卡");
            System.out.println("2、查看英雄");
            System.out.println("3、装备宝石");
            System.out.println("4、返回主页");
            System.out.println("请选择你的操作：");
            int i = sc.nextInt();
            switch (i){
                case 1:
                    levelGame();
                    break;
                case 2:
                    show();
                    break;
                case 3:
                    equipGem();
                    break;
                case 4:
                    gameMenu();
                default:
                    System.out.println("选择错误，请重新选择：");
                    sc.nextInt();
            }

        }
    }

    private static ArrayList<Hero> choiceHero() {
        DatabaseReader databaseReader = new DatabaseReader();
        System.out.println("请选择你的前排英雄，英雄名称：");
        String heroName1 = sc.next();
        Hero byName = databaseReader.getByName(heroName1);
        myHeroes.add(byName);
        System.out.println("请选择你的中排英雄");
        String heroName2 = sc.next();
        Hero byName1 = databaseReader.getByName(heroName2);
        myHeroes.add(byName1);
        System.out.println("请选择你的后排英雄");
        String heroName3 = sc.next();
        Hero byName2 = databaseReader.getByName(heroName3);
        myHeroes.add(byName2);
        System.out.println("你的英雄队列：");
        Iterator<Hero> iterator = myHeroes.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        return myHeroes;
    }

    private static void levelGame() {

        choiceHero();
            System.out.println("1、第一关");
            System.out.println("2、第二关");
            System.out.println("3、第三关");
            System.out.println("4、第四关");
            System.out.println("5、第五关");
            System.out.println("请选择要挑战的关卡：");
        int i = sc.nextInt();
        Battle battle = new Battle(myHeroes,new Level(i));
        int count=0;
       // battle.start(count);
    }

    private  static void equipGem(){
        DatabaseReader databaseReader = new DatabaseReader();
        System.out.println("请选择你要装备宝石的英雄：");
        String Name = sc.next();
        Hero name = databaseReader.getByName(Name);
        while (true){
            System.out.println("1.宝石合成");
            System.out.println("2.查看宝石");
            System.out.println("3.退出");

            int i = sc.nextInt();
            switch (i){
                case 1:
                    System.out.println("请选择宝石合成的类型（ATTACK、HP、DEFENSE）：");
                    String type = sc.next();
                    ArrayList<Gem> byTypeGem = databaseReader.getByTypeGem(type);
                    name.getGem().combine(byTypeGem);
                    break;
                case 2:
                   databaseReader.showGem();
                    break;
                case 3:
                    startGame();
                default:
                    System.out.println("");
            }
        }
    }

}
