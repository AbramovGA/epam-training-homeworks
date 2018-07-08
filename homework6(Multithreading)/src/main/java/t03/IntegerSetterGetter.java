package t03;

import java.util.Random;

class IntegerSetterGetter extends Thread {
    private SharedResource resource;
    private boolean run;
    private Random rand = new Random();

    public IntegerSetterGetter(String name, SharedResource resource) {
        super(name);
        this.resource = resource;
        run = true;
    }

    public void stopThread() {
        run = false;
    }


    public void run() {
        try {
            boolean action;
            while (run) {
                synchronized (resource) {
                    action = resource.isEmpty();
                }
                if (action) {
                    setIntegersIntoResource();
                } else {
                    getIntegersFromResource();
                }
            }
            System.out.println("Поток " + getName() + " завершил работу.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getIntegersFromResource() throws InterruptedException {
        Integer number;
        synchronized (resource) {
            System.out.println("Поток " + getName()
                    + " хочет извлечь число.");
            number = resource.getELement();
            while (number == null) {
                System.out.println("Поток " + getName()

                        + " ждет пока очередь заполнится.");

                resource.wait();
                System.out

                        .println("Поток " + getName() + " возобновил работу.");

                number = resource.getELement();
            }
            System.out

                    .println("Поток " + getName() + " извлек число " +
                            number);
        }
    }

    private void setIntegersIntoResource() {
        Integer number = rand.nextInt(500);
        synchronized (resource) {
            resource.setElement(number);
            System.out.println("Поток " + getName() + " записал число "
                    + number);
            resource.notify();
        }
    }
}
