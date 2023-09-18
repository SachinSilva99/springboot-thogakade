package lk.sachinsilva.thogakade.service;



import lk.sachinsilva.thogakade.dto.ItemDTO;
import lk.sachinsilva.thogakade.service.exception.DuplicateException;
import lk.sachinsilva.thogakade.service.exception.NotFoundException;

import java.util.List;

public interface ItemService  {
    List<String> loadItemCodes();
    ItemDTO get(String code)throws NotFoundException;

    void save(ItemDTO itemDTO) throws DuplicateException;

    List<ItemDTO> searchItems(String data);

    List<ItemDTO> getAllItems();

    void update(ItemDTO itemDTO) throws NotFoundException;

    void delete(String code)throws NotFoundException;
}
