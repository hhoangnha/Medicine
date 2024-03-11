/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xuly;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Thai Vinh
 */
public class CheckingNumber {

    public boolean isValidPhoneNumber(String phoneNumber) {
        // Định dạng số điện thoại: 10 chữ số (hoặc 11 nếu có mã quốc gia ok ko)
        Pattern pattern = Pattern.compile("^[0-9]{10,11}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public  boolean isValidID(String id) {
        // Biểu thức chính quy để kiểm tra số căn cước công dân
        String regex = "^\\d{12}$"; // Chuỗi gồm 12 chữ số

        // Biên dịch biểu thức chính quy
        Pattern pattern = Pattern.compile(regex);

        // Tạo một Matcher để kiểm tra chuỗi đầu vào
        Matcher matcher = pattern.matcher(id);

        // Kiểm tra xem chuỗi đầu vào có khớp với biểu thức chính quy không
        return matcher.matches();
    }

    public static void main(String[] args) {
        CheckingNumber test = new CheckingNumber();
        System.out.println(test.isValidPhoneNumber("0982989262"));
    }
}
