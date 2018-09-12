package bean;

/**
 * @author :        LinJiangMing
 * @description :
 * @date :        Created in 11:16 2018/9/7
 */
public class NameUtil {

    private String tableName;
    private String domainName;
    private String lowerDomainName;

    public NameUtil(String tableName){
        this.tableName = tableName;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    public String getDomainName(){
        if (domainName == null){
            String[] strArray = tableName.split("_");
            StringBuilder sb = new StringBuilder();
            for (String str : strArray){
                String first = String.valueOf(str.charAt(0));
                sb.append(str.replaceFirst(first,first.toUpperCase()));
            }
            domainName = sb.toString();
        }
        return domainName;
    }

    public String getLowerDomainName(){
        if (lowerDomainName == null) {
            String domainName = getDomainName();
            String first = String.valueOf(domainName);
            lowerDomainName = domainName.replaceFirst(first,first.toUpperCase());
        }
        return lowerDomainName;
    }
}
