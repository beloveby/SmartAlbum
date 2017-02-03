package ruc.team.smartalbum.MenuPage;

import java.util.List;

/**
 * Created by 边园 on 2016/5/9.
 */
public class Show {
    private List<String> paths;

    public int getCount(){
        return paths.size();
    }

    public String getPath(int position){
        return paths.get(position);
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }
}
