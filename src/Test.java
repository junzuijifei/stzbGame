import java.util.Random;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        // 英雄属性
        String[] heroNames = {"Hero1", "Hero2", "Hero3", "Hero4", "Hero5", "Hero6"};
        int[] heroLevels = {1, 1, 1, 1, 1, 1};
        int[] heroHp = {100, 120, 90, 110, 80, 100};
        int[] heroAttack = {20, 25, 18, 22, 15, 20};
        int[] heroDefense = {10, 15, 8, 12, 7, 10};
        int[] heroExp = {0, 0, 0, 0, 0, 0};
        int[] heroMaxExp = {100, 150, 120, 140, 110, 120};

        // 英雄站位及战斗规则
        int[] heroPositions = new int[6];
        for (int i = 0; i < heroPositions.length; i++) {
            heroPositions[i] = 0;
        }
        System.out.println("请选择己方英雄：");
        for (int i = 0; i < 3; i++) {
            System.out.println("请选择第" + (i + 1) + "个英雄：");
            for (int j = 0; j < heroNames.length; j++) {
                System.out.println(j + ": " + heroNames[j]);
            }
            int heroIndex = scanner.nextInt();
            heroPositions[heroIndex] = i + 1;
            System.out.println(heroNames[heroIndex] + " 已上阵。");
        }

        // 关卡功能
        int currentLevel = 1;
        while (currentLevel <= 5) {
            System.out.println("关卡 " + currentLevel + " 开始！");
            int[] enemyLevels = {1, 1, 1};
            int[] enemyHp = {100, 100, 100};
            int[] enemyAttack = {10, 12, 8};
            int[] enemyDefense = {5, 6, 4};
            int[] enemyExp = {10, 15, 12};
            int dropHeroIndex = -1;
            // 自动战斗过程
            int roundCount = 1;
            int[] heroAngers = {0, 0, 0, 0, 0, 0};
            int[] enemyAngers = {0, 0, 0};
            while (true) {
                System.out.println("第 " + roundCount + " 回合开始！");
                // 声明回合角色列表
                int[] roleIndexes = new int[6];
                int roleCount = 0;
                for (int i = 1; i <= 3; i++) {
                    for (int j = 0; j < heroPositions.length; j++) {
                        if (heroPositions[j] == i) {
                            roleIndexes[roleCount] = j;
                            roleCount++;
                        }
                    }
                }
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < enemyLevels.length; j++) {
                        roleIndexes[roleCount] = j + 3;
                        roleCount++;
                    }
                }
                // 执行回合
                boolean isAllHeroesDead = false;
                boolean isAllEnemiesDead = false;
                for (int i = 0; i < roleIndexes.length; i++) {
                    int roleIndex = roleIndexes[i];
                    if (roleIndex < 3 && heroHp[roleIndex] <= 0) {
                        continue;
                    }
                    if (roleIndex >= 3 && enemyHp[roleIndex - 3] <= 0) {
                        continue;
                    }
                    if (roleIndex < 3) {
                        // 己方英雄
                        System.out.println(heroNames[roleIndex] + " 发动攻击！");
                        boolean isSkillAttack = false;
                        int damage = heroAttack[roleIndex] - enemyDefense[roleIndex - 3];
                        if (damage < 1) {
                            damage = 1;
                        }
                        if (heroAngers[roleIndex] >= 3) {
                            System.out.println(heroNames[roleIndex] + " 发动技能攻击！");
                            isSkillAttack = true;
                            damage = heroAttack[roleIndex] * 2 - enemyDefense[roleIndex - 3];
                            if (damage < 1) {
                                damage = 1;
                            }
                            heroAngers[roleIndex] = 0;
                            // TODO: 技能效果
                        }
                        enemyHp[roleIndex - 3] -= damage;
                        System.out.println("造成了 " + damage + " 点伤害！");
                    } else {
                        // 敌方英雄
                        System.out.println("敌方英雄 " + (roleIndex - 2) + " 发动攻击！");
                        boolean isSkillAttack = false;
                        int damage = enemyAttack[roleIndex - 3] - heroDefense[roleIndex];
                        if (damage < 1) {
                            damage = 1;
                        }
                        if (enemyAngers[roleIndex - 3] >= 3) {
                            System.out.println("敌方英雄 " + (roleIndex - 2) + " 发动技能攻击！");
                            isSkillAttack = true;
                            damage = enemyAttack[roleIndex - 3] * 2 - heroDefense[roleIndex];
                            if (damage < 1) {
                                damage = 1;
                            }
                            enemyAngers[roleIndex - 3] = 0;
                            // TODO: 技能效果
                        }
                        heroHp[roleIndex] -= damage;
                        System.out.println("造成了 " + damage + " 点伤害！");
                    }
                    // 增加怒气
                    if (roleIndex < 3) {
                        heroAngers[roleIndex]++;
                    } else {
                        enemyAngers[roleIndex - 3]++;
                    }
                    // 判断胜负
                    isAllHeroesDead = true;
                    for (int j = 0; j < heroPositions.length; j++) {
                        if (heroPositions[j] > 0 && heroHp[j] > 0) {
                            isAllHeroesDead = false;
                            break;
                        }
                    }
                    if (isAllHeroesDead) {
                        System.out.println("你输了，重新挑战本关卡！");
                        break;
                    }
                    isAllEnemiesDead = true;
                    for (int j = 0; j < enemyHp.length; j++) {
                        if (enemyHp[j] > 0) {
                            isAllEnemiesDead = false;
                            break;
                        }
                    }
                    if (isAllEnemiesDead) {
                        System.out.println("你赢了！");
                        // 获得经验和掉落宝石
                        for (int j = 0; j < heroPositions.length; j++) {
                            if (heroPositions[j] > 0) {
                                heroExp[j] += enemyExp[j - 3];
                                if (heroExp[j] >= heroMaxExp[j] && heroLevels[j] < 10) {
                                    heroLevels[j]++;
                                    heroExp[j] -= heroMaxExp[j];
                                    heroHp[j] += 100;
                                    heroAttack[j] += 10;
                                    heroDefense[j] += 5;
                                    System.out.println(heroNames[j] + " 升级了！");
                                }
                                if (random.nextInt(100) < 20) {
                                    dropHeroIndex = j;
                                }
                            }
                        }
                        break;
                    }
                }
                if (dropHeroIndex >= 0) {
                    System.out.println(heroNames[dropHeroIndex] + " 掉落了一颗宝石！");
                }
                if (isAllHeroesDead || isAllEnemiesDead) {
                    break;
                }
                roundCount++;
            }
            currentLevel++;
        }

        // 宝石增加属性及宝石合成功能
        int[] gemTypes = {1, 2, 3}; // 1:加血 2:加攻击 3:加防御
        int[] gemLevels = {1, 1, 1};
        int[] gemValues = {10, 5, 3}; // 一级宝石数值

        int[] gems = {-1, -1, -1, -1, -1, -1};
        boolean isAddGem = true;
        while (isAddGem) {
            int gemType = -1;
            int gemIndex = -1;
            System.out.println("请选择要装备宝石的英雄：");
            for (int i = 0; i < heroPositions.length; i++) {
                if (heroPositions[i] > 0) {
                    System.out.println(i + ": " + heroNames[i]);
                }
            }
            int heroIndex = scanner.nextInt();
            for (int i = 0; i < gems.length; i++) {
                if (gems[i] < 0) {
                    gemIndex = i;
                    break;
                }
            }
            if (gemIndex < 0) {
                System.out.println("你已经装备了最大数量的宝石，是否升级宝石？");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    System.out.println("请选择要升级的宝石：");
                    for (int i = 0; i < gems.length; i++) {
                        if (gems[i] >= 0) {
                            System.out.println(i + ": " + gemTypes[gems[i]] + " 级宝石");
                        }
                    }
                    gemIndex = scanner.nextInt();
                    gemLevels[gems[gemIndex]]++;
                    gemValues[gems[gemIndex]] += 5;
                } else {
                    isAddGem = false;
                }
            } else {
                System.out.println("请选择要装备的宝石类型：");
                for (int i = 0; i < gemTypes.length; i++) {
                    System.out.println(i + ": " + gemTypes[i] + " 类宝石");
                }
                gemType = scanner.nextInt();
                System.out.println("你装备了 " + gemTypes[gemType] + " 级宝石！");
                gems[gemIndex] = gemType;
            }
        }
        // 输出英雄属性和宝石
        System.out.println("己方英雄属性：");
        for (int i = 0; i < heroPositions.length; i++) {
            if (heroPositions[i] > 0) {
                System.out.println(heroNames[i] + ", Level " + heroLevels[i] + ", HP " + heroHp[i] + ", Attack " + heroAttack[i] + ", Defense " + heroDefense[i]);
                if (gems[i] >= 0) {
                    System.out.println("      " + gemTypes[gems[i]] + " 级宝石，加 " + gemValues[gems[i]] + " 点 " + (gemTypes[gems[i]] == 1 ? "血量" : gemTypes[gems[i]] == 2 ? "攻击" : "防御"));
                }
            }
        }
    }
}
