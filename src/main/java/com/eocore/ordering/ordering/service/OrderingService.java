package com.eocore.ordering.ordering.service;

import com.eocore.ordering.ordering.domain.Ordering;
import com.eocore.ordering.ordering.dto.OrderingListResDto;
import com.eocore.ordering.ordering.repository.OrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderingService
{
    private final OrderingRepository orderingRepository;
    @Autowired
    public OrderingService(OrderingRepository orderingRepository)
    {
        this.orderingRepository = orderingRepository;
    }

    public Ordering findById(Long id)
    {
        return orderingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("없는 주문내역"));
    }
    public List<OrderingListResDto> findAll()
    {
        List<Ordering> orderings = orderingRepository.findAll();
        List<OrderingListResDto> orderingListResDtos = new ArrayList<>();

        for (Ordering ordering: orderings)
        {
            OrderingListResDto tempOrd = OrderingListResDto.builder()
                    .id(ordering.getId())
                    .orderStatus(ordering.getOrderStatus())
                    .createdTime(ordering.getCreatedTime())
                    .build();
            orderingListResDtos.add(tempOrd);
        }
        return orderingListResDtos;
    }
}
