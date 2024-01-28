package com.eocore.ordering.item.controller;

import com.eocore.ordering.item.service.ItemService;
import org.springframework.stereotype.Controller;

@Controller
public class ItemController
{
    private final ItemService itemService;

    public ItemController(ItemService itemService)
    {
        this.itemService = itemService;
    }
}
