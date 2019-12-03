package top.gloryjie.learn.poi;

import lombok.Data;

import java.io.InputStream;

/**
 * @author jie
 * @since 2019/11/30
 */
@Data
public class MessageContent {

    private String targetAddr;

    private String title;

    private String content;

    private String attachFileName;

    private InputStream attachFile;

}
