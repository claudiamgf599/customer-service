package com.trade.customerportfolio.dto;

import com.trade.customerportfolio.domain.Ticker;
import com.trade.customerportfolio.domain.TradeAction;

public record StockTradeRequest(Ticker ticker,
                                Integer price,
                                Integer quantity,
                                TradeAction action) {

    public Integer totalPrice() {
        return price * quantity;
    }
}
