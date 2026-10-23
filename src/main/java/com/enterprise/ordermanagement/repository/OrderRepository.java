package com.enterprise.ordermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.ordermanagement.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
