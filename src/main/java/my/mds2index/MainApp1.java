package my.mds2index;

import my.mds2index.config.Mds2indexConfig;
import my.mds2index.utils.FilesUtils;
import my.mds2index.utils.Md2HtmlUtils;

public class MainApp1 {
    /**
     * 转化主程序
     *
     * @param args args0[渲染配置文件路径，默认 ../config/config.yml]
     *             args1[MarkDown文件夹路径，默认 ../markdowns]
     *             args2[index.html模板文件路径，默认 ../template/index.html]
     *             args3[输出文件路径，默认 ../output/index.html]
     */
    public static void main(String[] args) {
    	
    	
        System.out.println("==================开始执行markdown文档转化为带索引的单页面index.html!!=================");

        System.out.println(">>>>>>>>>>传入参数检查...");
        if (null != args && args.length > 0) {
            System.out.println("检测到传入自定义渲染配置文件路径[" + args[0] + "]！读取覆盖默认配置！");
            Mds2indexConfig.loadConfig(args[0]);
        }
        Boolean useArgsPath = Mds2indexConfig.get("page.useArgsPathForJar");
        if (null != useArgsPath && useArgsPath) {
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
       
        //项目根目录
        String rootPath="E:\\markdown文本测试\\众创平台";
        
        FilesUtils.createHtmlByRootPath(rootPath);
        
        
        //文件目录
    }
}
