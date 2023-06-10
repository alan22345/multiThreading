Thread per task model issues -

*     Doesnt give optimal performance
*     When a thread is blocking on IO it cannot be used
*     Requires us to allocate more threads
*     Consuming more resources
*     Adding context switch overhead

Non blocking IO operation - doesnt block cpu operation by using callback
    
    method(){
        nonBlockingIo(resultData -> {
            ...
            UseData(resultData); // wait until result is ready execute code
                                 // after the result is ready
            ...
        });
    }
by using a callback function we don't block and we don't need to create more threads
the same thread can execute some instructions and execute the callbacks when ready

There is no context switching overhead because it's the same thread

This is the Thread per core Model

Reading the non blocking io operations can be a little harder because of the nesting of 
callbacks (callback hell)

Blocking IO is generally easier to write and test but its performance is worse 
But non blocking IOs performance is amazing but its a pain to write and debug properly 