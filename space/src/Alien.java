import java.util.Scanner;

public class Alien extends SpaceObject {
    private String image = "\uD83D\uDC7D";

    Alien(int mapSize) {
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
        System.out.println("👽 Встречен инопланетный корабль! Решите задачу для коммуникации:");

        if (difficulty == 1) {
            return super.handleEncounter(difficulty);
        } else {
            int x = r.nextInt(5 * difficulty, 10 * difficulty);
            int y = r.nextInt(2, 5);
            int z = r.nextInt(10 * difficulty, 20 * difficulty);
            int correctAnswer = x * y + z;
            System.out.println("Расшифруйте сигнал: " + x + " × " + y + " + " + z + " = ?");
            Scanner sc = new Scanner(System.in);
            int answer = sc.nextInt();
            if (answer == correctAnswer) {
                System.out.println("✓ Коммуникация успешна! Инопланетяне дружелюбны.");
                return true;
            }
            System.out.println("❌ Неверная расшифровка! Инопланетяне атакуют.");
            return false;
        }
    }
}