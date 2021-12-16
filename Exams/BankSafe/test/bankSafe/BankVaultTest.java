package bankSafe;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BankVaultTest {
    private BankVault bankVault;
    private Item firstItem;
    private Item secondItem;

    @Before
    public void setUp(){
        bankVault = new BankVault();
        firstItem = new Item("Jason", "21");
        secondItem = new Item("Jason2","22");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetVaultCellsShouldThrowException() {
        Map<String, Item> vaultCells = bankVault.getVaultCells();

        vaultCells.put("C3", firstItem);
    }

    @Test
    public void testAddItemCorrectly() throws OperationNotSupportedException {
        String actual = bankVault.addItem("C3", firstItem);
        String expected = String.format("Item:%s saved successfully!", firstItem.getItemId());

        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemInANonExistingCellShouldThrowException()
            throws OperationNotSupportedException {

        bankVault.addItem("C3414", firstItem);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddingExistingItem() throws OperationNotSupportedException {
        bankVault.addItem("C3", firstItem);
        bankVault.addItem("C4", firstItem);
    }

    @Test
    public void testRemovingItemsCorrectly() throws OperationNotSupportedException {
        bankVault.addItem("C3", firstItem);
        bankVault.addItem("C4", secondItem);

        String actual = bankVault.removeItem("C3", firstItem);
        String expected = String.format("Remove item:%s successfully!", firstItem.getItemId());
        
        Assert.assertFalse(bankVault.getVaultCells().containsValue(firstItem));
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNotNullCell() throws OperationNotSupportedException {
        bankVault.addItem("C4", firstItem);
        bankVault.addItem("C4", secondItem);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveItemNotExistingCellShouldThrowException() throws OperationNotSupportedException {
        bankVault.addItem("C3", firstItem);
        bankVault.removeItem("C4", firstItem);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemovingItemNotExistingShouldThrowException() {
        bankVault.removeItem("C3", firstItem);
    }


}