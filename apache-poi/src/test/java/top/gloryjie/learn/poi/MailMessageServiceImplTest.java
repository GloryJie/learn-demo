package top.gloryjie.learn.poi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayInputStream;
import java.util.*;

/**
 * @author jie
 * @since 2019/11/30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MailMessageServiceImplTest {

    @Autowired
    MessageService messageService;

    @Test
    public void send() {

        Map<String, String> mapping = new LinkedHashMap<>();
        mapping.put("姓名", "name");
        mapping.put("性别", "sex");
        mapping.put("年龄", "age");

        List<Map<String, String>> dataList = new ArrayList<>();
        Map<String, String> record = new HashMap<>();
        record.put("name", "jojo");
        record.put("sex", "male");
        record.put("age", "22");
        dataList.add(record);

        byte[] excelData = POIUtil.generateExcel(mapping, dataList);

        MessageContent content = new MessageContent();
        content.setTargetAddr("");
        content.setTitle("测试标题");
        content.setContent("测试内容");
        content.setAttachFileName("测试文档.xlsx");
        content.setAttachFile(new ByteArrayInputStream(excelData));

        messageService.send(content);

    }
}