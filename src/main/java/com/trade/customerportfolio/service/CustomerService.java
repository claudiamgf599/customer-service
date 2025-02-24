package com.trade.customerportfolio.service;

import com.trade.customerportfolio.dto.CustomerInformation;
import com.trade.customerportfolio.entity.Customer;
import com.trade.customerportfolio.exceptions.ApplicationExceptions;
import com.trade.customerportfolio.mapper.EntityDtoMapper;
import com.trade.customerportfolio.repository.CustomerRepository;
import com.trade.customerportfolio.repository.PortfolioItemRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final PortfolioItemRepository portfolioItemRepository;

    public CustomerService(CustomerRepository customerRepository, PortfolioItemRepository portfolioItemRepository) {
        this.customerRepository = customerRepository;
        this.portfolioItemRepository = portfolioItemRepository;
    }

    public Mono<CustomerInformation> getCustomerInformation(Integer customerId) {
        return this.customerRepository.findById(customerId)
                .switchIfEmpty(ApplicationExceptions.customerNotFound(customerId))
                .flatMap(this::buildCustomerInformation);
    }

    private Mono<CustomerInformation> buildCustomerInformation(Customer customer) {
        return this.portfolioItemRepository.findAllByCustomerId(customer.getId())
                .collectList() // retorna un Mono con la lista
                .map(list -> EntityDtoMapper.toCustomerInformation(customer, list));
    }

}
