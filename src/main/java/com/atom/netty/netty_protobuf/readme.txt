对于proto buf 生成的中间代码和 proto描述文件(IDL 文件 interface description language)的管理 方式：
1。使用git submodule
2. 使用 git subtree

注： 我们使用protocol Buffer 只是 定义消息，，message真正传输的载体我们是使用netty来做传输的。