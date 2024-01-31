package com.eocore.ordering.orderItem.repository;

import com.eocore.ordering.orderItem.domain.OrderItem;
import com.eocore.ordering.orderItem.dto.OrderItemsResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>
{
    List<OrderItem> findByOrdering_Id(Long id);
}
