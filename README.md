[![Build Status](https://travis-ci.org/petterobam/my-mds2index.html.svg?branch=master)](https://travis-ci.org/petterobam/my-mds2index.html)
[![MIT Licence](https://badges.frapsoft.com/os/mit/mit.svg?v=103)](https://opensource.org/licenses/mit-license.php)
[![stable](http://badges.github.io/stability-badges/dist/stable.svg)](http://github.com/badges/stability-badges)

# my-mds2index.html

多个Markdown文档转化为带索引的单HTML页面

# 使用说明
0. 去relase下载打包好的文件[my-mds2index.htmlv1.0.0.zip](https://github.com/petterobam/my-mds2index.html/releases/download/v1.0.0/my-mds2index.htmlv1.0.0.zip)
1. 将你的markdown文件放入 ```markdowns/``` 文件夹下面
2. 编辑 ```config/config.yml```将你的markdown文件的文件名按你想要展示的顺序添加到 ```markdowns/``` 属性下面，添加格式参照你第一次打开看到的格式写法
3. 点击 ```my-mds2index.html.exe.bat``` 或 ```bin/my-mds2index.html.bat```(windows) ；```sh bin/my-mds2index.html.sh``` (linux) 
4. 你要的单页面索引文件就在 ```output/``` 文件夹下面

# 扩展使用
1. ```template/index.html``` 可以修改，自定义生成页面的样式
2. ```config/config.yml``` 可以修改，搭配模板
3. 用命令运行脚本第一个参数可以带 脚本绝对路径 ，实现不同模板对应不同配置解析
4. 热爱学习的可以自己去发现更多... 

## TODO-LIST

1. ~~调试 shell脚本~~
2. ~~学习完善 bat脚本~~
