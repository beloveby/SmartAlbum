package ruc.team.smartalbum.Database;

/**
 * Created by 汪雅婧 on 2017/3/10.
 */

public class Category {
    private int categoryId;
    private String  categoryName;

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
