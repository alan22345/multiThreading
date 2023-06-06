package vaultExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VaultExample {

    public void run() {
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(1000));

        List<Thread> threads = new ArrayList<>();

        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceThread());

        for(Thread thread : threads){
            thread.start();
        }

    }

}
