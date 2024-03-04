/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Daos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Thai Vinh
 */
public class cate_adTest {

    cate_ad testCate;

    public cate_adTest() {
        testCate = new cate_ad();
    }

    @org.junit.jupiter.api.Test
    public void testAddNewCategory() {
        int rs = testCate.addNewCategory("Vật dụng y tế", "Chỉ nha khoa, băng keo cá nhân...");
        int expectedResult = 1;
        assertEquals(expectedResult, rs);
    }

    @org.junit.jupiter.api.Test
    public void test2() {
        int rs = testCate.addNewCategory("Vitamin", null);
        int expectedResult = 1;
        assertEquals(expectedResult, rs);
    }

    @org.junit.jupiter.api.Test
    public void test3() {
        int rs = testCate.addNewCategory("Thực phẩm chức năng", "5000");
        int expectedResult = 1;
        assertEquals(expectedResult, rs);
    }

    @org.junit.jupiter.api.Test
    public void test4() {
        int rs = testCate.addNewCategory("Thuốc điều trị", "sản phẩm được cấp phép bỏi bộ y tế");
        int expectedResult = 1;
        assertEquals(expectedResult, rs);
    }

}
