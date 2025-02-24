package com.trade.customerportfolio.mapper;

import com.trade.customerportfolio.domain.Ticker;
import com.trade.customerportfolio.dto.CustomerInformation;
import com.trade.customerportfolio.dto.Holding;
import com.trade.customerportfolio.dto.StockTradeRequest;
import com.trade.customerportfolio.dto.StockTradeResponse;
import com.trade.customerportfolio.entity.Customer;
import com.trade.customerportfolio.entity.PortfolioItem;

import java.util.List;

public class EntityDtoMapper {

    public static CustomerInformation toCustomerInformation(Customer customer, List<PortfolioItem> items) {
        List<Holding> holdings = items.stream()
                .map(item -> new Holding(item.getTicker(), item.getQuantity()))
                .toList();

        return new CustomerInformation(customer.getId(),
                customer.getName(),
                customer.getBalance(),
                holdings
        );
    }

    public static PortfolioItem toPortfolioItem(Integer customerId, Ticker ticker) {
        return PortfolioItem.builder()
                .customerId(customerId)
                .ticker(ticker)
                .quantity(0)
                .build();
    }

    public static StockTradeResponse toStockTradeResponse(StockTradeRequest request, Integer customerId, Integer balance) {
        return new StockTradeResponse(customerId, request.ticker(), request.price(), request.quantity(), request.action(), request.totalPrice(), balance);
    }
}
