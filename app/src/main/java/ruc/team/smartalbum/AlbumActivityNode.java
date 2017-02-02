package ruc.team.smartalbum;

/**
 * Created by 边园 on 2016/5/9.
 */
public class AlbumActivityNode {
    private int fatherNode;
    private int id;
    private String name;
    private boolean hasChild;
    private SmartAlbumAdapter.OrganizeMode organizeMode;

    public int getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(int fatherNode) {
        this.fatherNode = fatherNode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public SmartAlbumAdapter.OrganizeMode getOrganizeMode() {
        return organizeMode;
    }

    public void setOrganizeMode(SmartAlbumAdapter.OrganizeMode organizeMode) {
        this.organizeMode = organizeMode;
    }

    public void copy(AlbumActivityNode node) {
        this.setOrganizeMode(node.getOrganizeMode());
        this.setName(node.getName());
        this.setFatherNode(node.getFatherNode());
        this.setHasChild(node.isHasChild());
        this.setId(node.getId());
    }
}
