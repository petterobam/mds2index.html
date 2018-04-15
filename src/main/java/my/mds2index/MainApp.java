package my.mds2index;

import my.mds2index.config.Mds2indexConfig;
import my.mds2index.utils.FilesUtils;
import my.mds2index.utils.Md2HtmlUtils;

public class MainApp {
    /**
     * 转化主程序
     * @param args
     * args0[渲染配置文件路径，默认 ../config/config.yml]
     * args1[MarkDown文件夹路径，默认 ../markdowns]
     * args2[index.html模板文件路径，默认 ../template/index.html]
     * args3[输出文件路径，默认 ../output/index.html]
     */
    public static void main(String[] args) {
        System.out.println("==================开始执行markdown文档转化为带索引的单页面index.html!!=================");

        System.out.println(">>>>>>>>>>传入参数检查...");
        if(null != args && args.length > 0){
            System.out.println("检测到传入自定义渲染配置文件路径[" + args[0] + "]！读取覆盖默认配置！");
            Mds2indexConfig.loadConfig(args[0]);
        }
        Boolean useArgsPath = Mds2indexConfig.get("page.useArgsPathForJar");
        if(null != useArgsPath && useArgsPath) {
            if (null != args && args.length > 1) {
                System.out.println("检测到传入自定义MarkDown文件夹路径[" + args[1] + "]！设置！");
                Mds2indexConfig.put("page.mdBasePath", args[1]);
            }
            if (null != args && args.length > 2) {
                System.out.println("检测到传入自定义index.html模板文件路径[" + args[2] + "]！设置！");
                Mds2indexConfig.put("page.tplPath", args[2]);
            }
            if (null != args && args.length > 3) {
                System.out.println("检测到传入自定义输出文件路径[" + args[3] + "]！设置！");
                Mds2indexConfig.put("page.indexPath", args[3]);
            }
        }

        System.out.println(">>>>>>>>>>传入参数检查完毕！！");
        String tplPath = Mds2indexConfig.getTplPath();
        System.out.println("模板路径：" + tplPath);
        System.out.println(">>>>>>>>>>>>>>>>>>>>读取模板文件...");
        String tplStr = FilesUtils.readAll(tplPath);
        System.out.println(">>>>>>>>>>>>>>>>>>>>读取模板文件完毕！！！");
        System.out.println(">>>>>>>>>>>>>>>>>>>>解析渲染为带索引的单页面index.html内容...");
        String mdIndexHtml = Md2HtmlUtils.parsePageByParam(tplStr);
        System.out.println(">>>>>>>>>>>>>>>>>>>>解析渲染为带索引的单页面index.html内容结束！！！");
        System.out.println("########################################################################");
        System.out.println(mdIndexHtml);
        System.out.println("########################################################################");
        String indexPath = Mds2indexConfig.getIndexPath();
        System.out.println(">>>>>>>>>>>>>>>>>>>>保存生成index.html文件...");
        System.out.println("生成文件路径：" + indexPath);
        FilesUtils.newFileUtf8(indexPath,mdIndexHtml);
        System.out.println(">>>>>>>>>>>>>>>>>>>>保存生成index.html文件结束！！！");
        System.out.println("==================结束执行markdown文档转化为带索引的单页面index.html!!==================");
    }
}
