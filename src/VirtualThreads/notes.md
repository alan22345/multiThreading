Virtual threads

a virtual thread fully belongs to the JVM doesnt come with a fixed sized stack.
OS doesnt take a role of managing it.
It is just like an Object on the JVM heap.
Virtual threads are cheaper and much faster to create.

If virtual threads are just object how do they run on the CPU. A small pool of 
platform threads is created and when a virtual thread is created it is mounted
on the platform thread. When finished it becomes garbage and is disposed of.
If a thread doesnt finish its data is saved on the heap so that it can be easily 
recreated.

https://openjdk.org/jeps/425