package com.example.datatier_sep3.core;

import com.example.datatier_sep3.contracts.OrderModel;
import com.example.datatier_sep3.contracts.ProductModel;
import com.example.datatier_sep3.contracts.UserModel;
import com.example.datatier_sep3.models.*;

public class ModelFactory {

    private UserModel userModel;
    private ProductModel productModel;
    private OrderModel orderModel;

    public ModelFactory() {

    }

    public UserModel getUserModel()
    {
        if(userModel == null)
        {
            userModel = new UserModelImpl();
        }
        return userModel;
    }
    public ProductModel getProductModel()
    {
        if(productModel == null)
        {
            productModel = new ProductModelImpl();
        }
        return productModel;
    }
    public OrderModel getOrderModel()
    {
        if(orderModel == null)
        {
            orderModel = new OrderModelImpl();
        }
        return orderModel;
    }


}
