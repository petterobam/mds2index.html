#!/bin/bash
# author:petterobam
# url:https://github.com/petterobam/my-mds2index.html
# Usage: sh my-mds2indexhtml.sh [渲染配置文件路径，默认 ../config/config.yml] [MarkDown文件夹路径，默认 ../markdowns] [index.html模板文件路径，默认 ../template/index.html] [输出文件路径，默认 ../output/index.html]
curr_path="`cd .. | pwd`"
jar_path="$curr_path/my-mds2index.html-1.0.0.jar";
# 检查渲染配置文件默认路径
conf_path="$curr_path/config/config.yml"
if [ -n "$1" ]
    conf_path="$1"
fi
# MarkDown文件夹默认路径
mds_path="$curr_path/markdowns"
if [ -n "$2" ]
    mds_path="$2"
fi
# index.html模板文件默认路径
tpl_path="$curr_path/template/index.html"
if [ -n "$3" ]
    tpl_path="$3"
fi
# 输出文件默认路径
out_path="$curr_path/output/index.html"
if [ -n "$3" ]
    out_path="$3"
fi
echo "------------------------------------------------------------"
echo ">>>>>>>>>>>默认文件所在文件夹：$curr_path"
echo ">>>>>>>>>>>启动配置读取..."
echo ">>>>>>>>>>>渲染配置文件路径：$conf_path"
echo ">>>>>>>>>>>MarkDown文件夹路径：$mds_path"
echo ">>>>>>>>>>>index.html模板文件路径：$tpl_path"
echo ">>>>>>>>>>>输出文件路径：$out_path"
echo ">>>>>>>>>>>启动配置读取完毕！！"
echo "------------------------------------------------------------"
echo ">>>>>>>>>>>用于将MarkDown转化单索引页面的jar包路径：$out_path"
echo "------------------------------------------------------------"
echo ">>>>>>>>>>>开始转化..."
echo "------------------------------------------------------------"
java -jar $jar_path $conf_path $mds_path $tpl_path $out_path  > ./my-mds2index.html.out < /dev/null &
echo "------------------------------------------------------------"
echo ">>>>>>>>>>>转化结束！！"
echo "------------------------------------------------------------"