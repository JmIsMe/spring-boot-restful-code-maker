package view;

import org.apache.commons.lang3.StringUtils;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import core.MakeCore;
import engine.Config;

/**
 * @author :        LinJiangMing
 * @description :
 * @date :        Created in 15:50 2018/9/5
 */
public class CodeMaker {
    private JPanel maker;
    private JTextField dbAddressInput;
    private JTextField dbAccount;
    private JTextField outPutAddress;
    private JTextField dbPassword;
    private JButton makeCodeButton;
    private JButton connectButton;
    private JButton selectButton;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private JTextField projectName;
    private JTextField author;
    private JScrollPane tableNamesScroll;
    private JScrollPane logScroll;
    private JTextArea logOutput;
    private JList tableNamesList;
    private JButton clearLogButton;
    private MakeCore makeCore = MakeCore.getMakeCore();
    private Config config = Config.getConfig();
    public static CodeMaker codeMaker;
    public static void main(String[] args) {
        JFrame frame = new JFrame("CodeMaker");
        frame.setContentPane(CodeMaker.getCodeMaker().maker);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
        );
        frame.pack();
        frame.setVisible(true);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static CodeMaker getCodeMaker(){
        if (codeMaker == null){
            codeMaker = new CodeMaker();
        }
        return codeMaker;
    }
    private CodeMaker(){
        logOutput.setEditable(false);//设置不可编辑
        outPutAddress.setEditable(false);
        JTextAreaOutputStream jTextAreaOutputStream = new JTextAreaOutputStream (logOutput);
        System.setOut (new PrintStream (jTextAreaOutputStream));//设置输出重定向
        System.setErr(new PrintStream(jTextAreaOutputStream));//将错误输出也重定向,用于e.pritnStackTrace
        connectButton.addActionListener(e -> {
            String dbAddress = "jdbc:mysql://"+dbAddressInput.getText()+"?useSSL=false&serverTimezone=UTC";
            String account = dbAccount.getText();
            String password = dbPassword.getText();
            Connection conn = null;
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(dbAddress , account , password);
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            tableNamesList.setListData(getAllTableNames(conn).toArray());

            System.out.println("输出表名成功");
        });

        makeCodeButton.addActionListener(e -> {
            String proName = projectName.getText();
            String authorName = author.getText();
            String outputPath = outPutAddress.getText();
            String address = dbAddressInput.getText();
            String account = dbAccount.getText();
            String password = dbPassword.getText();
            List tableList = tableNamesList.getSelectedValuesList();
            Boolean mark = true;
            if(proName == null || proName.isEmpty()){
                System.out.println("错误：没有填写项目GroupIp");
                mark = false;
            }
            if (authorName == null || authorName.isEmpty()){
                System.out.println("错误：没有填写作者名称");
                mark = false;
            }
            if (outputPath == null || outputPath.isEmpty()){
                System.out.println("错误：没有选择输出的地址");
                mark = false;
            }
            if (address == null || address.isEmpty()){
                System.out.println("错误：没有选择数据库地址");
                mark = false;
            }
            if (account == null || account.isEmpty()){
                System.out.println("错误：没有填写连接数据库账号");
                mark = false;
            }
            if (password == null || password.isEmpty()){
                System.out.println("错误：没有填写连接数据库密码");
                mark = false;
            }
            if (tableList == null || tableList.isEmpty()){
                System.out.println("错误：没有选择需要生成的表");
                mark = false;
            }
            if(mark) {
                String dbAddress = "jdbc:mysql://"+address+"?useSSL=false&serverTimezone=UTC";
                config.setJdbcUserName(account);
                config.setJdbcUrl(dbAddress);
                config.setJdbcPassword(password);
                config.setProjectName(proName);
                config.setLocalPath(outputPath);
                config.setAuthor(authorName);
                config.setNameBeans(tableList);
                makeCore.makeCode();
            }


        });

        selectButton.addActionListener(e ->{
            SelectDirectory.makeSelectFrame();
        });

        clearLogButton.addActionListener(e -> {
            logOutput.setText("");
        });

    }

    public static List getAllTableNames(Connection conn) {
        List tableNames = new ArrayList();
        if (conn != null) {
            try {
                DatabaseMetaData dbmd = conn.getMetaData();
                // 表名列表
                ResultSet rest = dbmd.getTables(null, null, null, new String[] { "TABLE" });
                // 输出 table_name
                while (rest.next()) {
                    tableNames.add(rest.getString("TABLE_NAME"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tableNames;
    }

    public void setProjectName(String path){
        outPutAddress.setText(path);
    }
}

class JTextAreaOutputStream extends OutputStream
{
    private final JTextArea destination;


    public JTextAreaOutputStream (JTextArea destination)
    {
        if (destination == null)
            throw new IllegalArgumentException ("Destination is null");


        this.destination = destination;
    }
    @Override
    public void write(byte[] buffer, int offset, int length) throws IOException
    {
        final String text = new String (buffer, offset, length);
        SwingUtilities.invokeLater(() -> destination.append (text));
    }
    @Override
    public void write(int b) throws IOException
    {
        write (new byte [] {(byte)b}, 0, 1);
    }

}

