package top.gloryjie.learn.apache.common.cli;

import org.apache.commons.cli.*;

/**
 * Apache Common CLI 提供的命令参数解析工具
 * 使用主要分3步
 * 1. 定义option
 * 2. 根据定义的option解析args参数，生成CommandLine
 * 3. 根据CommandLine来判断args参数中使用了哪些option，从而实现不同的逻辑
 */
public class CliDemo {

    public static void main(String[] args) {
        // 命令行参数兴义
        Options options = new Options();

        Option hOption = new Option("h", "help",false, "Print Help");
        hOption.setRequired(false);

        Option configOption = new Option("c", "configFile", true, "Name server config properties file");
        configOption.setRequired(false);

        Option printConfigOption = new Option("p", "printConfigItem", false, "Print all config item");
        printConfigOption.setRequired(false);

        options.addOption(hOption);
        options.addOption(configOption);
        options.addOption(printConfigOption);

        // 命令行参数解析
        CommandLine commandLine = null;
        try {
            CommandLineParser commandLineParser = new DefaultParser();
            commandLine = commandLineParser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        // 打印帮助信息
        if (commandLine.hasOption("h")) {
            // 使用辅助类打印
            HelpFormatter helpFormatter = new HelpFormatter();
            helpFormatter.setWidth(110);
            helpFormatter.printHelp("testApp", options, true);
        }

        // 指定了配置文件
        if (commandLine.hasOption("c")){
            String optionValue = commandLine.getOptionValue("c");
            System.out.println("appoint config file: " + optionValue);
        }
    }


}
