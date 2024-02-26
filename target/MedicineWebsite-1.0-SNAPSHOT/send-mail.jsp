<%@ page import="java.util.Properties" %>
<%@ page import="javax.mail.*" %>
<%@ page import="javax.mail.internet.*" %>

<%
    final String username = "0939165008hoang@gmail.com"; // Replace with your email
    final String password = "jwwl clsp wspt lltj"; // Replace with your password

    String to = "dangtrandangkhoa3715@gmail.com"; // Replace with the recipient's email
    String from = "0939165008hoang@gmail.com"; // Replace with your email

    String mailHost = "smtp.gmail.com"; // Or your mail server host

    Properties mailProperties = System.getProperties();
    mailProperties.setProperty("mail.smtp.host", mailHost);
    mailProperties.setProperty("mail.smtp.auth", "true");
    mailProperties.setProperty("mail.smtp.port", "587"); // Port might change based on your mail provider
    mailProperties.put("mail.smtp.starttls.enable", "true");
    Session mailSession = Session.getInstance(mailProperties, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    try {
        MimeMessage message = new MimeMessage(mailSession);

        message.setFrom(new InternetAddress(from));

        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        message.setSubject("Subject of the Email");

        message.setText("This is the content of the email.");

        Transport.send(message);
        out.println("Email sent successfully");
    } catch (MessagingException mex) {
        mex.printStackTrace();
        out.println("Email failed to send");
    }
%>
