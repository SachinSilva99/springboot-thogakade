package lk.sachinsilva.thogakade.entity;

import java.io.Serializable;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class OrderDetailId implements Serializable {

    @ManyToOne
    private Orders orders;

    @ManyToOne
    private Item item;

}
