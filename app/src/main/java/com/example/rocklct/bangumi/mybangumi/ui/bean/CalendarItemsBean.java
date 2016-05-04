package com.example.rocklct.bangumi.mybangumi.ui.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rocklct on 2016/4/20.
 */
public class CalendarItemsBean {
    public List<CalendarBean> weekitem = new ArrayList<>();


    public class CalendarBean{
        public class Item{
            public String id;
            public String name;
            public String name_cn;
            public float score;
            public ImageURL images;
            public class ImageURL{
                public String large;
                public String common;
                public String medium;
                public String small;
                public String grid;
            }
        }

        public List<Item> items = new ArrayList<>();
    }


}
