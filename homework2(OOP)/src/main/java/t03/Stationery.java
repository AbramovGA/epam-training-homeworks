package t03;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public abstract class Stationery {
    Integer price;
    String name;
}
