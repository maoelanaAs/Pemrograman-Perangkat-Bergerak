package com.learning.serasamenu;

public class MenuModel {

    String menuName;
    int menuImg, menuPrice;

    public MenuModel(String menuName, int menuImg, int menuPrice) {
        this.menuName = menuName;
        this.menuImg = menuImg;
        this.menuPrice = menuPrice;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuImg() {
        return menuImg;
    }

    public int getMenuPrice() {
        return menuPrice;
    }
}
