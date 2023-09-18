package lk.sachinsilva.thogakade.service.impl;


import lk.sachinsilva.thogakade.dto.ItemDTO;
import lk.sachinsilva.thogakade.entity.Item;
import lk.sachinsilva.thogakade.repo.ItemRepo;
import lk.sachinsilva.thogakade.service.ItemService;
import lk.sachinsilva.thogakade.service.exception.DuplicateException;
import lk.sachinsilva.thogakade.service.exception.NotFoundException;
import lk.sachinsilva.thogakade.util.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepo itemRepo;
    private final Mapper mapper = new Mapper();

    public ItemServiceImpl(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }


    @Override
    public List<String> loadItemCodes() {
        return itemRepo.findAll().stream().map(Item::getCode).collect(Collectors.toList());
    }

    @Override
    public ItemDTO get(String code) throws NotFoundException {
        Optional<Item> byId = itemRepo.findById(code);
        if (byId.isEmpty()) {
            throw new DuplicateException(code + " already exists");
        }
        return mapper.toItemDTO(byId.get());
    }


    @Override
    public void save(ItemDTO itemDTO) throws DuplicateException {
        if (itemRepo.existsById(itemDTO.getCode())) {
            throw new DuplicateException(itemDTO.getCode() + " already exists");
        }
        itemRepo.save(mapper.toItem(itemDTO));
    }

    @Override
    public List<ItemDTO> searchItems(String data) {
        //TODO: implement search
        return null;
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepo.findAll().stream().map(mapper::toItemDTO).collect(Collectors.toList());
    }

    @Override
    public void update(ItemDTO itemDTO) throws NotFoundException {
        Optional<Item> byId = itemRepo.findById(itemDTO.getCode());
        if (byId.isEmpty()) {
            throw new NotFoundException(itemDTO.getCode() + " item not found");
        }
        Item item = byId.get();
        item.setDescription(itemDTO.getDescription());
        item.setUnitPrice(itemDTO.getUnitPrice());
        item.setQtyOnHand(itemDTO.getQtyOnHand());
    }

    @Override
    public void delete(String code) throws NotFoundException {
        if (!itemRepo.existsById(code)) {
            throw new DuplicateException(code + " item not found");
        }
        itemRepo.deleteById(code);
    }
}
