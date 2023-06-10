package NonBlockingLockFreeOps.AtomicReference;

import java.util.concurrent.atomic.AtomicReference;

public class Example {
    public static void main(String[] args) {
        String oldName = "old";
        String newName = "new";

        AtomicReference<String> atomicReference = new AtomicReference<>(oldName);
        //atomicReference.set("not new");
        if(atomicReference.compareAndSet(oldName,newName)){
            System.out.println("New val is : " +atomicReference.get());
        }else {
            System.out.println("Nothing changed");
        }
    }
}
