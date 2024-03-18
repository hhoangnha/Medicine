/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xuly;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Thai Vinh
 */
public class SaveToSession {
    // Hàm tiện ích để lưu các giá trị vào session

    public void saveToSession(HttpServletRequest request, String... values) {
        HttpSession session = request.getSession();
        String[] keys = {"manuName", "manuLicense", "manuAddress", "manuPhone"};

        for (int i = 0; i < keys.length && i < values.length; i++) {
            session.setAttribute(keys[i], values[i]);
        }
    }

}
