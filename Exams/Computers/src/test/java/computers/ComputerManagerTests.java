package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ComputerManagerTests {
    private ComputerManager computerManager;
    private Computer computer = new Computer("DELL", "A4532", 300.00);
    private Computer computer2 = new Computer("ASUS", "ROG", 500.00);

    @Before
    public void setUp() {
        this.computerManager = new ComputerManager();
        computer = new Computer("DELL", "A4532", 300.00);
        computer2 = new Computer("ASUS", "ROG", 500.00);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetComputersShouldReturnList() {
        computerManager.getComputers().remove(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddComputerAddingNullShouldThrowException() {
        this.computerManager.addComputer(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddComputerAddingExistingComputerShouldThrowException() {
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer);
    }

    @Test
    public void testAddComputerCorrectly() {
        this.computerManager.addComputer(computer);

        Assert.assertEquals(1, this.computerManager.getCount());
    }

    @Test
    public void testRemoveComputerShouldRemoveCorrectly() {
        this.computerManager.addComputer(computer);
        this.computerManager.addComputer(computer2);

        Assert.assertNotNull(computerManager);

        this.computerManager.removeComputer(computer.getManufacturer(), computer.getModel());

        Assert.assertEquals(1, computerManager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetManufacturerIsNullShouldThrowException() {
        this.computerManager.getComputer(null, "test_model");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetModelIsNullShouldThrowException() {
        this.computerManager.getComputer("test_model2", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNonExistingComputerShouldThrowException() {
        this.computerManager.getComputer(computer.getManufacturer(), computer.getModel());
    }

    @Test
    public void testGetMethodReturnCorrectData() {
        computerManager.addComputer(computer);
        computerManager.addComputer(computer2);
        Computer returned = this.computerManager.getComputer(this.computer.getManufacturer(),
                this.computer.getModel());

        Assert.assertNotNull(returned);

        Assert.assertEquals(computer.getManufacturer(), returned.getManufacturer());
        Assert.assertEquals(computer.getModel(), returned.getModel());
    }

    @Test
    public void testGetComputersByManufacturer() {
        computerManager.addComputer(computer);
        computerManager.addComputer(computer2);

        List<Computer> list = computerManager.getComputersByManufacturer(computer.getManufacturer());
        Assert.assertNotNull(list);
        Assert.assertEquals(list.get(0).getManufacturer(), computer.getManufacturer());
    }

    @Test
    public void testGetComputersByManufacturerWhenEmpty() {
        List<Computer> list = computerManager
                .getComputersByManufacturer(computer.getManufacturer());

        Assert.assertNotNull(list);
        Assert.assertTrue(list.isEmpty());

    }
}