package controller.discount_controller;

import entity.discount.DiscountDTO;

public interface DiscountController {
    void fetchAll();
    void redeemDiscount(DiscountDTO dto);
    DiscountDTO handNext();
}
