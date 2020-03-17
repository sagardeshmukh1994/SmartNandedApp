package com.example.smtrick.smartnanded.constants;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Constant {
    /************************************** Firebase Storage reference constants ***************************************************************************/
    public static final FirebaseStorage STORAGE = FirebaseStorage.getInstance();
    public static final StorageReference STORAGE_REFERENCE = STORAGE.getReference();
    private static final FirebaseDatabase DATABASE = FirebaseDatabase.getInstance();
    public static final FirebaseAuth AUTH = FirebaseAuth.getInstance();
    public static final DatabaseReference USER_TABLE_REF = DATABASE.getReference("users");
    public static final DatabaseReference LEEDS_TABLE_REF = DATABASE.getReference("leeds");
    public static final DatabaseReference PRODUCTS_TABLE_REF = DATABASE.getReference("Products");
    public static final DatabaseReference ADVERTISE_TABLE_REF = DATABASE.getReference("Advertise");



    public static final String DOCUMENTS_PATH = "images/documents";
    public static final String CUSROMER_PROFILE_PATH = "images/customer";
    public static final String STORAGE_PATH_UPLOADS = "Products/";
    public static final String DATABASE_PATH_UPLOADS = "Products";
    public static final String STORAGE_PATH_ADVERTISE = "Advertise/";
    public static final String DATABASE_PATH_ADVERTISE = "Advertise";

    /************************************** Firebase Authentication reference constants ***************************************************************************/

    /************************************** Calender Constatns ***************************************************************************/
    public static final Calendar cal = Calendar.getInstance();
    public static final int DAY = cal.get(Calendar.DAY_OF_MONTH);
    public static final int MONTH = cal.get(Calendar.MONTH);
    public static final int YEAR = cal.get(Calendar.YEAR);
    public static final String SUPPORT_PHONE_NUMBER = "+917030648899";
    public static String TWO_DIGIT_LIMIT = "%02d";
    public static String FOUR_DIGIT_LIMIT = "%04d";
    public static final String SUCCESS = "Success";
    public static final String MALE = "Male";
    public static final String FEMALE = "Female";
    public static final String AGENT = "AGENT";
    public static final String AGENT_PREFIX = "AG-";
    public static final String LEED_PREFIX = "L-";
    public static final String EMAIL_POSTFIX = "@smartloan.com";
    public static final String STORAGE_PATH = "STORAGE_PATH";
    public static final String BITMAP_IMG = "BITMAP_IMG";
    public static final String IMAGE_URI_LIST = "IMAGE_URI_LIST";
    public static final String LEED_ID = "LEED_ID";
    public static final String IMAGE_COUNT = "IMAGE_COUNT";
    public static final String TOTAL_IMAGE_COUNT = "TOTAL_IMAGE_COUNT";
    public static final String LEED_MODEL = "LEED_MODEL";
    public static final String PRODUCT_MODEL = "LEED_MODEL";
    //********************************************STATUS FLEADS*****************************
    //***************************************************************************************
    public static final String GLOBAL_DATE_FORMATE = "dd MMM yyyy hh:mm a";
    public static final String CALANDER_DATE_FORMATE = "dd/MM/yy";
    public static final String LEED_DATE_FORMATE = "dd MMM, yyyy";
    public static final String DAY_DATE_FORMATE = "EEEE";
    public static final String TIME_DATE_FORMATE = "hh:mm a";
    public static final int REQUEST_CODE = 101;
    public static final int RESULT_CODE = 201;
    //****************************************************************
    public static final String ROLE_CUSTOMER = "CUSTOMER";
    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_DEACTIVE = "DEACTIVE";
    public static final String CUSTOMER_PREFIX = "CM-";
    //*********************************************************************************************
    //**************************CATEGORIES*********************************************************

    public static final String CATEGORY_MARKET = "Super Market";
    public static final String CATEGORY_CITY = "My City";
    public static final String CATEGORY_PROPERTIES = "Properties";
    public static final String CATEGORY_BIKE = "Bike";
    public static final String CATEGORY_CAR = "Car";
    public static final String CATEGORY_TRANSPORT = "Transport";
    public static final String CATEGORY_TRAVELS = "Travels";
    public static final String CATEGORY_JOBS = "Jobs";
    public static final String CATEGORY_MOBILES = "Mobiles";
    public static final String CATEGORY_AGRICULTURE = "Agriculture";
    public static final String CATEGORY_OFFERS = "Offers";
    public static final String CATEGORY_OTHERS = "Others";


    public static final List<Integer> QUANTITY_LIST = new ArrayList<Integer>();

    static {
        for (int i = 1; i < 11; i++) QUANTITY_LIST.add(i);
    }
}
