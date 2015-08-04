package com.example.hairom.volleygson160615;

import java.util.List;

/**
 * Created by hairom on 17/06/2015.
 */
public class Contacts {

    String id, name, email, address, gender;
    Phone phoneList;

    public Phone getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(Phone phoneList) {
        this.phoneList = phoneList;
    }
}




