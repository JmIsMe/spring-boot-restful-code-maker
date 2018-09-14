package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

/**
 * @author :        LinJiangMing
 * @description :
 * @date :        Created in 15:16 2018/9/14
 */
public class SelectDirectory {
    private static  JFrame frame;
    public  JPanel selectDir;
    private JTree tree;
    private JButton cancelButton;
    private JButton selectButton;
    protected FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    public static TreePath mouseInPath;

    public static JFrame makeSelectFrame(){
        frame = new JFrame("SelectDirectory");
        frame.setContentPane(new SelectDirectory().selectDir);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }


    public SelectDirectory() {
        tree.setModel(new FileTreeModel(new DefaultMutableTreeNode(new FileNode("root",null,null,true))));
        tree.setCellRenderer(new FileTreeRenderer());

        tree.addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                DefaultMutableTreeNode lastTreeNode =(DefaultMutableTreeNode) event.getPath().getLastPathComponent();
                FileNode fileNode = (FileNode) lastTreeNode.getUserObject();
                if (!fileNode.isInit) {
                    File[] files;
                    if (fileNode.isDummyRoot) {
                        files = fileSystemView.getRoots();
                    } else {
                        files = fileSystemView.getFiles(
                                ((FileNode) lastTreeNode.getUserObject()).file,
                                false);
                    }
                    for (int i = 0; i < files.length; i++) {
                        if (files[i].isDirectory()) {
                            FileNode childFileNode = new FileNode(
                                    fileSystemView.getSystemDisplayName(files[i]),
                                    fileSystemView.getSystemIcon(files[i]), files[i],
                                    false);
                            DefaultMutableTreeNode childTreeNode = new DefaultMutableTreeNode(childFileNode);
                            lastTreeNode.add(childTreeNode);
                        }
                    }
                    //通知模型节点发生变化
                    DefaultTreeModel treeModel1 = (DefaultTreeModel) tree.getModel();
                    treeModel1.nodeStructureChanged(lastTreeNode);
                }
                //更改标识，避免重复加载
                fileNode.isInit = true;
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {

            }

        });

        selectButton.addActionListener(e -> {

            FileNode fileNode = (FileNode)((DefaultMutableTreeNode )tree.getSelectionPath().getLastPathComponent()).getUserObject();
            String filePath = fileNode.file.getAbsolutePath();
            CodeMaker.getCodeMaker().setProjectName(filePath);
            frame.dispose();

        });
        cancelButton.addActionListener(e ->{
            try {
                frame.dispose();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }

}
class FileNode{
    public FileNode(String name,Icon icon,File file,boolean isDummyRoot){
        this.name=name;this.icon=icon;this.file=file;this.isDummyRoot=isDummyRoot;
    }
    public boolean isInit;
    public boolean isDummyRoot;
    public String name;
    public Icon icon;
    public File file;
}
class FileTreeModel extends DefaultTreeModel {
    public FileTreeModel(TreeNode root) {
        super(root);
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        File[] files=fileSystemView.getRoots();
        for (int i = 0; i < files.length; i++) {
            FileNode childFileNode = new FileNode(fileSystemView.getSystemDisplayName(files[i]), fileSystemView.getSystemIcon(files[i]), files[i], false);
            DefaultMutableTreeNode childTreeNode = new DefaultMutableTreeNode(childFileNode);
            ((DefaultMutableTreeNode)root).add(childTreeNode);
        }
    }
    @Override
    public boolean isLeaf(Object node) {
        DefaultMutableTreeNode treeNode=(DefaultMutableTreeNode)node;
        FileNode fileNode=(FileNode)treeNode.getUserObject();
        if(fileNode.isDummyRoot)return false;
        return fileNode.file.isFile();
    }
}
class FileTreeRenderer extends DefaultTreeCellRenderer {
    public FileTreeRenderer(){
    }
    @Override
    public Component getTreeCellRendererComponent(javax.swing.JTree tree,
                                                  java.lang.Object value,
                                                  boolean sel,
                                                  boolean expanded,
                                                  boolean leaf,
                                                  int row,
                                                  boolean hasFocus){

        JLabel label= (JLabel) super.getTreeCellRendererComponent(tree,value,sel,expanded,leaf,row,hasFocus);

        DefaultMutableTreeNode node=(DefaultMutableTreeNode)value;
        FileNode fileNode=(FileNode)node.getUserObject();
        label.setText(fileNode.name);
        label.setIcon(fileNode.icon);

        label.setOpaque(false);
        if(SelectDirectory.mouseInPath!=null&&
                SelectDirectory.mouseInPath.getLastPathComponent().equals(value)){
            label.setOpaque(true);
            label.setBackground(new Color(255,0,0,90));
        }
        return label;
    }
}