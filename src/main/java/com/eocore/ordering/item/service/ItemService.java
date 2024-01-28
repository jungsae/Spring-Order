package com.eocore.ordering.item.service;

import com.eocore.ordering.item.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService
{
    private final ItemRepository itemRepository;
    @Autowired
    public ItemService(ItemRepository itemRepository)
    {
        this.itemRepository = itemRepository;
    }
}
