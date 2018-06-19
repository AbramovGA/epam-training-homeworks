package t06;


import lombok.NoArgsConstructor;
import t07.Marine;

@Marine
public class Submarine {

    private ДвигательДляАтомнойЛодки engine;


    Submarine() {
        engine = new ДвигательДляАтомнойЛодки();
    }

    public static void main(String[] args) {
        Submarine submarine = new Submarine();
        submarine.sail();
    }

    public void sail() {
        engine.switchOn();
    }

    @NoArgsConstructor
    class ДвигательДляАтомнойЛодки {
        boolean isWorking = false;

        boolean switchOn() {
            if (isWorking)
                return false;
            isWorking = true;
            return true;
        }

    }
}
