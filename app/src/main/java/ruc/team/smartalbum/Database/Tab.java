package ruc.team.smartalbum.Database;

/**
 * Created by 边园 on 2016/5/9.
 */
public class Tab {
    private int tabId;
    private String tabName;
    private String LogisticArg;

    public String getLogisticArg() {
        return LogisticArg;
    }

    public void setLogisticArg(String logisticArg) {
        LogisticArg = logisticArg;
    }

    public int getTabId() {
        return tabId;
    }

    public void setTabId(int tabId) {
        this.tabId = tabId;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }
}
