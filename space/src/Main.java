import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String spaceBase = "\uD83C\uDFE0";
        int sizeBoard = 6;

        SpaceExplorer explorer = new SpaceExplorer(sizeBoard);

        int step = 0;

        String[][] spaceMap = new String[sizeBoard][sizeBoard];
        for (int y = 0; y < sizeBoard; y++) {
            for (int x = 0; x < sizeBoard; x++) {
                spaceMap[y][x] = "  ";
            }
        }

        int spaceObjectsCount = sizeBoard * sizeBoard - sizeBoard - 4;
        Random r = new Random();

        SpaceObject[] spaceObjects = new SpaceObject[spaceObjectsCount + 1];
        int count = 0;
        SpaceObject obj;
        while (count <= spaceObjectsCount){
            if (r.nextBoolean()) {
                obj = new Alien(sizeBoard);
            } else {
                obj = new Asteroid(sizeBoard);
            }
            if (spaceMap[obj.getY()][obj.getX()].equals("  ")){
                spaceMap[obj.getY()][obj.getX()] = obj.getImage();
                spaceObjects[count] = obj;
                count++;
            }
        }

        int baseX = r.nextInt(sizeBoard);
        int baseY = 0;
        spaceMap[baseY][baseX] = spaceBase;

        System.out.println("🚀 Добро пожаловать в игру 'Космический исследователь'!");
        System.out.println("Ты готов отправиться в космическое путешествие? (ДА/НЕТ)");

        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        System.out.println("Ваш ответ: " + answer);

        switch (answer.toUpperCase()) {
            case "ДА" -> {
                System.out.println("Выбери сложность миссии (от 1 до 5):");
                int missionDifficulty = sc.nextInt();
                System.out.println("Сложность миссии: " + missionDifficulty);

                while (explorer.getEnergy() > 0) {
                    spaceMap[explorer.getY() - 1][explorer.getX() - 1] = explorer.getImage();
                    displaySpaceMap(spaceMap, explorer.getEnergy(), step);
                    System.out.println("Введите координаты для перемещения (x y):");
                    System.out.println("Текущие координаты - (x: " + explorer.getX() + ", y: " + explorer.getY() + ")");
                    int x = sc.nextInt();
                    int y = sc.nextInt();

                    if (explorer.isValidMove(x, y)) {
                        String target = spaceMap[y - 1][x - 1];
                        if (target.equals("  ")) {
                            spaceMap[explorer.getY() - 1][explorer.getX() - 1] = "  ";
                            explorer.move(x, y);
                            step++;
                            System.out.println("✓ Перемещение успешно! Новые координаты: " + explorer.getX() + ", " + explorer.getY());
                        } else if (target.equals(spaceBase)) {
                            System.out.println("🎉 Поздравляем! Вы достигли космической базы!");
                            break;
                        } else {
                            for (SpaceObject spaceObj : spaceObjects) {
                                if (spaceObj != null && spaceObj.conflictWithExplorer(x, y)) {
                                    if (spaceObj.handleEncounter(missionDifficulty)) {
                                        spaceMap[explorer.getY() - 1][explorer.getX() - 1] = "  ";
                                        explorer.move(x, y);
                                        System.out.println("✓ Вы преодолели препятствие!");
                                    } else {
                                        explorer.loseEnergy();
                                        System.out.println("⚠ Вы потеряли энергию! Осталось энергии: " + explorer.getEnergy());
                                    }
                                    break;
                                }
                            }
                        }
                    } else {
                        System.out.println("❌ Некорректное перемещение! Можно двигаться только на 1 клетку по вертикали или горизонтали.");
                    }

                    if (explorer.getEnergy() <= 0) {
                        System.out.println("💀 Ваш корабль потерял всю энергию! Миссия провалена.");
                        break;
                    }
                }
            }
            case "НЕТ" -> System.out.println("Ждем вас в следующей миссии!");
            default -> System.out.println("❌ Неверный ответ");
        }
        sc.close();
    }

    static void displaySpaceMap(String[][] map, int energy, int step) {
        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— + —— + —— + —— + —— + —— +";

        System.out.println("\n🌌 КОСМИЧЕСКАЯ КАРТА:");
        for (String[] row : map) {
            System.out.println(wall);
            for (String cell : row) {
                System.out.print(leftBlock + cell + " ");
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);

        System.out.println("⚡ Энергия корабля: " + energy);
    }
}