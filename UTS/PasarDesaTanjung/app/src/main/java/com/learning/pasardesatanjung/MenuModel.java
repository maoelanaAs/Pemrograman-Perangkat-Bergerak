package com.learning.pasardesatanjung;

public class MenuModel {

    String menuName, menuDesc;
    int menuImg, menuPrice;

    public MenuModel(String menuName, String menuDesc, int menuImg, int menuPrice) {
        this.menuName = menuName;
        this.menuDesc = menuDesc;
        this.menuImg = menuImg;
        this.menuPrice = menuPrice;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public int getMenuImg() {
        return menuImg;
    }

    public int getMenuPrice() {
        return menuPrice;
    }
}
