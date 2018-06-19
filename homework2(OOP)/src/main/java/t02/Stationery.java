package t02;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Stationery {
    int price;

    Item name;

    enum Item {PEN, PENCIL, TEXTBOOK, STEPLER, FOLDER, FILE, ERASER, PAPER}
}
