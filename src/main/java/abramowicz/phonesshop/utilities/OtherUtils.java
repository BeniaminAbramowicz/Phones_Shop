package abramowicz.phonesshop.utilities;

import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.OrderList;

import java.util.List;

public class OtherUtils {

    public static boolean containsItem(List<OrderList> orderListItems, int id){
        for(OrderList orderList : orderListItems){
            if(orderList.getProduct().getProductId() == id){
                return true;
            }
        }
        return false;
    }

    public static boolean userHasOrder(List<Order> orders, int id){
        for(Order order : orders){
            if(order.getOrderId() == id){
                return true;
            }
        }
        return false;
    }
}
