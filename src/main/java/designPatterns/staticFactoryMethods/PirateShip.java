package designPatterns.staticFactoryMethods;

public class PirateShip implements EnemyShip{
    @Override
    public void attack() {
        System.out.println("Pirate ship attack");
    }
}
