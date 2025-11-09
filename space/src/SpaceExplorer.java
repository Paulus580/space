import java.util.Random;

public class SpaceExplorer {
    protected int x, y;
    private String image = "\uD83D\uDE80"; // Ракета
    private int energy = 5;
    Random r = new Random();

    SpaceExplorer(int mapSize) {
        y = mapSize;
        int n = r.nextInt(mapSize);
        x = n == 0 ? 1 : n;
    }

    SpaceExplorer(int x, int y){
        this.x = x;
        this.y = y;
    }

    SpaceExplorer(){
        this(1, 1);
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public int getEnergy() {
        return energy;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isValidMove(int x, int y){
        return this.x == x && Math.abs(this.y - y) == 1 || this.y == y && Math.abs(this.x - x) == 1;
    }

    void move(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void loseEnergy(){
        energy--;
    }

    public void gainEnergy() {
        energy++;
    }
}