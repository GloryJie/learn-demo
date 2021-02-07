package top.gloryjie.learn.netty.splitpacket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Jie
 * @since 2021/1/30
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomProtocol {

    private int length;

    private byte[] content;

}
