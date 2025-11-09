import java.util.Scanner;

public class Asteroid extends SpaceObject {
    private String image = "\uD83C\uDF12";

    Asteroid(int mapSize) {
        super(mapSize);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean handleEncounter(int difficulty){
        System.out.println("☄️ На пути поле астероидов! Рассчитайте траекторию:");

        if (difficulty <= 2) {
            return super.handleEncounter(difficulty);
        } else {
            int a = r.nextInt(50 * difficulty, 100 * difficulty);
            int b = r.nextInt(20 * difficulty, 40 * difficulty);
            int c = r.nextInt(5, 15);
            int correctAnswer = (a + b) / c;
            System.out.println("Рассчитайте: (" + a + " + " + b + ") ÷ " + c + " = ?");
            Scanner sc = new Scanner(System.in);
            int answer = sc.nextInt();
            if (answer == correctAnswer) {
                System.out.println("✓ Траектория рассчитана! Пролетаем безопасно.");
                return true;
            }
            System.out.println("❌ Ошибка в расчетах! Столкновение с астероидом.");
            return false;
        }
    }
}