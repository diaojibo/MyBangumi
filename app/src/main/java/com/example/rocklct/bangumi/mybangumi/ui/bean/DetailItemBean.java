package com.example.rocklct.bangumi.mybangumi.ui.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rocklct on 2016/5/7.
 */
public class DetailItemBean extends BaseBean {

    public String id;
    public String summary;
    public String name;
    public String name_cn;

    //放送日期
    public String air_date;
    public List<crtBean> crt = new ArrayList<crtBean>();
    public List<staffBean> staff = new ArrayList<>();


    public DeatilInfoBean getinfobean() {
        return new DeatilInfoBean(id, air_date, summary, name, name_cn);
    }

    public class DeatilInfoBean extends BaseBean {

        public String id;
        public String air_date;
        public String summary;
        public String name;
        public String name_cn;

        public DeatilInfoBean(String id, String air_date, String summary, String name, String name_cn) {
            this.id = id;
            this.air_date = air_date;
            this.summary = summary;
            this.name = name;
            this.name_cn = name_cn;
            if (name_cn == "") {
                this.name_cn = this.name;
            }
        }
    }


    public class imageBean {
        public String large;
        public String medium;
        public String small;
        public String grid;
    }


    public class crtBean extends BaseBean {
        public String id;
        public String name_cn;
        public String name;
        public String role_name;
        public imageBean images;
        public List<actorBean> actors;

        public String getCv() {
            String nn = "";
            if (actors != null) {
                actorBean actb = actors.get(0);
                nn = actb.name;
            }
            return nn;
        }

        public class actorBean {
            public String name;
        }

        public class imageBean {
            public String large;
            public String medium;
            public String small;
            public String grid;
        }

        public String cv;
    }

    public class staffBean {
        String id;
        String name;
        String name_cn;
        String role_name;
        imageBean images;
        ArrayList<String> jobs = new ArrayList<>();
    }

}
