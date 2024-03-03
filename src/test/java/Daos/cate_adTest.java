/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Daos;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author qn699
 */
public class cate_adTest {
    cate_ad callFunc;
    public cate_adTest() {
        callFunc = new cate_ad();
    }

    @Test
    public void testGetAll() {
        int rs = callFunc.addNewCategory("", "cac thiet bi y te");
        int expected = 0;
        assertEquals(expected, rs);
    }

    @Test
    public void testUpdateCategory() {
        
    }

    @Test
    public void testAddNewCategory() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testGetCate() {
    }

    @Test
    public void testMain() {
    }
    
}
