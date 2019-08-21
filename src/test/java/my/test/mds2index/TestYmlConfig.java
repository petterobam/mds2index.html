package my.test.mds2index;

import java.util.Map;

import org.junit.Test;

import my.mds2index.config.Mds2indexConfig;

public class TestYmlConfig {
    @Test
    public void test1() {
        System.out.println("-----------配置读取测试-------------");
        Map config = Mds2indexConfig.getYmlInfo();
        String title = Mds2indexConfig.get("page.title");
        System.out.println("page.title:" + title);
        String inc = Mds2indexConfig.get("page.inc");
        System.out.println("page.inc:" + inc);
        String mdPath = Mds2indexConfig.getMarkdownBasePath();
        System.out.println("page.mdPath:" + mdPath);
        String tplPath = Mds2indexConfig.getTplPath();
        System.out.println("page.tplPath:" + tplPath);
        String indexPath = Mds2indexConfig.getIndexPath();
        System.out.println("page.indexPath:" + indexPath);
    }

    @Test
    public void test2() {
        System.out.println("-----------修改配置属性测试-------------");
        String title = Mds2indexConfig.get("page.title");
        System.out.println("修改前>>>>>>>page.title:" + title);
        Mds2indexConfig.put("page.title", "Test修改属性");
        title = Mds2indexConfig.get("page.title");
        System.out.println("修改后>>>>>>>page.title:" + title);
    }

    @Test
    public void test3() {
        try {
            System.out.println("-----------覆盖默认配置测试-------------");
            String testConfigPath = Mds2indexConfig.class.getResource("/test-config.yml").getPath().toString();
            System.out.println("测试的配置文件地址：" + testConfigPath);
            Mds2indexConfig.loadConfig(testConfigPath);
            Map config = Mds2indexConfig.getYmlInfo();
            String title = Mds2indexConfig.get("page.title");
            System.out.println("page.title:" + title);
            String inc = Mds2indexConfig.get("page.inc");
            System.out.println("page.inc:" + inc);
            String mdPath = Mds2indexConfig.getMarkdownBasePath();
            System.out.println("page.mdPath:" + mdPath);
            String tplPath = Mds2indexConfig.getTplPath();
            System.out.println("page.tplPath:" + tplPath);
            String indexPath = Mds2indexConfig.getIndexPath();
            System.out.println("page.indexPath:" + indexPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
