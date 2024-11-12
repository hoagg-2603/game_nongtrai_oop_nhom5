package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.crops.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Check {
    // Kiểm tra xem đầu vào khi đăng nhập, đăng ký, thay đổi thông tin có đúng không

    // Độ mạnh của mật khẩu thấp nếu ít hơn hoặc bằng 5 ký tự
    public static boolean checkPassworst(String pass){
       if(pass.length()>5)
           return true;
       return false;

    }

    //Độ mạnh của mật khẩu trung bình khi trên 5 ký tự, bao gồm cả số và chữ cái
    public static boolean checkPassmiddle(String pass){
        boolean isDight = false;//Biến boolean để kiểm tra có chứa số không
        boolean isLetter = false;//Biến boolean để kiểm tra có chứa chữ cái không
        for (int i = 0; i < pass.length(); i++) {
            if (Character.isDigit(pass.charAt(i))) {//Kiểm tra từng ký tự là số
                isDight = true;
            }
            if (Character.isLetter(pass.charAt(i))) {//Kiểm tra từng ký tự là chữ cái
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9@#$%^&*!()]{5,}$";
        boolean isRight = isDight && isLetter && pass.matches(regex);
        return isRight;
    }

    //Kiểm tra xem có lưu thông tin tài khoản cục bộ không
    public static boolean checkLocalUserExist()
    {
        return Gdx.files.internal("user/user.json").exists();
    }



    //Độ mạnh của mật khẩu cao khi có ít nhất 1 chữ hoa, 1 chữ thường, 1 số và 1 ký tự đặc biệt
    public static boolean checkPassbest(String pass) {
        boolean isDight = false;//Biến boolean để kiểm tra có chứa số không
        boolean isLetter = false;//Biến boolean để kiểm tra có chứa chữ cái không
        for (int i = 0; i < pass.length(); i++) {
            if (Character.isDigit(pass.charAt(i))) {//Kiểm tra từng ký tự là số
                isDight = true;
            }
            if (Character.isLetter(pass.charAt(i))) {//Kiểm tra từng ký tự là chữ cái
                isLetter = true;
            }
        }
        String regex = "^.*(?=.{10,})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$";
        boolean isRight =pass.matches(regex);
        return isRight;
    }



   //Kiểm tra xem ID người dùng có hợp lệ không, từ 5-12 ký tự
    public static boolean checkID(String num) {

        String regex = "^[a-zA-Z0-9]{5,12}";
        boolean isRight = num.matches(regex);
        return isRight;
    }


    // Kiểm tra xem tài khoản đã tồn tại trong cơ sở dữ liệu chưa
    public static boolean checkIDExistFromSQL(String userID) throws SQLException {
        String id=null;
        if(SQLConnector.getConn()!=null)
        {
            ResultSet rs=SQLConnector.readData("select userID,Password from user");
            while (rs.next())
            {
                id=rs.getString("userID");
                if(id.equals(userID))
                    break;
            }
            if(id.equals(userID)){
                return true;
            }
            return false;
        }
        else {
            if(!Gdx.files.internal("user/"+userID+"/user.json").exists())
                return false;
            return true;
        }


    }

    //Kiểm tra xem mật khẩu xác minh có chính xác không
    public static boolean checkVerifyPass(String pass,String veriPass)
    {
        return pass.equals(veriPass);

    }

    //Kiểm tra xem tài khoản và mật khẩu có chính xác không
    public static int checkLogin(String userID,String pass) throws SQLException {
        int USERIDNOTEXIST=0;
        int PASSWORDERROR=1;
        int LOGINSUCEESS=2;
        String id=null;
        if(SQLConnector.getConn()!=null)
        {
            ResultSet rs=SQLConnector.readData("select userID,Password from user");
            while (rs.next())
            {
                id=rs.getString("userID");
                if(id.equals(userID))
                    break;
            }
            if(!id.equals(userID)){
                return USERIDNOTEXIST;
            }
            else {
                String password=rs.getString("Password");
                rs.close();
                if (password.equals(pass))
                    return LOGINSUCEESS;
                return PASSWORDERROR;
            }
        }
        else {
            Json json=new Json();
            FileHandle userFile;
            if(!Gdx.files.internal("user/"+userID+"/user.json").exists())
                return USERIDNOTEXIST;
            else {
                userFile=Gdx.files.internal("user/"+userID+"/user.json");
                User user=json.fromJson(User.class, userFile);
                if(user.getPass().equals(pass))
                    return LOGINSUCEESS;
            }
            return PASSWORDERROR;
        }


    }

    // Kiểm tra tên người dùng
    public static boolean checkusername(String username){
        return username.equals("");
    }

    //Kiểm tra mật khẩu có rỗng không
    public static boolean checkPass(String pass){
        return pass.equals("");
    }
}

