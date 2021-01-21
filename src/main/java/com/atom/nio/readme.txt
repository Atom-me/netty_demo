java.io
java.nio


java.io 中最为核心的一个概念是流（stream），面向流的编程，Java.io中，一个流要么是输入流，要么是输出流，不可能同时既是输入流又是输出流。


java.nio 中有3个核心概念：Selector，Channel，Buffer，在java.nio中，我们是面向块（block），或是缓冲区（buffer）编程的。
Buffer本身就是一块内存，底层实现上，实际是一个数组，数据的读、写都是通过Buffer来实现的。
除了数组之外，Buffer还提供来对于数据的结构化访问方式，并且可以追踪到系统的读写过程。

Java中的8中原生数据类型，都有各自对应的Buffer类型，如IntBuffer.LongBuffer,ShortBuffer...,但是并没有BooleanBuffer

Channel指的是可以向其写入数据或者是从中读取数据的对象，它类似于java.io中的stream。

所有的数据的读写都是通过Buffer来进行的。永远不会出现直接向Channel写入数据的情况，也永远不会出现直接从channel读取数据的情况。
与java.io 中流（Stream）不同的是，Channel 是双向的，一个流只可能是 Inputstream 或者 OutputStream,Channel打开后则可以进行读取、写入或者读写。
由于Channel是双向的，因此它能更好的反映出底层操作系统的真实情况；在Linux系统中，底层操作系统的通道就是双向的。

关于NIO Buffer 中的三个重要状态属性的含义：position、limit、capacity。

capacity： 就是Buffer容器所包含的元素的数量，Buffer分配大小之后，capacity永远不会为负数、也永远不会变化。
limit:
position:

大小关系：
0 <= mark  <= position <= limit <= capacity

