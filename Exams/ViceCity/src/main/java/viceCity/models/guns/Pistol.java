package viceCity.models.guns;

public class Pistol extends BaseGun {
    private static final int DEFAULT_FIRE_BULLETS = 1;
    private static int BULLETS_PER_BARREL = 10;
    private static int TOTAL_BULLETS = 100;

    public Pistol(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public boolean canFire() {
        return this.getTotalBullets() >= DEFAULT_FIRE_BULLETS;
    }

    @Override
    public int fire() {
        if (this.getBulletsPerBarrel() >= DEFAULT_FIRE_BULLETS) {
            this.setBulletsPerBarrel(this.getBulletsPerBarrel() - DEFAULT_FIRE_BULLETS);
        }

        if (this.getBulletsPerBarrel() == 0) {
            if (this.getTotalBullets() <= 0) {
                return 0;
            }

            this.setTotalBullets(this.getTotalBullets() - BULLETS_PER_BARREL);
            this.setBulletsPerBarrel(BULLETS_PER_BARREL);
        }

        return DEFAULT_FIRE_BULLETS;
    }
}
