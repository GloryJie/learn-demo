package top.gloryjie.learn.poi;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;

/**
 * @author jie
 * @since 2019/11/30
 */
@Slf4j
@Service
public class MailMessageServiceImpl implements MessageService {

    @Value("${spring.mail.username}")
    private String fromAddr;

    @Autowired
    private JavaMailSender javaMailSender;


    @PostConstruct
    public void init() {
        // 解决文件名过长导致接收到的附件名异常问题
        System.setProperty("mail.mime.splitlongparameters", "false");
    }


    @Override
    public boolean send(MessageContent content) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(fromAddr);
            helper.setSubject(content.getTitle());
            helper.setText(content.getContent());
            helper.setTo(content.getTargetAddr());

            if (StringUtils.isNotBlank(content.getAttachFileName()) && content.getAttachFile() != null) {
                helper.addAttachment(content.getAttachFileName(), new ByteArrayResource(IOUtils.toByteArray(content.getAttachFile())));
            }

            javaMailSender.send(mimeMessage);

            return true;
        } catch (Exception e) {
            log.error("", e);
        }
        return false;
    }
}
