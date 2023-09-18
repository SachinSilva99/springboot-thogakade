package lk.sachinsilva.thogakade.controller;


import lk.sachinsilva.thogakade.dto.ItemDTO;
import lk.sachinsilva.thogakade.regex.Validates;
import lk.sachinsilva.thogakade.regex.Validation;
import lk.sachinsilva.thogakade.service.ItemService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Author : Sachin Silva
*/
@RestController
@RequestMapping("/thogakade/api/v1/items")
public class ItemController {
    private final ItemService itemService;
    private final Validation validation = new Validation();

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    protected void doPost(@RequestBody ItemDTO itemDTO) {
        if (!validation.match(itemDTO.getCode(), Validates.ITEM_CODE)) {
            return;
        }
        if (!validation.match(itemDTO.getDescription(), Validates.ITEM_DESCRIPTION)) {
            return;
        }
        if (!validation.match(String.valueOf(itemDTO.getQtyOnHand()), Validates.QTY)) {
            return;
        }
        if (!validation.match(String.valueOf(itemDTO.getUnitPrice()), Validates.PRICE)) {
            return;
        }
        itemService.save(itemDTO);
    }

    @GetMapping("/{code}")
    public ItemDTO get(@PathVariable String code) {
        return itemService.get(code);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code) {
        itemService.delete(code);
    }

    @PatchMapping("/{code}")
    public void update(@PathVariable String code, @RequestBody ItemDTO itemDTO) {
        itemService.update(itemDTO);
    }

    @GetMapping("/searches/{data}")
    public List<ItemDTO> search(@PathVariable String data) {
        return itemService.searchItems(data);
    }
}
