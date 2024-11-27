package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.crops.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataManage {
    // Lớp quản lý dữ liệu, chịu trách nhiệm ghi dữ liệu vào cơ sở dữ liệu và đọc dữ liệu từ cơ sở dữ liệu

    // Lưu thông tin người dùng vào cơ sở dữ liệu
    public static void saveUserDataToSQL(User user) throws SQLException {
        if(SQLConnector.getConn()!=null)
        {
            Json json = new Json();
            String userData = json.toJson(user);//Ghi đối tượng vào chuỗi json
            String userID = user.getUserID();
            ResultSet rs = SQLConnector.readData("select userID from user");
            Boolean exist = false;
            while (rs.next()) {
                // Kiểm tra theo trường
                String id = rs.getString("userID");
                if (id.equals(userID)) {
                    exist = true;
                    break;
                }

            }
            rs.close();
            if (exist) {
                SQLConnector.writeData("update user set userData='" + userData + "',Password='"+user.getPass()+"' where userID='" + userID + "'");
            } else {
                SQLConnector.writeData("insert into user(userID,Password,userData) values ('" + userID + "','" + user.getPass() + "','" + userData + "')");
            }
        }
        else
            saveUserMsgToLocal(user);

    }

    //Đọc thông tin người dùng từ cơ sở dữ liệu
    public static User readUserDataFromSQL(String userID) throws SQLException {
        User user = null;
        if(SQLConnector.getConn()!=null)
        {
            String userData = null;
            Json json = new Json();
            ResultSet rs = SQLConnector.readData("select userData from user where userID='" + userID + "'");
            while (rs.next()) {
                userData = rs.getString("userData");
            }
            rs.close();
            user = json.fromJson(User.class, userData);
        }
        else
            user=readUserMsgFromLocal(userID);
        return user;

    }


    // Lưu dữ liệu thời gian vào cơ sở dữ liệu
    public static void saveTimerDataToSQL(Timer timer, String userID) throws SQLException {
        if(SQLConnector.getConn()!=null)
        {
            Json json = new Json();
            String timerData = json.toJson(timer);
            SQLConnector.writeData("update user set TimerData='" + timerData + "'where userID='" + userID + "'");
        }
        else
            saveTimer(timer, userID);
    }

    //Đọc dữ liệu thời gian từ cơ sở dữ liệu
    public static Timer readTimerDataFromSQL(String userID) throws SQLException {
        Timer timer = null;
        if(SQLConnector.getConn()!=null)
        {
            Json json = new Json();
            ResultSet rs = SQLConnector.readData("select * from user where userID='" + userID + "'");
            String timerData = null;
            while (rs.next()) {
                timerData = rs.getString("TimerData");


            }
            timer = json.fromJson(Timer.class, timerData);
            rs.close();
        }
        else
            timer=readTimer(userID);
        return timer;

    }

    public static void saveCropDataToSQL(Array<Crop> cropArray, String userID) throws SQLException {
        if(SQLConnector.getConn()!=null)
        {
            Json json = new Json();
            String cropData = json.toJson(cropArray);
            SQLConnector.writeData("update user set CropData='" + cropData + "'where userID='" + userID + "'");
        }
        else
            saveCrop(cropArray, userID);

    }

    //Các phương thức còn lại tiếp tục dịch theo cùng cách như vậy
    public static Array<Crop> readCropDataFromSQL(String userID) throws SQLException {

        Array<Crop> cropArray = null;
        if(SQLConnector.getConn()!=null){

            Json json = new Json();
            ResultSet rs = SQLConnector.readData("select * from user where userID='" + userID + "'");
            String cropData = null;
            while (rs.next()) {
                cropData = rs.getString("CropData");

            }

            cropArray = json.fromJson(Array.class, Crop.class, cropData);
            rs.close();
        }
        else
            cropArray=readCrop(userID);
        return cropArray;
    }

    //Lưu trữ dữ liệu trái cây vào cơ sở dữ liệu
    public static void saveFruitDataToSQL(Array<Fruit> fruitArray, String userID) throws SQLException {
        if(SQLConnector.getConn()!=null){
            Json json = new Json();
            String fruitData = json.toJson(fruitArray);
            SQLConnector.writeData("update user set FruitData='" + fruitData + "'where userID='" + userID + "'");
        }
        else
            saveFruit(fruitArray, userID);

    }

    //Đọc dữ liệu trái cây từ cơ sở dữ liệu
    public static Array<Fruit> readFruitDataFromSQL(String userID) throws SQLException {
        Array<Fruit> fruitArray = null;
        if(SQLConnector.getConn()!=null){
            Json json = new Json();
            ResultSet rs = SQLConnector.readData("select * from user where userID='" + userID + "'");
            String fruitData = null;
            while (rs.next()) {
                fruitData = rs.getString("FruitData");

            }

            fruitArray = json.fromJson(Array.class, Fruit.class, fruitData);
            rs.close();
        }
        else
            fruitArray=readFruit(userID);

        return fruitArray;
    }

    //Lưu trữ dữ liệu prop vào cơ sở dữ liệu
    public static void savePropDataToSQL(Array<Prop> propArray, String userID) throws SQLException {
        if(SQLConnector.getConn()!=null){
            Json json = new Json();
            String propData = json.toJson(propArray);
            SQLConnector.writeData("update user set PropData='" + propData + "'where userID='" + userID + "'");
        }
        else saveProp(propArray, userID);

    }

    //Đọc dữ liệu prop từ cơ sở dữ liệu
    public static Array<Prop> readPropDataFromSQL(String userID) throws SQLException {
        Array<Prop> propArray = null;
        if(SQLConnector.getConn()!=null){
            Json json = new Json();
            ResultSet rs = SQLConnector.readData("select * from user where userID='" + userID + "'");
            String propData = null;
            while (rs.next()) {
                propData = rs.getString("PropData");

            }

            propArray = json.fromJson(Array.class, Prop.class, propData);
            rs.close();
        }
        else
            propArray=readProp(userID);
        return propArray;
    }

    //Lưu trữ dữ liệu đất đai vào cơ sở dữ liệu
    public static void saveLandDataToSQL(Array<Land> landArray, String userID) throws SQLException {
        if(SQLConnector.getConn()!=null){
            Json json = new Json();
            String landData = json.toJson(landArray);
            SQLConnector.writeData("update user set LandData='" + landData + "'where userID='" + userID + "'");
        }
        else saveLand(landArray, userID);
    }

    //Đọc dữ liệu đất đai từ cơ sở dữ liệu
    public static Array<Land> readLandDataFromSQL(String userID) throws SQLException {
        Array<Land> landArray = null;
        if(SQLConnector.getConn()!=null){
            Json json = new Json();
            ResultSet rs = SQLConnector.readData("select * from user where userID='" + userID + "'");
            String landData = null;
            while (rs.next()) {
                landData = rs.getString("LandData");

            }

            landArray = json.fromJson(Array.class, Land.class, landData);
            rs.close();
        }
        else landArray=readLand(userID);

        return landArray;
    }


    //Khởi tạo thông tin tài khoản sau khi đăng ký tài khoản
    public static void initialUser(String userID) {
        Json json = new Json();
        Timer timer = new Timer();

        //Dữ liệu cần được khởi tạo
        Array<Crop> cropArray = new Array<Crop>();
        Array<Fruit> fruitArray = new Array<Fruit>();
        Array<Prop> propArray = new Array<Prop>();
        Array<Land> landArray = new Array<Land>();

        //Khởi tạo cây trồng
        Crop crop0 = new Crop(0, "Hạt giống Dâu", "../assets/image/crops/crop0/seed.png", 5, 1, 10, 1, 0);
        Crop crop1 = new Crop(1, "Hạt giống Ngô", "../assets/image/crops/crop1/seed.png", 5, 2, 15, 1, 0);
        Crop crop2 = new Crop(2, "Hạt giống Củ cải trắng", "../assets/image/crops/crop2/seed.png", 4, 3, 17, 1, 0);
        Crop crop3 = new Crop(3, "Hạt giống Cà rốt", "../assets/image/crops/crop3/seed.png", 4, 4, 19, 1, 0);
        Crop crop4 = new Crop(4, "Hạt giống Khoai tây", "../assets/image/crops/crop4/seed.png", 5, 6, 20, 2, 0);
        Crop crop5 = new Crop(5, "Hạt giống Cà tím", "../assets/image/crops/crop5/seed.png", 5, 7, 22, 2, 0);
        Crop crop6 = new Crop(6, "Hạt giống Cà chua", "../assets/image/crops/crop6/seed.png", 5, 8, 24, 3, 0);
        Crop crop7 = new Crop(7, "Hạt giống Đậu Hà Lan", "../assets/image/crops/crop7/seed.png", 5, 10, 26, 3, 0);
        Crop crop8 = new Crop(8, "Hạt giống Ớt", "../assets/image/crops/crop8/seed.png", 5, 12, 28, 3, 0);
        Crop crop9 = new Crop(9, "Hạt giống Bí ngô", "../assets/image/crops/crop9/seed.png", 5, 14, 30, 4, 0);
        Crop crop10 = new Crop(10, "Hạt giống Anh đào", "../assets/image/crops/crop10/seed.png", 6, 15, 33, 4, 0);
        Crop crop11 = new Crop(11, "Hạt giống Dưa hấu", "../assets/image/crops/crop11/seed.png", 5, 18, 35, 5, 0);
        Crop crop12 = new Crop(12, "Hạt giống Chuối", "../assets/image/crops/crop12/seed.png", 6, 21, 37, 5, 0);
        Crop crop13 = new Crop(13, "Hạt giống Đào", "../assets/image/crops/crop13/seed.png", 5, 26, 38, 6, 0);
        cropArray.add(crop0);
        cropArray.add(crop1);
        cropArray.add(crop2);
        cropArray.add(crop3);
        cropArray.add(crop4);
        cropArray.add(crop5);
        cropArray.add(crop6);
        cropArray.add(crop7);
        cropArray.add(crop8);
        cropArray.add(crop9);
        cropArray.add(crop10);
        cropArray.add(crop11);
        cropArray.add(crop12);
        cropArray.add(crop13);

        //Danh sách quả
        Fruit fruit0 = new Fruit(0, "Dâu", "../assets/image/crops/crop0/seed.png", 20, 0);
        Fruit fruit1 = new Fruit(1, "Ngô", "../assets/image/crops/crop1/seed.png", 30, 0);
        Fruit fruit2 = new Fruit(2, "Củ cải trắng", "../assets/image/crops/crop2/seed.png", 36, 0);
        Fruit fruit3 = new Fruit(3, "Cà rốt", "../assets/image/crops/crop3/seed.png", 38, 0);
        Fruit fruit4 = new Fruit(4, "Khoai tây", "../assets/image/crops/crop4/seed.png", 40, 0);
        Fruit fruit5 = new Fruit(5, "Cà tím", "../assets/image/crops/crop5/seed.png", 44, 0);
        Fruit fruit6 = new Fruit(6, "Cà chua", "../assets/image/crops/crop6/seed.png", 48, 0);
        Fruit fruit7 = new Fruit(7, "Đậu Hà Lan", "../assets/image/crops/crop7/seed.png", 52, 0);
        Fruit fruit8 = new Fruit(8, "Ớt", "../assets/image/crops/crop8/seed.png", 56, 0);
        Fruit fruit9 = new Fruit(9, "Bí ngô", "../assets/image/crops/crop9/seed.png", 60, 0);
        Fruit fruit10 = new Fruit(10, "Anh đào", "../assets/image/crops/crop10/seed.png", 66, 0);
        Fruit fruit11 = new Fruit(11, "Dưa hấu", "../assets/image/crops/crop11/seed.png", 70, 0);
        Fruit fruit12 = new Fruit(12, "Chuối", "../assets/image/crops/crop12/seed.png", 74, 0);
        Fruit fruit13 = new Fruit(13, "Đào", "../assets/image/crops/crop13/seed.png", 76, 0);
        fruitArray.add(fruit0);
        fruitArray.add(fruit1);
        fruitArray.add(fruit2);
        fruitArray.add(fruit3);
        fruitArray.add(fruit4);
        fruitArray.add(fruit5);
        fruitArray.add(fruit6);
        fruitArray.add(fruit7);
        fruitArray.add(fruit8);
        fruitArray.add(fruit9);
        fruitArray.add(fruit10);
        fruitArray.add(fruit11);
        fruitArray.add(fruit12);
        fruitArray.add(fruit13);

        //Danh sách đạo cụ
        Prop prop0 = new Prop(0, "Bánh mì", "../assets/image/props/prop0.png", 10, 1, "phục hồi thể lực", 5, 0);
        Prop prop1 = new Prop(1, "Nước trái cây", "../assets/image/props/prop1.png", 20, 2, "phục hồi thể lực", 20, 0);
        Prop prop2 = new Prop(2, "Đùi gà", "../assets/image/props/prop2.png", 50, 5, "phục hồi thể lực", 50, 0);
        Prop prop3 = new Prop(3, "Phân bón", "../assets/image/props/prop3.png", 100, 5, "tăng sản lượng", 15, 0);
        propArray.add(prop0);
        propArray.add(prop1);
        propArray.add(prop2);
        propArray.add(prop3);

        //danh sách land
        Land land0 = new Land(0);
        Land land1 = new Land(1);
        Land land2 = new Land(2);
        Land land3 = new Land(3);
        Land land4 = new Land(4);
        Land land5 = new Land(5);
        Land land6 = new Land(6);
        Land land7 = new Land(7);
        Land land8 = new Land(8);
        Land land9 = new Land(9);
        Land land10 = new Land(10);
        Land land11 = new Land(11);


        landArray.add(land0);
        landArray.add(land1);
        landArray.add(land2);
        landArray.add(land3);
        landArray.add(land4);
        landArray.add(land5);
        landArray.add(land6);
        landArray.add(land7);
        landArray.add(land8);
        landArray.add(land9);
        landArray.add(land10);
        landArray.add(land11);


        if(SQLConnector.getConn()!=null)
        {
            //Lưu trữ dữ liệu vào chuỗi json và ghi vào cơ sở dữ liệu
            String timerData = json.toJson(timer);
            String cropData = json.toJson(cropArray);
            String fruitData = json.toJson(fruitArray);
            String propData = json.toJson(propArray);
            String landData = json.toJson(landArray);
            SQLConnector.writeData("update user set TimerData='" + timerData + "',CropData='" + cropData + "',FruitData='" + fruitData +
                    "',PropData='" + propData + "',LandData='" + landData + "' where userID='" + userID + "'");
        }
        else {
            saveLand(landArray, userID);
            saveProp(propArray, userID);
            saveFruit(fruitArray, userID);
            saveTimer(timer,userID);
            saveCrop(cropArray, userID);
        }

    }

    //Đọc thông tin người dùng cục bộ
    public static void saveUserMsg(User user) {
        Json json=new Json();
        FileHandle userFileFolder = new FileHandle("user/"+"/");
        String s=new String();
        if(!userFileFolder.exists())
            userFileFolder.mkdirs();
        FileHandle userFile=new FileHandle("user/"+"/user.json");
        if (!userFile.exists()) {
            try {
                userFile.file().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        s= Base64Coder.encodeString(json.toJson(user));
        userFile.writeString(s, false);
    }

    public static User readUserMsg() {
        User user = null;
        Json json=new Json();
        String s=new String();
        FileHandle userFile=new FileHandle("user/user.json");
        s=Base64Coder.decodeString(userFile.readString());
        user=json.fromJson(User.class, s);
        return user;
    }


    //Đọc thông tin người dùng cục bộ
    public static void saveUserMsgToLocal(User user) {
        Json json=new Json();
        String userID = user.getUserID();
        FileHandle userFileFolder = new FileHandle("user/"+userID+"/");
        if(!userFileFolder.exists())
            userFileFolder.mkdirs();

        FileHandle userFile=new FileHandle("user/"+userID+"/user.json");
        if (!userFile.exists()) {
            try {
                userFile.file().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        json.toJson(user, userFile);
    }


    public static User readUserMsgFromLocal(String userID) {
        User user = null;
        Json json=new Json();
        FileHandle userFile=new FileHandle("user/"+userID+"/user.json");
        user=json.fromJson(User.class, userFile);
        return user;
    }

    public static Timer readTimer(String userID) {
        Timer timer;
        Json json=new Json();
        FileHandle timerFile;
        if (!Gdx.files.internal("user/"+userID+"/timer.json").exists())
        {
            timer=new Timer();
            saveTimer(timer, userID);
            return timer;
        }
        else {
            timerFile=new FileHandle("user/"+userID+"/timer.json");
            timer=json.fromJson(Timer.class, timerFile);
            return timer;
        }

    }

    public static void saveTimer(Timer timer,String userID){
        Json json=new Json();

        FileHandle timerFile=new FileHandle("user/"+userID+"/timer.json");
        if (!timerFile.exists()) {
            try {
                timerFile.file().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        json.toJson(timer,timerFile);

    }




    public static Array<Crop> readCrop(String userID) {
        Array<Crop> crop;
        Json json=new Json();
        FileHandle cropFile;
        if (!Gdx.files.internal("user/"+userID+"/crop.json").exists())
        {
            crop=new Array<Crop>();
            saveCrop(crop, userID);
            return crop;
        }
        else {
            cropFile=new FileHandle("user/"+userID+"/crop.json");
            crop=json.fromJson(Array.class,Crop.class, cropFile);
            return crop;
        }

    }

    public static void saveCrop(Array<Crop> crop, String userID){
        Json json=new Json();

        FileHandle cropFile=new FileHandle("user/"+userID+"/crop.json");
        if (!cropFile.exists()) {
            try {
                cropFile.file().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        json.toJson(crop,cropFile);

    }




    public static Array<Fruit> readFruit(String userID) {
        Array<Fruit> fruit;
        Json json=new Json();
        FileHandle fruitFile;
        if (!Gdx.files.internal("user/"+userID+"/fruit.json").exists())
        {
            fruit=new Array<Fruit>();
            saveFruit(fruit, userID);
            return fruit;
        }
        else {
            fruitFile=new FileHandle("user/"+userID+"/fruit.json");
            fruit=json.fromJson(Array.class,Fruit.class, fruitFile);
            return fruit;
        }

    }

    public static void saveFruit(Array<Fruit> fruit,String userID){
        Json json=new Json();

        FileHandle fruitFile=new FileHandle("user/"+userID+"/fruit.json");
        if (!fruitFile.exists()) {
            try {
                fruitFile.file().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        json.toJson(fruit,fruitFile);

    }


    public static Array<Land> readLand(String userID) {
        Array<Land> land;
        Json json=new Json();
        FileHandle landFile;
        if (!Gdx.files.internal("user/"+userID+"/land.json").exists())
        {
            land=new  Array<Land>();
            saveLand(land, userID);
            return land;
        }
        else {
            landFile=new FileHandle("user/"+userID+"/land.json");
            land=json.fromJson(Array.class,Land.class, landFile);
            return land;
        }

    }

    public static void saveLand(Array<Land> land,String userID){
        Json json=new Json();

        FileHandle landFile=new FileHandle("user/"+userID+"/land.json");
        if (!landFile.exists()) {
            try {
                landFile.file().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        json.toJson(land,landFile);

    }

    public static Array<Prop> readProp(String userID) {
        Array<Prop> prop;
        Json json=new Json();
        FileHandle propFile;
        if (!Gdx.files.internal("user/"+userID+"/prop.json").exists())
        {
            prop=new Array<Prop>();
            saveProp(prop, userID);
            return prop;
        }
        else {
            propFile=new FileHandle("user/"+userID+"/prop.json");
            prop=json.fromJson(Array.class,Prop.class, propFile);
            return prop;
        }

    }

    public static void saveProp(Array<Prop> prop,String userID){
        Json json=new Json();

        FileHandle propFile=new FileHandle("user/"+userID+"/prop.json");
        if (!propFile.exists()) {
            try {
                propFile.file().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        json.toJson(prop,propFile);

    }



}
