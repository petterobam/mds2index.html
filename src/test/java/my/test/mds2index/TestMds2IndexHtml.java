package my.test.mds2index;

import my.mds2index.config.Mds2indexConfig;
import my.mds2index.utils.FilesUtils;
import my.mds2index.utils.Md2HtmlUtils;
import org.junit.Test;

public class TestMds2IndexHtml {
    @Test
    public void test1(){
        String tplPath = Mds2indexConfig.getTplPath();
        System.out.println("模板路径：" + tplPath);
        String tplStr = FilesUtils.readAll(tplPath);
        String mdIndexHtml = Md2HtmlUtils.parsePageByParam(tplStr);
        System.out.println(mdIndexHtml);
        String indexPath = Mds2indexConfig.getIndexPath();
        System.out.println("生成文件路径：" + indexPath);
        FilesUtils.newFileUtf8(indexPath,mdIndexHtml);
    }
}
