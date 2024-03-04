/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Daos;

import Model.UnitModel;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class UnitDAOTest {

    UnitDAO getUnit;

    public UnitDAOTest() {
        getUnit = new UnitDAO();
    }

    @Test
    public void testUnitSuccessful() {
        int UnitID = 7; // Giá trị mong đợi của UnitID
        UnitModel unit = getUnit.getUnit(UnitID); // Gọi phương thức getUnit để lấy đối tượng UnitModel
        assertEquals(UnitID, unit.getUnitID());
    }
    @Test
    public void testUnitWrong() {
        int UnitID = 0; // Giá trị mong đợi của UnitID
        UnitModel unit = getUnit.getUnit(UnitID); // Gọi phương thức getUnit để lấy đối tượng UnitModel
        assertEquals(UnitID, unit.getUnitID());
    }
    @Test
    public void testUnitNull() {
        Integer UnitID = null; // Giá trị mong đợi của UnitID
        UnitModel unit = getUnit.getUnit(UnitID); // Gọi phương thức getUnit để lấy đối tượng UnitModel
        assertEquals(null, unit.getUnitID());
    }
}
