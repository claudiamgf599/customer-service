package com.trade.customerportfolio.controller;

import com.trade.customerportfolio.dto.CustomerInformation;
import com.trade.customerportfolio.dto.StockTradeRequest;
import com.trade.customerportfolio.dto.StockTradeResponse;
import com.trade.customerportfolio.service.CustomerService;
import com.trade.customerportfolio.service.TradeService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    private final TradeService tradeService;

    public CustomerController(CustomerService customerService, TradeService tradeService) {
        this.customerService = customerService;
        this.tradeService = tradeService;
    }

    @GetMapping("/{customerId}")
    public Mono<CustomerInformation> getCostumerInformation(@PathVariable Integer customerId) {
        return this.customerService.getCustomerInformation(customerId);
    }

    @PostMapping("/{customerId}/trade")
    public Mono<StockTradeResponse> trade(@PathVariable Integer customerId, @RequestBody Mono<StockTradeRequest> mono) {
        return mono.flatMap(request -> this.tradeService.trade(customerId, request));
    }
}
