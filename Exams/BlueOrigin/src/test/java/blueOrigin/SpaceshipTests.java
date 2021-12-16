package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    private Spaceship spaceship;
    private Astronaut astronaut1;
    private Astronaut astronaut2;
    private Astronaut astronaut3;

    @Before
    public void setUp() {
        this.spaceship = new Spaceship("KorabaMaika", 2);
        this.astronaut1 = new Astronaut("Pesho", 20);
        this.astronaut2 = new Astronaut("Gosho", 10);
        this.astronaut3 = new Astronaut("Genadi", 5);
    }


    @Test
    public void testConstructorCorrectly() {
        Assert.assertEquals("KorabaMaika", spaceship.getName());
        Assert.assertEquals(2, spaceship.getCapacity());
        Assert.assertEquals(0, spaceship.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullName() {
        new Spaceship(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorEmptyName() {
        new Spaceship("", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContructorBelowZeroCapacity() {
        new Spaceship("Ivan", -5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNoCapacity() {
        spaceship.add(astronaut1);
        spaceship.add(astronaut2);
        spaceship.add(astronaut3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddExistingAstronaut() {
        spaceship.add(astronaut1);
        spaceship.add(astronaut1);
    }

    @Test
    public void testAddCorrectly() {
        spaceship.add(astronaut1);
        spaceship.add(astronaut2);
        Assert.assertEquals(spaceship.getCount(), 2);
    }

    @Test
    public void testRemoveExistingAstronaut() {
        spaceship.add(astronaut1);
        spaceship.add(astronaut2);
        boolean result = spaceship.remove(astronaut1.getName());
        Assert.assertTrue(result);
        Assert.assertEquals(spaceship.getCount(), 1);
    }

    @Test
    public void testRemoveNonExistingAstronaut() {
        spaceship.add(astronaut1);
        boolean result = spaceship.remove(astronaut2.getName());
        Assert.assertEquals(spaceship.getCount(), 1);
        Assert.assertFalse(result);
    }
}
