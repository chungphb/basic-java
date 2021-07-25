package com.concurrency;

class Lover {
    private final String name;

    public Lover(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public synchronized void kiss(Lover lover) {
        System.out.format("%s: %s has kissed me!%n", this.name, lover.getName());
        lover.kissBack(this);
    }

    public synchronized void kissBack(Lover lover) {
        System.out.format("%s: %s has kissed me back!%n", this.name, lover.getName());
    }
}

class Spoon {
    private Diner owner;

    public Spoon(Diner owner) {
        this.owner = owner;
    }

    public Diner getOwner() {
        return owner;
    }

    public void setOwner(Diner owner) {
        this.owner = owner;
    }

    public void use() {
        System.out.format("%s has eaten!", owner.getName());
    }
}

class Diner {
    private String name;

    private boolean isStarving;

    public Diner(String name) {
        this.name = name;
        isStarving = true;
    }

    public String getName() {
        return name;
    }

    public boolean isStarving() {
        return isStarving;
    }

    public void eatWith(Spoon spoon, Diner partner) {
        while (isStarving) {
            if (spoon.getOwner() != this) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    continue;
                }
                continue;
            }
            if (partner.isStarving) {
                System.out.format("%s: You eat first, my darling %s!%n", name, partner.getName());
                spoon.setOwner(partner);
                continue;
            }
            spoon.use();
            isStarving = false;
            System.out.format("%s: I am stuffed, my darling %s!%n", name, partner.getName());
            spoon.setOwner(partner);
        }
    }
}

public class TestLiveness {
    public static void main(String[] args) throws InterruptedException {
        { // Deadlock
            final Lover elio = new Lover("Elio");
            final Lover oliver = new Lover("Oliver");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    elio.kiss(oliver);
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    oliver.kiss(elio);
                }
            }).start();
        }
        { // Starvation
            // - Describes a situation where a thread is unable to gain regular access to shared resources and is unable to make progress.
            // - Happens when shared resources are made unavailable for long periods by "greedy" threads.
        }
        { // Livelock
            final Diner elio = new Diner("Elio");
            final Diner oliver = new Diner("Oliver");
            final Spoon spoon = new Spoon(elio);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    elio.eatWith(spoon, oliver);
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    oliver.eatWith(spoon, elio);
                }
            }).start();
        }
    }

    public static void startTest(String test) {
        System.out.println(test);
    }

    public static void endTest() {
        System.out.println(new String(new char[40]).replace('\0', '='));
    }
}
