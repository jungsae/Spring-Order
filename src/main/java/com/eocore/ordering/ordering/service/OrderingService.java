package com.eocore.ordering.ordering.service;

import com.eocore.ordering.item.domain.Item;
import com.eocore.ordering.item.repository.ItemRepository;
import com.eocore.ordering.member.repository.MemberRepository;
import com.eocore.ordering.orderItem.domain.OrderItem;
import com.eocore.ordering.orderItem.dto.OrderItemsResDto;
import com.eocore.ordering.orderItem.repository.OrderItemRepository;
import com.eocore.ordering.ordering.domain.OrderStatus;
import com.eocore.ordering.ordering.domain.Ordering;
import com.eocore.ordering.ordering.dto.OrderingDto;
import com.eocore.ordering.ordering.dto.OrderingReqDto;
import com.eocore.ordering.ordering.repository.OrderingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderingService
{
    private final OrderingRepository orderingRepository;
    private final MemberRepository memberRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemRepository itemRepository;
    @Autowired
    public OrderingService(OrderingRepository orderingRepository, MemberRepository memberRepository, OrderItemRepository orderItemRepository, ItemRepository itemRepository)
    {
        this.orderingRepository = orderingRepository;
        this.memberRepository = memberRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemRepository = itemRepository;
    }

    public Ordering findById(Long id)
    {
        return orderingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("없는 주문내역"));
    }
    public List<OrderingDto> findAll()
    {
        List<Ordering> orderings = orderingRepository.findAll();
        List<OrderingDto> orderingListResDtos = new ArrayList<>();

        for (Ordering ordering: orderings)
        {
            OrderingDto tempOrd = OrderingDto.builder()
                    .id(ordering.getId())
                    .orderStatus(ordering.getOrderStatus())
                    .createdTime(ordering.getCreatedTime())
                    .build();
            orderingListResDtos.add(tempOrd);
        }
        return orderingListResDtos;
    }

    @Transactional
    public void saveOrder(OrderingReqDto orderingReqDto)
    {
        Ordering ordering = Ordering.builder()
                .orderStatus(OrderStatus.ORDERED)
                .member(memberRepository.getReferenceById(orderingReqDto.getMemberId()))
                .build();
        orderingRepository.save(ordering);
        for (OrderingReqDto.OrderItemDto orderItemDto: orderingReqDto.getOrderItems())
        {
            Item item = itemRepository.findById(orderItemDto.getItemId()).get();
            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .ordering(ordering)
                    .quantity(orderItemDto.getCount())
                    .build();
            if (item.getStockQuantity() - orderItemDto.getCount() < 0) throw new IllegalArgumentException("재고 수량 부족");
            orderItemRepository.save(orderItem);
            item.updateItem(item.getStockQuantity() - orderItemDto.getCount());
        }
    }
    @Transactional
    public void cancelOrder(Long id)
    {
        Ordering ordering = this.findById(id);
        ordering.updateStatus("CANCELED");
        for (OrderItem orderItem: orderItemRepository.findByOrdering_Id(ordering.getId()))
            orderItem.getItem().updateItem(orderItem.getItem().getStockQuantity() + orderItem.getQuantity());
    }
}
