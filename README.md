# FirstJavaFx
This is a sample about JavaFX 8.For the detail tutorials,please linkto www.makery.ch

#错误处理
如果你现在运行应用程序，你应该能够从表中删除选择的人员。但是，当你没有在表中选择人员时点击删除按钮时会发生什么呢。
这里有一个ArrayIndexOutOfBoundsException，因为它不能删除掉索引为-1人员项目。索引-1由getSelectedIndex()返回，它意味着你没有选择项目。
当然，忽略这种错误不是非常好。我们应该让用户知道在删除时必须选择一个人员。（更好的是我们应该禁用删除按钮，以便用户没有机会做错误的事情）。
我们添加一个弹出对话框通知用户，你将需要添加一个库`Dialogs`：

1. 下载`controlsfx-8.0.6_20.jar` （你也能从ControlsFX Website中获取）。 重要：ControlsFX必须是8.0.6_20以上版本才能在JDK8U20以上版本工作。
2. 在项目中创建一个lib子目录，添加controlsf jar文件到该目录下。
3. 添加库到你的项目classpath中。在Eclipse中右击jar文件|选择Build Path| Add to Build Path。现在Eclipse知道这个库了。

#保存用户设置
Java允许我们使用Preferences类保存一些应用状态。依赖于操作系统，Perferences保存在不同的地方（例如：Windows中的注册文件）。
我们不能使用Preferences来保存全部地址簿。但是它允许我们保存一些简单的应用状态。一件这样事情是最后打开文件的路径。使用这个信息，我们能加载最后应用的状态，不管用户什么时候重启应用程序。
下面两个方法用于保存和检索Preference。添加它们到你的MainApp类的最后：
MainApp中的`getPersonFilePath()`, `setPersonFilePath()`。

#持久性数据到XML
##为何使用XML
持久性数据的一种最常用的方法是使用数据库。数据库通常包含一些类型的关系数据（例如：表），当我们需要保存的数据是对象时。这称object-relational impedance mismatch。匹配对象到关系型数据库表有很多工作要做。这里有一些框架帮助我们匹配（例如：Hibernate，最流行的一个）。但是它仍然需要相当多的设置工作。

对于简单的数据模型，非常容易使用XML。我们使用称为JAXB（Java Architecture for XML Binding）的库。只需要几行代码，JAXB将允许我们生成XML输出，