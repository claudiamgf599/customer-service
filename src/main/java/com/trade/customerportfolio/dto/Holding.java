package com.trade.customerportfolio.dto;

import com.trade.customerportfolio.domain.Ticker;

public record Holding(Ticker ticker,
                      Integer quantity) {
}
