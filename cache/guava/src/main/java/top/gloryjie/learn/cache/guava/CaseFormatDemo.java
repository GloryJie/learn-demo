package top.gloryjie.learn.cache.guava;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;

/**
 * 格式转换
 *
 * @author jie
 * @since 2020/2/24
 */
public class CaseFormatDemo {

    public static void main(String[] args) {
        // 将 telephoneTransformer -> Telephone transformer

        String name = "telephoneTransformer";
        // telephoneTransformer -> telephone_transformer -> telephone transformer
        name = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name).replace("_", " ");

        // 下面的功能一样,将首字母变成大写
        //name = name.substring(0,1).toUpperCase() + name.substring(1);
        name = StringUtils.capitalize(name);

        System.out.println(name);

    }
}
