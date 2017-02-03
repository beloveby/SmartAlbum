package ruc.team.smartalbum;

import java.util.ArrayList;
import java.util.List;

import ruc.team.smartalbum.MenuPage.AlbumActivityNode;
import ruc.team.smartalbum.MenuPage.Show;

/**
 * Created by 边园 on 2016/5/9.
 */
public class TempData {
    public static int[] logos = new int[]{
            R.drawable.gallary,
            R.drawable.scene,
            R.drawable.people,
            R.drawable.object,
            R.drawable.learning
    };

    public static int[][] itemlogos = new int[][]{
            {},
            {R.drawable.mont, R.drawable.water},
            {R.drawable.alone, R.drawable.groupofalone},
            {R.drawable.object},
            {R.drawable.learning}
    };

    public static String[] armTypes = new String[]{
            "所有图片", "风景", "人物", "物品", "学习"
    };//id 0 - 4

    public static String[][] arms = new String[][]{
            {},
            {"山", "水"},
            {"alone", "a group of alone"},
            {"物品"},
            {"learning"}
    };

    public static String[][] scene_draw = new String[][]{
            {"drawable://" + R.drawable.mont, "drawable://" + R.drawable.mont, "drawable://" + R.drawable.mont, "drawable://" + R.drawable.mont},
            {"drawable://" + R.drawable.water, "drawable://" + R.drawable.water, "drawable://" + R.drawable.water, "drawable://" + R.drawable.water}
    };
    public static String[][] people_draw = new String[][]{
            {"drawable://" + R.drawable.alone, "drawable://" + R.drawable.alone, "drawable://" + R.drawable.alone, "drawable://" + R.drawable.alone},
            {"drawable://" + R.drawable.groupofalone, "drawable://" + R.drawable.groupofalone, "drawable://" + R.drawable.groupofalone, "drawable://" + R.drawable.groupofalone}
    };
    public static String[][] object_draw = new String[][]{
            {"drawable://" + R.drawable.object, "drawable://" + R.drawable.object, "drawable://" + R.drawable.object, "drawable://" + R.drawable.object}
    };
    public static String[][] learn_draw = new String[][]{
            {"drawable://" + R.drawable.learning, "drawable://" + R.drawable.learning, "drawable://" + R.drawable.learning, "drawable://" + R.drawable.learning}
    };

    public static int getCount(int id) {
        if (id == 0) {
            return 4;
        } else if (id < 5 && id > 0)
            return arms[id].length;
        else
            return 1;
    }

    public static int getChildId(int fatherId, int n) {
        if (fatherId == 0) {
            return n + 1;
        } else if (fatherId < 5 && fatherId > 0) {
            int sum = 5;
            for (int i = 0; i < fatherId; i++) {
                sum += arms[i].length;
            }
            return sum + n;
        } else {
            return -1;
        }
    }

    public static List<Show> manyShow(int id) {
        List<Show> shows = new ArrayList<>();
        List<String> path5 = new ArrayList<>();
        List<String> path6 = new ArrayList<>();
        Show show5 = new Show();
        Show show6 = new Show();

        switch (id) {
            case 0:
                List<String> path1 = new ArrayList<>();
                path1.add(scene_draw[0][0]);
                path1.add(scene_draw[0][0]);
                path1.add(scene_draw[0][1]);
                path1.add(scene_draw[0][0]);
                Show show1 = new Show();
                show1.setPaths(path1);

                List<String> path2 = new ArrayList<>();
                path2.add(people_draw[0][0]);
                path2.add(people_draw[0][1]);
                Show show2 = new Show();
                show2.setPaths(path2);

                List<String> path3 = new ArrayList<>();
                path3.add(object_draw[0][0]);
                path3.add(object_draw[0][1]);
                Show show3 = new Show();
                show3.setPaths(path3);

                List<String> path4 = new ArrayList<>();
                path4.add(learn_draw[0][0]);
                path4.add(learn_draw[0][1]);
                Show show4 = new Show();
                show4.setPaths(path4);

                shows.add(show1);
                shows.add(show2);
                shows.add(show3);
                shows.add(show4);
                break;
            case 1:
                path5.add(scene_draw[0][0]);
                path5.add(scene_draw[0][1]);
                show5.setPaths(path5);

                path6.add(scene_draw[1][0]);
                path6.add(scene_draw[1][1]);
                show6.setPaths(path6);

                shows.add(show5);
                shows.add(show6);
                break;
            case 2:
                path5.add(people_draw[0][0]);
                path5.add(people_draw[0][1]);
                show5.setPaths(path5);

                path6.add(people_draw[1][0]);
                path6.add(people_draw[1][1]);
                show6.setPaths(path6);

                shows.add(show5);
                shows.add(show6);
                break;
            case 3:
                path5.add(object_draw[0][0]);
                path5.add(object_draw[0][1]);
                show5.setPaths(path5);

                shows.add(show5);
                break;
            case 4:
                path5.add(learn_draw[0][0]);
                path5.add(learn_draw[0][1]);
                show5.setPaths(path5);

                shows.add(show5);
                break;
        }

        return shows;
    }

    public static List<AlbumActivityNode> manyLabel(int id) {
        List<AlbumActivityNode> labels = new ArrayList<>();

        if (id == 0) {
            for (int i = 1; i < 5; i++) {
                AlbumActivityNode label = new AlbumActivityNode();
                label.setName(armTypes[i]);
                label.setHasChild(true);
                label.setFatherNode(0);
                label.setId(i);
                labels.add(label);
            }
        } else {
            int mid = 5;
            for (int i = 0; i < id; i++) {
                mid += arms[i].length;
            }
            for (int i = 0; i < arms[id].length; i++) {
                AlbumActivityNode label = new AlbumActivityNode();
                label.setName(arms[id][i]);
                label.setHasChild(false);
                label.setFatherNode(id);
                label.setId(mid);
                mid++;
                labels.add(label);
            }
        }
        return labels;
    }

    public static List<Show> oneShow(int id) {
        List<Show> shows = new ArrayList<>();
        List<String> path1 = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            path1.add(scene_draw[0][0]);
            path1.add(scene_draw[0][1]);
            path1.add(scene_draw[1][0]);
            path1.add(scene_draw[1][1]);
            path1.add(people_draw[0][0]);
            path1.add(people_draw[0][1]);
            path1.add(people_draw[1][0]);
            path1.add(people_draw[1][1]);
            path1.add(object_draw[0][0]);
            path1.add(object_draw[0][1]);
            path1.add(learn_draw[0][0]);
            path1.add(learn_draw[0][1]);
        }
        Show show1 = new Show();
        show1.setPaths(path1);
        shows.add(show1);
        return shows;
    }

    public static int getId(int groupid, int childid) {
        int id = 5;
        for (int i = 0; i < groupid; i++) {
            id += arms[i].length;
        }
        id += childid;
        return id;
    }

    public static int setFatherNode(int id) {
        if (id == 0)
            return -1;
        else if (id < 5 && id > 0)
            return 0;
        else if (id > 4) {
            int fid = 0;
            while (id >= 5) {
                id -= arms[fid].length;
                fid++;
            }
            return fid - 1;
        }

        return -1;

    }

    public static boolean setHasChild(int id) {
        if (id > 4)
            return false;
        else
            return true;
    }

    public static String setName(int id) {
        if (id < 5) {
            return armTypes[id];
        } else {
            int fid = setFatherNode(id);
            for (int i = 0; i < fid; i++) {
                id -= arms[i].length;
            }
            id -= 5;

            return arms[fid][id];
        }
    }
}
