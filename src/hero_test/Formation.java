package hero_test;

import java.util.List;

public class Formation {
    private List<Hero> heroes;

    public Formation(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public void changeFormation() {
        // 实现不同的站位策略
    }
}
