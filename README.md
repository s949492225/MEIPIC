# MEIPIC (created by syiyi)
这是一个开源的Android图片浏览器.
该项目基于实时的html解析,主要内容为图片加文字,不基于服务器,是一个独立的本地项目.
该项目通过实时的分析html,动态的加载出列表,标题,和内容以及图片.
该项目适配了android5.0系统,目前为基础版本,存在一些bug,欢迎大家修改并提出一些建议.
对爬虫项目、Android5.0项目有兴趣的网友可以下载学习.
该项目所解析的网址为天极图片网,未获得天极图片网授权的个人或者公司,请勿用于商业用途.仅供于学习.郑重提醒,误用于商业用途,里面的网址可自行修改.

对该项目的展望,其实该项目爬取的规则是使用dom解析,由于图片网站的中html的规律性,可以很容易的分析出我们所需要的内容.

   所以我们可以让用户定义出常用的图片网站列表的解析规则,比如列表解析的规则是".mainClass div ul li a",这样我们很容易的就找到class是mainClass标签下的
div中的ul中的li中a标签,这样列表的所在的href超链接就获取到了,然后我们需要看此列表中的内容时可以再解析该列表的内容,与解析列表的
方法是一样的.

我只是写了一个最基本的东西,未来还需要靠大家扩展升级.
![image](https://github.com/s949492225/MEIPIC/blob/master/0.jpg)
![image](https://github.com/s949492225/MEIPIC/blob/master/1.jpg)
![image](https://github.com/s949492225/MEIPIC/blob/master/2.jpg)
