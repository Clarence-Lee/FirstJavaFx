# FirstJavaFx
This is a sample about JavaFX 8.For the detail tutorials,please linkto www.makery.ch

#错误处理
如果你现在运行应用程序，你应该能够从表中删除选择的人员。但是，当你没有在表中选择人员时点击删除按钮时会发生什么呢。
这里有一个ArrayIndexOutOfBoundsException，因为它不能删除掉索引为-1人员项目。索引-1由getSelectedIndex()返回，它意味着你没有选择项目。
当然，忽略这种错误不是非常好。我们应该让用户知道在删除时必须选择一个人员。（更好的是我们应该禁用删除按钮，以便用户没有机会做错误的事情）。
我们添加一个弹出对话框通知用户，你将需要*添加一个库Dialogs：

1. 下载controlsfx-8.0.6_20.jar （你也能从ControlsFX Website中获取）。 重要：ControlsFX必须是8.0.6_20以上版本才能在JDK8U20以上版本工作。
2. 在项目中创建一个lib子目录，添加controlsf jar文件到该目录下。
3. 添加库到你的项目classpath中。在Eclipse中右击jar文件|选择Build Path| Add to Build Path。现在Eclipse知道这个库了。
