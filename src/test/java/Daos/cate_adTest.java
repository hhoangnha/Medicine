/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Daos;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ngo Phuc Vinh - CE170573
 */
public class cate_adTest {

    cate_ad update;

    public cate_adTest() {
        update = new cate_ad();
    }

    @Test
    public void testUpdateCategorySuccesfull() {
        int cateid = 1;
        String cateName = "Vật Tư Y Tế";
        String cateDescription = "Bông băng, thuốc đỏ,...";
        int result = update.updateCategory(cateid, cateName, cateDescription);
        assertEquals(1, result);
    }

    @Test
    public void testUpdateCategoryWrongIDerror() {
        int cateid = 0;
        String cateName = "Thực Phẩm Chức Năng";
        String cateDescription = "Bổ phận tráng dương...";
        int result = update.updateCategory(cateid, cateName, cateDescription);
        assertEquals(1, result);
    }

    @Test
    public void testUpdateCategoryNullCatename() {
        int cateid = 2;
        String cateDescription = "Kim tiêm...";
        int result = update.updateCategory(cateid, null, cateDescription);
        assertEquals(1, result);
    }

    @Test
    public void testUpdateCategoryNullCateDescription() {
        int cateid = 4;
        String cateName = "Thuốc bổ mắt";
        int result = update.updateCategory(cateid, cateName, null);
        assertEquals(1, result);
    }

    @Test
    public void testUpdateCategoryDupliCateID() throws SQLException {
        int existCateID = 4;
        String exitCateName = "Thuốc bổ mắt";
        int cateid = 3;
        String cateName = "Thuốc bổ mắt";
        if (existCateID == cateid && exitCateName.equals(cateName)) {
            int result = update.updateCategory(cateid, cateName, null);
            assertEquals(1, result);
        } else {
            int result1 = update.checkDuplicateName(cateName);
            assertEquals(0, result1);
//            System.out.println("test" + result1);
        }
    }
      @Test
    public void testUpdateCateNameandCateIDoverlap() throws SQLException {
        int existCateID = 4;
        String exitCateName = "Thuốc bổ mắt";
        int cateid = 4;
        String cateName = "Thuốc bổ mắt";
        if (existCateID == cateid && exitCateName.equals(cateName)) {
            int result = update.updateCategory(cateid, cateName, null);
            assertEquals(1, result);
        } else {
            int result1 = update.checkDuplicateName(cateName);
            assertEquals(0, result1);
        }
    }
}
