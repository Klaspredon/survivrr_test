package com.survivrrdev.survivrr;

public class UserInfo {
    public static final String TABLE_NAME = "UserInfo";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_NAME_ICE = "contact_name";
    public static final String COLUMN_ADDRESS_ICE = "contact_address";
    public static final String COLUMN_PHONE_ICE = "contact_phone";

    private int id;
    private String username;
    private String password;
    private String name;
    private String address;
    private String phone;
    private String gender;
    private String birthday;
    private String contact_name;
    private String contact_address;
    private String contact_phone;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_USERNAME + " TEXT,"
                    + COLUMN_PASSWORD + " TEXT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_PHONE + " TEXT,"
                    + COLUMN_ADDRESS + " TEXT,"
                    + COLUMN_BIRTHDAY + " TEXT,"
                    + COLUMN_GENDER + " TEXT,"
                    + COLUMN_NAME_ICE + " TEXT,"
                    + COLUMN_PHONE_ICE + " TEXT,"
                    + COLUMN_ADDRESS_ICE + " TEXT"
                    + ")";

    public UserInfo(){

    }

    public UserInfo(int id, String username, String password, String name, String phone,
                    String address, String birthday, String gender, String contact_name,
                    String contact_phone, String contact_address){
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
        this.contact_name = contact_name;
        this.contact_phone = contact_phone;
        this.contact_address = contact_address;
    }

    public int getUserId(){
        return id;
    }

    public String getUserUsername(){
        return name;
    }

    public String getUserPassword(){
        return password;
    }

    public String getUserName(){
        return name;
    }

    public String getUserPhone(){
        return phone;
    }

    public String getUserAddress(){
        return address;
    }

    public String getUserBirthday(){
        return birthday;
    }

    public String getUserGender(){
        return gender;
    }

    public String getUserContactName(){
        return contact_name;
    }

    public String getUserContactPhone(){
        return contact_phone;
    }

    public String getUserContactAddress(){
        return contact_address;
    }

    public void setUserId(int id){
        this.id = id;
    }

    public void setUserUsername(String username){
        this.username = username;
    }

    public void setUserPassword(String password){
        this.password = password;
    }

    public void setUserName(String name){
        this.name = name;
    }

    public void setUserPhone(String phone){
        this.phone = phone;
    }

    public void setUserAddress(String address){
        this.address = address;
    }

    public void setUserBirthday(String birthday){
        this.birthday = birthday;
    }

    public void setUserGender(String gender){
        this.gender = gender;
    }

    public void setUserContactName(String contact_name){
        this.contact_name = contact_name;
    }

    public void setUserContactPhone(String contact_phone){
        this.contact_phone = contact_phone;
    }

    public void setUserContactAddress(String contact_address){
        this.contact_address = contact_address;
    }
}
