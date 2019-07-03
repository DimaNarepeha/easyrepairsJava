package com.softserve.demo.configuration.security;

import com.softserve.demo.dto.OrderDTO;
import com.softserve.demo.dto.PostDTO;
import com.softserve.demo.model.*;
import com.softserve.demo.repository.OrderRepository;
import com.softserve.demo.repository.PortfolioRepository;
import com.softserve.demo.repository.PostRepository;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class AccessPermission {

    private final OrderRepository orderRepository;
    private final PortfolioRepository portfolioRepository;
    private final PostRepository postRepository;

    public AccessPermission(OrderRepository orderRepository, PortfolioRepository portfolioRepository,
                            PostRepository postRepository) {
        this.orderRepository = orderRepository;
        this.portfolioRepository = portfolioRepository;
        this.postRepository = postRepository;
    }

    public boolean canDeleteOrder(Integer id, String username) {
        Order order = orderRepository.findById(id).get();
        Customer customer = order.getCustomer();
        String customerUsername = customer.getUser().getUsername();
        boolean isSameName = username.equals(customerUsername);
        return isSameName && isCustomer(customer.getUser());
    }

    public boolean orderUpdatePermission(OrderDTO orderDTO, String username) {
        Order order = orderRepository.findById(orderDTO.getId()).get();
        Customer customer = order.getCustomer();
        Provider provider = order.getProvider();
        String customerUsername = customer.getUser().getUsername();
        String providerUsername = provider.getUser().getUsername();
        boolean isSameName = username.equals(customerUsername) || username.equals(providerUsername);
        return isSameName && (isCustomer(customer.getUser()) || isProvider(provider.getUser()));
    }

    public boolean postPermission(PostDTO postDTO, Principal principal) {
        return hasProviderPermission(postDTO.getPortfolioId(), principal);
    }

    public boolean deletePostPermission(Integer postId, Principal principal) {
        Post post = postRepository.findById(postId).get();
        Integer portfolioId = post.getPortfolio().getId();
        return hasProviderPermission(portfolioId, principal);
    }

    private boolean hasProviderPermission(Integer portfolioId, Principal principal) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId).get();
        Provider provider = portfolio.getProvider();
        String userName = provider.getUser().getUsername();
        boolean isSameName = principal.getName().equals(userName);
        return isSameName && isProvider(provider.getUser());
    }

    private boolean isCustomer(User user) {
        return user.getRoles().contains(Role.CUSTOMER);
    }

    private boolean isProvider(User user) {
        return user.getRoles().contains(Role.PROVIDER);
    }
}
