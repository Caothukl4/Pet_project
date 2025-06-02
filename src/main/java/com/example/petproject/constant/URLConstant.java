package com.example.petproject.constant;

public class URLConstant {
        public static final String API_ADMIN_LOGIN = "/api/admin/login";
        public static final String API_LOGIN = "/api/login";
        public static final String API_REGISTER = "/api/register";
        public static final String API_GET_USER_ID = "/api/user";
        public static final String API_GET_USER_EMAIL = "/api/user/email";
        public static final String API_GET_USER= "/api/users";
        public static final String API_UPDATE_USER = "/api/update/user/{id}";
        public static final String API_DELETE_USER = "/api/delete/user/{id}";

        public static final String API_PRODUCT = "/api/product";
        public static final String API_GET_PRODUCT = "/api/product/id";
        public static final String API_GET_PRODUCT_STATUS = "/api/product/status";
        public static final String API_GET_ALL_PRODUCT = "/api/products";
        public static final String API_GET_PRODUCT_CATEGORY = "/api/product/category";
        public static final String API_GET_PRODUCT_NAME = "/api/product/name";
        public static final String API_UPDATE_PRODUCT = "/admin/products/update/{id}";
        public static final String API_DELETE_PRODUCT = "/admin/products/delete/{id}";

        public static final String API_CART_USER = "/api/cart";
        public static final String API_CART_ADD = "/api/cart/{userId}/add";
        public static final String API_CART_UPDATE = "/api/cart/{userId}/update/{cartItemId}";
        public static final String API_CART_DELETE = "/api/cart/{userId}/remove/{cartItemId}";
        public static final String API_CART_CLEAR = "/api/cart/{userId}/clear";

        public static final String API_CREATE_ORDER = "/api/orders/create/{userId}";
        public static final String API_GET_ORDER_ID = "/api/orders";
        public static final String API_GET_ORDER_USERID = "/api/orders/users";
        public static final String API_UPDATE_ORDER_STATUS = "/api/orders/{orderId}/status";
        public static final String API_DELETE_ORDER = "/api/orders/delete";



    }
