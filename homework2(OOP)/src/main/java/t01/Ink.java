package t01;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ink {
    int inkAmount;
    Color color;

    enum Color {BLUE, RED, GREEN, BLACK}
}
