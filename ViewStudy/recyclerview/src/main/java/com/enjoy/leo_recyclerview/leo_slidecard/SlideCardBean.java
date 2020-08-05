package com.enjoy.leo_recyclerview.leo_slidecard;

import java.util.ArrayList;
import java.util.List;

public class SlideCardBean {
    private int postition;
    private String url;
    private String name;

    public SlideCardBean(int postition, String url, String name) {
        this.postition = postition;
        this.url = url;
        this.name = name;
    }

    public int getPostition() {
        return postition;
    }

    public SlideCardBean setPostition(int postition) {
        this.postition = postition;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public SlideCardBean setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getName() {
        return name;
    }

    public SlideCardBean setName(String name) {
        this.name = name;
        return this;
    }

    public static List<SlideCardBean> initDatas() {
        List<SlideCardBean> datas = new ArrayList<>();
        int i = 1;
        datas.add(new SlideCardBean(i++, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595434963213&di=5d07d9de35f42c16238c3076119a6e98&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fmobile%2F2018-12-13%2F5c120783eba2b.jpg", "美女1"));
        datas.add(new SlideCardBean(i++, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595435110291&di=67d1066bc7fc86a92fbf8b52de05398d&imgtype=0&src=http%3A%2F%2Fimg.tupianzj.com%2Fuploads%2Fallimg%2F150919%2F9-150919205I5.jpg", "美女2"));
        datas.add(new SlideCardBean(i++, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595435159388&di=c6581dc27ef4c1e956ac24d8b50bf8cf&imgtype=0&src=http%3A%2F%2Fimg.08087.cc%2Fuploads%2F20191223%2F18%2F1577098270-azHRMjKysW.jpg", "美女3"));
        datas.add(new SlideCardBean(i++, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595435251280&di=7de649efc7dbd900534870e226609c3f&imgtype=0&src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_match%2F0%2F11956700004%2F0.jpg", "美女4"));
        datas.add(new SlideCardBean(i++, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595435309398&di=f5a0ba4719386c298d98ad56394c61f4&imgtype=0&src=http%3A%2F%2Fimg.tupianzj.com%2Fuploads%2Fallimg%2F150921%2F9-1509210S604-50.jpg", "美女5"));
        datas.add(new SlideCardBean(i++, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595435349750&di=2958b535274265ef958d71fda1032ab2&imgtype=0&src=http%3A%2F%2Fimg3.yxlady.com%2Fmr%2FUploadFiles_9207%2F2015093%2F20150903115457296.jpg", "美女6"));
        datas.add(new SlideCardBean(i++, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560164210849&di=c6ea3fdd3ec938600ddde9022f46033c&imgtype=0&src=http%3A%2F%2Fbbs-fd.zol-img.com.cn%2Ft_s800x5000%2Fg4%2FM09%2F00%2F07%2FCg-4WlJA9zCIPZ8PAAQWAhRW0ssAAMA8wD2hYAABBYa996.jpg", "美女7"));
        datas.add(new SlideCardBean(i++, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1595435458268&di=a44e8862a2b5ecf8d7519188bd06c30b&imgtype=0&src=http%3A%2F%2Fwww.guanxiu.com%2Fuploads%2Fallimg%2Fc140918%2F14110351bS220-14617-lp.jpg","美女8"));
        return datas;
    }
}
