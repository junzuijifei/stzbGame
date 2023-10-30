package hero_test;

import java.util.ArrayList;
import java.util.Iterator;

public class Test1 {
    public static void main(String[] args) {
        DatabaseReader databaseReader = new DatabaseReader();
        ArrayList<Hero> heroes = databaseReader.readHero();
        Iterator<Hero> iterator = heroes.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
