ASCII (American Standard Code for Information Interchange,美国信息交换标准代码)。
    7 bit表示一个字符，共可以表示 2的7次方 128种字符。

ISO-8859-1，在原有的 ASCII的基础上进行来一个扩展。
    8 bit表示一个字符，即用完整的一个字节（byte）来表示一个字符，共计可以表示 2的8次方 256个字符。

gb2312, gb（国标）对所有的汉字进行编码，但是没有考虑生僻字。
    用两个字节（byte）来表示一个汉字,

gbk, gbk所表示的汉字要超过 gb2312，gbk是gb2312的一个超集，完全兼容gb2312的，包含了生僻字。

gb18030 ,是最完整的汉字，（中华人民共和国简体汉字）的表示形式，gb18030 所表示的汉字的数量是最多的。

big5 , 大5码，台湾繁体字的一种编码。

unicode ，能表示全世界所有国家的字符，最广的，最全的字符表示形式，采用了两个字节（byte）来表示一个字符。
    问题：存储变大了。特别是针对英文的存储，会造成巨大的空间浪费。

UTF, (Unicode Translation Format ,unicode 转换格式)
    unicode是一种编码方式，而UTF则是一种存储方式：UTF-8/UTF-16/UTF-32是unicode的实现方式

UTF-16: UTF-16LE (little endian 小端),UTF-16BE(big endian 大端)，也是使用两个字节表示一个字符

UTF-8: 变长字节表示形式，根据不同的字符，采用的存储的字节个数是不一样的。
    对于中文，UTF-8会用3个字节来表示一个中文。UTF-8最多会用6个字节来表示一个字符。

BOM(Byte Order Mark 字节序标记)：







