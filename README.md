# 2023年2月移动应用开发期末练习

## 完成情况

- Activity之间的传值和Activity的生命周期:smile:
- Dialog:smile:
- 跨程序通信和RecyclerView:smile:
- MediaPlayer和Broadcast:smile:
- 两个Fragment之间的传值:smile:
- Notification:smile:
- okHttp3网络请求:smile:
- SharePreference和跨程序通信:smile:
- kotlin多线程:smile:
- AsyncTask和downloadManager:sob:(使用downloadManager时不知什么原因手机一直提示“下载失败”，现在的"exercise_ten"代码中只使用了AsyncTask进行模拟进度条下载，并未真正下载，在“My application”代码中是下载文件的代码，只要将两者结合在一起即可打成题目要求)
- Service、RecyclerView和sqlite:smile:

## 题目

1. 请实现以下两个界面，并完成从(a)界面传值跳转到(b)界面的过程。要求是：至少在一个activity里面的主要生命周期函数中，用Log输出一句测试的话，并对Log画面进行截图；对两个Activity传值效果进行截图。请注意：密码框使用*隐藏显示

   ![image](https://user-images.githubusercontent.com/100272100/216392633-dc2e8c1c-ecde-4fd1-8636-299c2df2c30b.png)

2. 请编写以下界面，实现启动一个Dialog，然后将返回值更新在原始界面上。另外，需要在第一个页面的主要生命周期函数中，用Log输出一句测试的话。

   ![image](https://user-images.githubusercontent.com/100272100/216392657-12f7389d-d8b6-47b5-a2c7-1b1fec4b4c9d.png)

3. 访问手机通讯录，并用RecycleView显示出来，列表中显示姓名,电话。

4. 在A Activity上点击按钮“播放”，发送一条系统全局广播，接收方收到广播后，开始播放一首歌曲。点击“停止”按钮，发送一条系统全局广播，接收方将停止播放。

5. 请使用Fragment，界面分成左右结构，左侧是菜单，分别有信息学部，教育学部，经管学部三个菜单项，点击每个菜单项，在内容区可以显示三个学院的介绍。

6. 请设计一个界面，只有一个按钮“产生通知”。每次点击按钮，产生一条通知，显示hello+数字。数字累加。3

7. 访问https://restapi.amap.com/v3/weather/weatherInfo?city=110000&key=65069e6e1cebabaf68efe9391db90b56&extensions=all
，获取今天的天气情况，并显示。(注意编码是UTF-8)

8. (综合题）两个界面。界面A有两个按钮。点击“加载”按钮，将通讯录内容读出，放在SharedPreference中，并显示成功。点击显示按钮，跳转到界面B，将所有的通讯录按照名字顺序显示出来(ListView)。

9. （综合题）界面A包含一个按钮和一个文本框。点击按钮启动新线程进行耗时操作(产生一个随机字符串后休眠10秒)。此时文本框显示“数据等待中”。新线程完成休眠后，将字符串保存到文件中，并在主界面文本框显示“计a算已完成”。同时发送一条通知，点击通知跳到界面B，然后显示该字符串。

10. （综合题）使用AsyncTask来下载以下文件，并定时在界面上显示下载进度，界面设计不限。文件地址：http://115.29.231.93:8080/compare/mongodb.tgz。

11. （综合题）每隔10秒，产生一条字符串：hello, 系统时间，并将字符串插入到数据库sqlite中。在Activity界面上设置3个按钮，可以启动和service、停止Service和查看数据库记录(RecycleView)。
