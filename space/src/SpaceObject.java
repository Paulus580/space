import java.util.Random;
import java.util.Scanner;

public class SpaceObject {
    private String image = "❓";
    private final int x, y;
    Random r = new Random();

    SpaceObject(int mapSize){
        this.y = r.nextInt(mapSize - 1);
        this.x = r.nextInt(mapSize);
    }

    public String getImage() {
        return image;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean conflictWithExplorer(int expX, int expY){
        return expY - 1 == this.y && expX - 1 == this.x;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean handleEncounter(int difficulty){
        System.out.println("🚨 Обнаружен космический объект!");
        int a = r.nextInt(20 * difficulty, 30 * difficulty);
        int b = r.nextInt(10 * difficulty, 20 * difficulty);
        int correctAnswer = a - b;
        System.out.println("Решите задачу для навигации: " + a + " - " + b + " = ?");
        Scanner sc = new Scanner(System.in);
        int answer = sc.nextInt();
        if (answer == correctAnswer) {
            System.out.println("✓ Навигация успешна! Препятствие преодолено.");
            return true;
        }
        System.out.println("❌ Ошибка в расчетах!");
        return false;
    }
}