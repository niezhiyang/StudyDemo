package cn.nzy.retrofitdemo.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * on 2017/12/12.
 * 类的描述:
 */

public class MovicBean {
    /**
     * control : {"expires":1800}
     * data : {"hasNext":true,"movies":[{"3d":true,"cat":"动画,冒险,家庭","cnms":0,"dir":"李·昂克里奇,阿德里安.莫利纳","dur":105,"id":342068,"imax":false,"img":"http://p1.meituan.net/165.220/movie/4ba0ebccc0e3115911bfecdcfbbafa1a5114683.jpg","late":false,"nm":"寻梦环游记","pn":52,"preSale":0,"rt":"2017-11-24上映","sc":9.6,"scm":"","showDate":"","showInfo":"今天182家影院放映2475场","sn":0,"snum":354431,"src":"","star":"安东尼·冈萨雷斯,本杰明·布拉特,盖尔·加西亚·贝纳尔","time":"","vd":"","ver":"2D/3D","wish":16241},{"3d":false,"cat":"喜剧,动画,冒险","cnms":0,"dir":"保罗·金","dur":103,"id":343905,"imax":true,"img":"http://p1.meituan.net/165.220/movie/1ae0b6ef3773e5fa784b882d4f1ba0516626165.jpg","late":false,"nm":"帕丁顿熊2","pn":141,"preSale":0,"rt":"2017-12-08上映","sc":9.3,"scm":"","showDate":"","showInfo":"今天183家影院放映1633场","sn":0,"snum":42376,"src":"","star":"本·威士肖,杜江,休·格兰特","time":"","vd":"","ver":"2D/IMAX 2D/中国巨幕","wish":39888},{"3d":false,"cat":"剧情,爱情,战争","cnms":0,"dir":"冯小刚","dur":136,"id":1170264,"imax":true,"img":"http://p0.meituan.net/165.220/movie/fe0d4da87d70ba2b91e10ac98e0bf5ef1365131.png","late":false,"nm":"芳华","pn":420,"preSale":1,"rt":"本周五上映","sc":9.1,"scm":"少年文工团，青春很茫然","showDate":"","showInfo":"2017-12-15 本周五上映","sn":0,"snum":17604,"src":"","star":"黄轩,苗苗,钟楚曦","time":"","vd":"","ver":"2D/IMAX 2D/中国巨幕","wish":154095},{"3d":true,"cat":"喜剧,动作,奇幻","cnms":0,"dir":"袁和平","dur":113,"id":344183,"imax":true,"img":"http://p1.meituan.net/165.220/movie/ef63aac77aeaba4c1a4cbbc22f51aefc955763.jpg","late":false,"nm":"奇门遁甲","pn":158,"preSale":1,"rt":"本周五上映","sc":7.9,"scm":"天地有妖气，逗逼救地球","showDate":"","showInfo":"2017-12-15 本周五上映","sn":0,"snum":46196,"src":"","star":"大鹏,倪妮,李治廷","time":"","vd":"","ver":"2D/3D/IMAX 3D/中国巨幕","wish":163851},{"3d":false,"cat":"剧情,动画,传记","cnms":0,"dir":"多洛塔·科别拉,休·威尔士曼","dur":95,"id":337443,"imax":false,"img":"http://p0.meituan.net/165.220/movie/baa62fa68a0d243ee14074f155092bca131722.jpg","late":false,"nm":"至爱梵高·星空之谜","pn":103,"preSale":0,"rt":"2017-12-08上映","sc":9.2,"scm":"","showDate":"","showInfo":"今天176家影院放映1059场","sn":0,"snum":16450,"src":"","star":"道格拉斯·布斯,罗伯特·古拉奇克,杰罗姆·弗林","time":"","vd":"","ver":"2D","wish":31672},{"3d":false,"cat":"剧情,动作,犯罪","cnms":0,"dir":"徐纪周","dur":124,"id":1197732,"imax":false,"img":"http://p0.meituan.net/165.220/movie/f56e16bf98ac1e5dcbe2f70f9f8ecb3b811616.jpg","late":false,"nm":"心理罪之城市之光","pn":230,"preSale":1,"rt":"下周五上映","sc":0,"scm":"连环凶杀案，城市陷狂欢","showDate":"","showInfo":"2017-12-22 下周五上映","sn":0,"snum":4956,"src":"","star":"邓超,阮经天,刘诗诗","time":"","vd":"","ver":"2D/中国巨幕","wish":278920},{"3d":false,"cat":"爱情,奇幻","cnms":0,"dir":"陈凯歌","dur":129,"id":345862,"imax":true,"img":"http://p0.meituan.net/165.220/movie/821b3303bb6eae4889430f2e114f6ed0857167.jpg","late":false,"nm":"妖猫传","pn":228,"preSale":1,"rt":"下周五上映","sc":0,"scm":"妖猫入长安，空海查迷案","showDate":"","showInfo":"2017-12-22 下周五上映","sn":0,"snum":1759,"src":"","star":"黄轩,染谷将太,张雨绮","time":"","vd":"","ver":"2D/IMAX 2D","wish":179225},{"3d":false,"cat":"剧情,犯罪","cnms":0,"dir":"彭顺","dur":123,"id":1203120,"imax":false,"img":"http://p0.meituan.net/165.220/movie/c2b43e01c486395bc324d5f80c35a73a493968.jpg","late":false,"nm":"巨额来电","pn":91,"preSale":0,"rt":"2017-12-08上映","sc":8.4,"scm":"猜猜我是谁，猜到吓死鬼","showDate":"","showInfo":"今天149家影院放映462场","sn":0,"snum":15328,"src":"","star":"陈学冬,张孝全,桂纶镁","time":"","vd":"","ver":"2D/中国巨幕","wish":13773},{"3d":false,"cat":"恐怖,惊悚,冒险","cnms":0,"dir":"约翰内斯·罗伯茨","dur":90,"id":930966,"imax":false,"img":"http://p1.meituan.net/165.220/movie/791494d9ad13ad40181d5f67d0178d46201147.jpg","late":false,"nm":"鲨海","pn":90,"preSale":0,"rt":"2017-12-08上映","sc":6.5,"scm":"","showDate":"","showInfo":"今天148家影院放映458场","sn":0,"snum":6935,"src":"","star":"曼迪·摩尔,克莱尔·霍尔特,马修·莫迪恩","time":"","vd":"","ver":"2D/中国巨幕","wish":17851},{"3d":true,"cat":"动作,悬疑,科幻","cnms":0,"dir":"张立嘉","dur":110,"id":655258,"imax":true,"img":"http://p0.meituan.net/165.220/movie/73a9482272fe76a1fdec37a5c7f26606251790.jpg","late":false,"nm":"机器之血","pn":96,"preSale":1,"rt":"下周五上映","sc":0,"scm":"血液藏隐秘，大哥战悉尼","showDate":"","showInfo":"2017-12-22 下周五上映","sn":0,"snum":1354,"src":"","star":"成龙,罗志祥,欧阳娜娜","time":"","vd":"","ver":"2D/3D/IMAX 3D/中国巨幕/全景声","wish":65784},{"3d":false,"cat":"西部,爱情,战争","cnms":0,"dir":"杨蕊","dur":97,"id":1208644,"imax":false,"img":"http://p0.meituan.net/165.220/movie/7316b82d684dbdbecdffa7970e897006999152.jpg","late":false,"nm":"金珠玛米","pn":153,"preSale":0,"rt":"本周二上映","sc":0,"scm":"","showDate":"","showInfo":"今天128家影院放映411场","sn":0,"snum":1190,"src":"","star":"王紫逸,多布杰,杨秀措","time":"","vd":"","ver":"2D","wish":3170},{"3d":false,"cat":"剧情,历史,传记","cnms":0,"dir":"乔·赖特","dur":125,"id":346625,"imax":false,"img":"http://p1.meituan.net/165.220/movie/6617ca11895da44065e9bcf378d0f62c252496.jpg","late":false,"nm":"至暗时刻","pn":34,"preSale":0,"rt":"2017-12-01上映","sc":8.9,"scm":"","showDate":"","showInfo":"今天92家影院放映311场","sn":0,"snum":13378,"src":"","star":"加里·奥德曼,克里斯汀·斯科特·托马斯,莉莉·詹姆斯","time":"","vd":"","ver":"2D/中国巨幕","wish":5423},{"3d":true,"cat":"喜剧,奇幻","cnms":0,"dir":"肖洋","dur":110,"id":368135,"imax":false,"img":"http://p0.meituan.net/165.220/movie/2969d1dc1040b4d6873ba13d028035ab2312192.jpg","late":false,"nm":"二代妖精之今生有幸","pn":125,"preSale":1,"rt":"2017-12-29上映","sc":0,"scm":"人与狐妖恋，为爱闯妖界","showDate":"","showInfo":"2017-12-29上映","sn":0,"snum":1028,"src":"","star":"冯绍峰,刘亦菲,李光洁","time":"","vd":"","ver":"2D/3D/中国巨幕","wish":59431},{"3d":false,"cat":"喜剧","cnms":0,"dir":"吴君如","dur":0,"id":1200984,"imax":false,"img":"http://p1.meituan.net/165.220/movie/883435391f0bda1f1863ed70a54b93e3378563.jpg","late":false,"nm":"妖铃铃","pn":70,"preSale":1,"rt":"2017-12-29上映","sc":0,"scm":"","showDate":"","showInfo":"2017-12-29上映","sn":0,"snum":455,"src":"","star":"吴君如,沈腾,岳云鹏","time":"","vd":"","ver":"2D","wish":64696},{"3d":false,"cat":"剧情,传记,灾难","cnms":0,"dir":"约瑟夫·科辛斯基","dur":127,"id":368228,"imax":false,"img":"http://p1.meituan.net/165.220/movie/bc1a4c9f3a3a24e3ff2242d5990976366016781.jpg","late":false,"nm":"勇往直前","pn":86,"preSale":0,"rt":"2017-12-08上映","sc":8.8,"scm":"","showDate":"","showInfo":"今天112家影院放映221场","sn":0,"snum":4017,"src":"","star":"乔什·布洛林,迈尔斯·特勒,杰夫·布里吉斯","time":"","vd":"","ver":"2D","wish":5732},{"3d":false,"cat":"剧情","cnms":0,"dir":"周子阳","dur":110,"id":1207099,"imax":false,"img":"http://p1.meituan.net/165.220/movie/ab6a7047e10905986468a7d4f76401ba1010475.jpg","late":false,"nm":"老兽","pn":55,"preSale":0,"rt":"本周一上映","sc":7.8,"scm":"不顾养育恩，父爱难开口","showDate":"","showInfo":"今天87家影院放映190场","sn":0,"snum":279,"src":"","star":"涂们,王超北,阿拉腾乌拉","time":"","vd":"","ver":"2D","wish":3554},{"3d":true,"cat":"动作,冒险,科幻","cnms":0,"dir":"扎克·施奈德","dur":120,"id":341195,"imax":true,"img":"http://p1.meituan.net/165.220/movie/8798032469af2faf18e531f7cdedc39e998644.jpg","late":false,"nm":"正义联盟","pn":286,"preSale":0,"rt":"2017-11-17上映","sc":8.6,"scm":"","showDate":"","showInfo":"今天63家影院放映158场","sn":0,"snum":254888,"src":"","star":"本·阿弗莱克,亨利·卡维尔,盖尔·加朵","time":"","vd":"","ver":"3D/IMAX 3D/中国巨幕/全景声","wish":176293},{"3d":false,"cat":"喜剧,动作","cnms":0,"dir":"丹尼·伯恩","dur":107,"id":1140198,"imax":false,"img":"http://p1.meituan.net/165.220/movie/4484270052c8d416680f20773cd8aff92022765.jpg","late":false,"nm":"疯狂特警队","pn":27,"preSale":0,"rt":"2017-12-08上映","sc":8.2,"scm":"","showDate":"","showInfo":"今天63家影院放映105场","sn":0,"snum":1112,"src":"","star":"丹尼·伯恩,爱丽丝·波尔,安·玛丽文","time":"","vd":"","ver":"2D","wish":3336},{"3d":false,"cat":"喜剧,爱情","cnms":0,"dir":"王郢","dur":101,"id":1198459,"imax":false,"img":"http://p0.meituan.net/165.220/movie/68bcd16889a6b7cff006b1d3e115f13e237668.jpg","late":false,"nm":"假如王子睡着了","pn":117,"preSale":0,"rt":"2017-12-08上映","sc":7.7,"scm":"孤独灰姑娘，误成未婚妻","showDate":"","showInfo":"今天47家影院放映67场","sn":0,"snum":3966,"src":"","star":"陈柏霖,林允,张云龙","time":"","vd":"","ver":"2D","wish":12137},{"3d":false,"cat":"喜剧,家庭","cnms":0,"dir":"阿兰·夏巴","dur":99,"id":1206402,"imax":false,"img":"http://p0.meituan.net/165.220/movie/865fd595909f4bba8d67575063d413973232890.jpg","late":false,"nm":"圣诞奇妙公司","pn":59,"preSale":1,"rt":"本周五上映","sc":0,"scm":"","showDate":"","showInfo":"2017-12-15 本周五上映","sn":0,"snum":172,"src":"","star":"阿兰·夏巴,奥黛丽·塔图,皮奥·马麦","time":"","vd":"","ver":"2D","wish":5623}]}
     * status : 0
     */

    private ControlBean control;
    private DataBean data;
    private int status;

    public ControlBean getControl() {
        return control;
    }

    public void setControl(ControlBean control) {
        this.control = control;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ControlBean {
        /**
         * expires : 1800
         */

        private int expires;

        public int getExpires() {
            return expires;
        }

        public void setExpires(int expires) {
            this.expires = expires;
        }
    }

    public static class DataBean {
        /**
         * hasNext : true
         * movies : [{"3d":true,"cat":"动画,冒险,家庭","cnms":0,"dir":"李·昂克里奇,阿德里安.莫利纳","dur":105,"id":342068,"imax":false,"img":"http://p1.meituan.net/165.220/movie/4ba0ebccc0e3115911bfecdcfbbafa1a5114683.jpg","late":false,"nm":"寻梦环游记","pn":52,"preSale":0,"rt":"2017-11-24上映","sc":9.6,"scm":"","showDate":"","showInfo":"今天182家影院放映2475场","sn":0,"snum":354431,"src":"","star":"安东尼·冈萨雷斯,本杰明·布拉特,盖尔·加西亚·贝纳尔","time":"","vd":"","ver":"2D/3D","wish":16241},{"3d":false,"cat":"喜剧,动画,冒险","cnms":0,"dir":"保罗·金","dur":103,"id":343905,"imax":true,"img":"http://p1.meituan.net/165.220/movie/1ae0b6ef3773e5fa784b882d4f1ba0516626165.jpg","late":false,"nm":"帕丁顿熊2","pn":141,"preSale":0,"rt":"2017-12-08上映","sc":9.3,"scm":"","showDate":"","showInfo":"今天183家影院放映1633场","sn":0,"snum":42376,"src":"","star":"本·威士肖,杜江,休·格兰特","time":"","vd":"","ver":"2D/IMAX 2D/中国巨幕","wish":39888},{"3d":false,"cat":"剧情,爱情,战争","cnms":0,"dir":"冯小刚","dur":136,"id":1170264,"imax":true,"img":"http://p0.meituan.net/165.220/movie/fe0d4da87d70ba2b91e10ac98e0bf5ef1365131.png","late":false,"nm":"芳华","pn":420,"preSale":1,"rt":"本周五上映","sc":9.1,"scm":"少年文工团，青春很茫然","showDate":"","showInfo":"2017-12-15 本周五上映","sn":0,"snum":17604,"src":"","star":"黄轩,苗苗,钟楚曦","time":"","vd":"","ver":"2D/IMAX 2D/中国巨幕","wish":154095},{"3d":true,"cat":"喜剧,动作,奇幻","cnms":0,"dir":"袁和平","dur":113,"id":344183,"imax":true,"img":"http://p1.meituan.net/165.220/movie/ef63aac77aeaba4c1a4cbbc22f51aefc955763.jpg","late":false,"nm":"奇门遁甲","pn":158,"preSale":1,"rt":"本周五上映","sc":7.9,"scm":"天地有妖气，逗逼救地球","showDate":"","showInfo":"2017-12-15 本周五上映","sn":0,"snum":46196,"src":"","star":"大鹏,倪妮,李治廷","time":"","vd":"","ver":"2D/3D/IMAX 3D/中国巨幕","wish":163851},{"3d":false,"cat":"剧情,动画,传记","cnms":0,"dir":"多洛塔·科别拉,休·威尔士曼","dur":95,"id":337443,"imax":false,"img":"http://p0.meituan.net/165.220/movie/baa62fa68a0d243ee14074f155092bca131722.jpg","late":false,"nm":"至爱梵高·星空之谜","pn":103,"preSale":0,"rt":"2017-12-08上映","sc":9.2,"scm":"","showDate":"","showInfo":"今天176家影院放映1059场","sn":0,"snum":16450,"src":"","star":"道格拉斯·布斯,罗伯特·古拉奇克,杰罗姆·弗林","time":"","vd":"","ver":"2D","wish":31672},{"3d":false,"cat":"剧情,动作,犯罪","cnms":0,"dir":"徐纪周","dur":124,"id":1197732,"imax":false,"img":"http://p0.meituan.net/165.220/movie/f56e16bf98ac1e5dcbe2f70f9f8ecb3b811616.jpg","late":false,"nm":"心理罪之城市之光","pn":230,"preSale":1,"rt":"下周五上映","sc":0,"scm":"连环凶杀案，城市陷狂欢","showDate":"","showInfo":"2017-12-22 下周五上映","sn":0,"snum":4956,"src":"","star":"邓超,阮经天,刘诗诗","time":"","vd":"","ver":"2D/中国巨幕","wish":278920},{"3d":false,"cat":"爱情,奇幻","cnms":0,"dir":"陈凯歌","dur":129,"id":345862,"imax":true,"img":"http://p0.meituan.net/165.220/movie/821b3303bb6eae4889430f2e114f6ed0857167.jpg","late":false,"nm":"妖猫传","pn":228,"preSale":1,"rt":"下周五上映","sc":0,"scm":"妖猫入长安，空海查迷案","showDate":"","showInfo":"2017-12-22 下周五上映","sn":0,"snum":1759,"src":"","star":"黄轩,染谷将太,张雨绮","time":"","vd":"","ver":"2D/IMAX 2D","wish":179225},{"3d":false,"cat":"剧情,犯罪","cnms":0,"dir":"彭顺","dur":123,"id":1203120,"imax":false,"img":"http://p0.meituan.net/165.220/movie/c2b43e01c486395bc324d5f80c35a73a493968.jpg","late":false,"nm":"巨额来电","pn":91,"preSale":0,"rt":"2017-12-08上映","sc":8.4,"scm":"猜猜我是谁，猜到吓死鬼","showDate":"","showInfo":"今天149家影院放映462场","sn":0,"snum":15328,"src":"","star":"陈学冬,张孝全,桂纶镁","time":"","vd":"","ver":"2D/中国巨幕","wish":13773},{"3d":false,"cat":"恐怖,惊悚,冒险","cnms":0,"dir":"约翰内斯·罗伯茨","dur":90,"id":930966,"imax":false,"img":"http://p1.meituan.net/165.220/movie/791494d9ad13ad40181d5f67d0178d46201147.jpg","late":false,"nm":"鲨海","pn":90,"preSale":0,"rt":"2017-12-08上映","sc":6.5,"scm":"","showDate":"","showInfo":"今天148家影院放映458场","sn":0,"snum":6935,"src":"","star":"曼迪·摩尔,克莱尔·霍尔特,马修·莫迪恩","time":"","vd":"","ver":"2D/中国巨幕","wish":17851},{"3d":true,"cat":"动作,悬疑,科幻","cnms":0,"dir":"张立嘉","dur":110,"id":655258,"imax":true,"img":"http://p0.meituan.net/165.220/movie/73a9482272fe76a1fdec37a5c7f26606251790.jpg","late":false,"nm":"机器之血","pn":96,"preSale":1,"rt":"下周五上映","sc":0,"scm":"血液藏隐秘，大哥战悉尼","showDate":"","showInfo":"2017-12-22 下周五上映","sn":0,"snum":1354,"src":"","star":"成龙,罗志祥,欧阳娜娜","time":"","vd":"","ver":"2D/3D/IMAX 3D/中国巨幕/全景声","wish":65784},{"3d":false,"cat":"西部,爱情,战争","cnms":0,"dir":"杨蕊","dur":97,"id":1208644,"imax":false,"img":"http://p0.meituan.net/165.220/movie/7316b82d684dbdbecdffa7970e897006999152.jpg","late":false,"nm":"金珠玛米","pn":153,"preSale":0,"rt":"本周二上映","sc":0,"scm":"","showDate":"","showInfo":"今天128家影院放映411场","sn":0,"snum":1190,"src":"","star":"王紫逸,多布杰,杨秀措","time":"","vd":"","ver":"2D","wish":3170},{"3d":false,"cat":"剧情,历史,传记","cnms":0,"dir":"乔·赖特","dur":125,"id":346625,"imax":false,"img":"http://p1.meituan.net/165.220/movie/6617ca11895da44065e9bcf378d0f62c252496.jpg","late":false,"nm":"至暗时刻","pn":34,"preSale":0,"rt":"2017-12-01上映","sc":8.9,"scm":"","showDate":"","showInfo":"今天92家影院放映311场","sn":0,"snum":13378,"src":"","star":"加里·奥德曼,克里斯汀·斯科特·托马斯,莉莉·詹姆斯","time":"","vd":"","ver":"2D/中国巨幕","wish":5423},{"3d":true,"cat":"喜剧,奇幻","cnms":0,"dir":"肖洋","dur":110,"id":368135,"imax":false,"img":"http://p0.meituan.net/165.220/movie/2969d1dc1040b4d6873ba13d028035ab2312192.jpg","late":false,"nm":"二代妖精之今生有幸","pn":125,"preSale":1,"rt":"2017-12-29上映","sc":0,"scm":"人与狐妖恋，为爱闯妖界","showDate":"","showInfo":"2017-12-29上映","sn":0,"snum":1028,"src":"","star":"冯绍峰,刘亦菲,李光洁","time":"","vd":"","ver":"2D/3D/中国巨幕","wish":59431},{"3d":false,"cat":"喜剧","cnms":0,"dir":"吴君如","dur":0,"id":1200984,"imax":false,"img":"http://p1.meituan.net/165.220/movie/883435391f0bda1f1863ed70a54b93e3378563.jpg","late":false,"nm":"妖铃铃","pn":70,"preSale":1,"rt":"2017-12-29上映","sc":0,"scm":"","showDate":"","showInfo":"2017-12-29上映","sn":0,"snum":455,"src":"","star":"吴君如,沈腾,岳云鹏","time":"","vd":"","ver":"2D","wish":64696},{"3d":false,"cat":"剧情,传记,灾难","cnms":0,"dir":"约瑟夫·科辛斯基","dur":127,"id":368228,"imax":false,"img":"http://p1.meituan.net/165.220/movie/bc1a4c9f3a3a24e3ff2242d5990976366016781.jpg","late":false,"nm":"勇往直前","pn":86,"preSale":0,"rt":"2017-12-08上映","sc":8.8,"scm":"","showDate":"","showInfo":"今天112家影院放映221场","sn":0,"snum":4017,"src":"","star":"乔什·布洛林,迈尔斯·特勒,杰夫·布里吉斯","time":"","vd":"","ver":"2D","wish":5732},{"3d":false,"cat":"剧情","cnms":0,"dir":"周子阳","dur":110,"id":1207099,"imax":false,"img":"http://p1.meituan.net/165.220/movie/ab6a7047e10905986468a7d4f76401ba1010475.jpg","late":false,"nm":"老兽","pn":55,"preSale":0,"rt":"本周一上映","sc":7.8,"scm":"不顾养育恩，父爱难开口","showDate":"","showInfo":"今天87家影院放映190场","sn":0,"snum":279,"src":"","star":"涂们,王超北,阿拉腾乌拉","time":"","vd":"","ver":"2D","wish":3554},{"3d":true,"cat":"动作,冒险,科幻","cnms":0,"dir":"扎克·施奈德","dur":120,"id":341195,"imax":true,"img":"http://p1.meituan.net/165.220/movie/8798032469af2faf18e531f7cdedc39e998644.jpg","late":false,"nm":"正义联盟","pn":286,"preSale":0,"rt":"2017-11-17上映","sc":8.6,"scm":"","showDate":"","showInfo":"今天63家影院放映158场","sn":0,"snum":254888,"src":"","star":"本·阿弗莱克,亨利·卡维尔,盖尔·加朵","time":"","vd":"","ver":"3D/IMAX 3D/中国巨幕/全景声","wish":176293},{"3d":false,"cat":"喜剧,动作","cnms":0,"dir":"丹尼·伯恩","dur":107,"id":1140198,"imax":false,"img":"http://p1.meituan.net/165.220/movie/4484270052c8d416680f20773cd8aff92022765.jpg","late":false,"nm":"疯狂特警队","pn":27,"preSale":0,"rt":"2017-12-08上映","sc":8.2,"scm":"","showDate":"","showInfo":"今天63家影院放映105场","sn":0,"snum":1112,"src":"","star":"丹尼·伯恩,爱丽丝·波尔,安·玛丽文","time":"","vd":"","ver":"2D","wish":3336},{"3d":false,"cat":"喜剧,爱情","cnms":0,"dir":"王郢","dur":101,"id":1198459,"imax":false,"img":"http://p0.meituan.net/165.220/movie/68bcd16889a6b7cff006b1d3e115f13e237668.jpg","late":false,"nm":"假如王子睡着了","pn":117,"preSale":0,"rt":"2017-12-08上映","sc":7.7,"scm":"孤独灰姑娘，误成未婚妻","showDate":"","showInfo":"今天47家影院放映67场","sn":0,"snum":3966,"src":"","star":"陈柏霖,林允,张云龙","time":"","vd":"","ver":"2D","wish":12137},{"3d":false,"cat":"喜剧,家庭","cnms":0,"dir":"阿兰·夏巴","dur":99,"id":1206402,"imax":false,"img":"http://p0.meituan.net/165.220/movie/865fd595909f4bba8d67575063d413973232890.jpg","late":false,"nm":"圣诞奇妙公司","pn":59,"preSale":1,"rt":"本周五上映","sc":0,"scm":"","showDate":"","showInfo":"2017-12-15 本周五上映","sn":0,"snum":172,"src":"","star":"阿兰·夏巴,奥黛丽·塔图,皮奥·马麦","time":"","vd":"","ver":"2D","wish":5623}]
         */

        private boolean hasNext;
        private List<MoviesBean> movies;

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public List<MoviesBean> getMovies() {
            return movies;
        }

        public void setMovies(List<MoviesBean> movies) {
            this.movies = movies;
        }

        public static class MoviesBean {
            /**
             * 3d : true
             * cat : 动画,冒险,家庭
             * cnms : 0
             * dir : 李·昂克里奇,阿德里安.莫利纳
             * dur : 105
             * id : 342068
             * imax : false
             * img : http://p1.meituan.net/165.220/movie/4ba0ebccc0e3115911bfecdcfbbafa1a5114683.jpg
             * late : false
             * nm : 寻梦环游记
             * pn : 52
             * preSale : 0
             * rt : 2017-11-24上映
             * sc : 9.6
             * scm :
             * showDate :
             * showInfo : 今天182家影院放映2475场
             * sn : 0
             * snum : 354431
             * src :
             * star : 安东尼·冈萨雷斯,本杰明·布拉特,盖尔·加西亚·贝纳尔
             * time :
             * vd :
             * ver : 2D/3D
             * wish : 16241
             */

            @SerializedName("3d")
            private boolean _$3d;
            private String cat;
            private int cnms;
            private String dir;
            private int dur;
            private int id;
            private boolean imax;
            private String img;
            private boolean late;
            private String nm;
            private int pn;
            private int preSale;
            private String rt;
            private double sc;
            private String scm;
            private String showDate;
            private String showInfo;
            private int sn;
            private int snum;
            private String src;
            private String star;
            private String time;
            private String vd;
            private String ver;
            private int wish;

            public boolean is_$3d() {
                return _$3d;
            }

            public void set_$3d(boolean _$3d) {
                this._$3d = _$3d;
            }

            public String getCat() {
                return cat;
            }

            public void setCat(String cat) {
                this.cat = cat;
            }

            public int getCnms() {
                return cnms;
            }

            public void setCnms(int cnms) {
                this.cnms = cnms;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public int getDur() {
                return dur;
            }

            public void setDur(int dur) {
                this.dur = dur;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isImax() {
                return imax;
            }

            public void setImax(boolean imax) {
                this.imax = imax;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public boolean isLate() {
                return late;
            }

            public void setLate(boolean late) {
                this.late = late;
            }

            public String getNm() {
                return nm;
            }

            public void setNm(String nm) {
                this.nm = nm;
            }

            public int getPn() {
                return pn;
            }

            public void setPn(int pn) {
                this.pn = pn;
            }

            public int getPreSale() {
                return preSale;
            }

            public void setPreSale(int preSale) {
                this.preSale = preSale;
            }

            public String getRt() {
                return rt;
            }

            public void setRt(String rt) {
                this.rt = rt;
            }

            public double getSc() {
                return sc;
            }

            public void setSc(double sc) {
                this.sc = sc;
            }

            public String getScm() {
                return scm;
            }

            public void setScm(String scm) {
                this.scm = scm;
            }

            public String getShowDate() {
                return showDate;
            }

            public void setShowDate(String showDate) {
                this.showDate = showDate;
            }

            public String getShowInfo() {
                return showInfo;
            }

            public void setShowInfo(String showInfo) {
                this.showInfo = showInfo;
            }

            public int getSn() {
                return sn;
            }

            public void setSn(int sn) {
                this.sn = sn;
            }

            public int getSnum() {
                return snum;
            }

            public void setSnum(int snum) {
                this.snum = snum;
            }

            public String getSrc() {
                return src;
            }

            public void setSrc(String src) {
                this.src = src;
            }

            public String getStar() {
                return star;
            }

            public void setStar(String star) {
                this.star = star;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getVd() {
                return vd;
            }

            public void setVd(String vd) {
                this.vd = vd;
            }

            public String getVer() {
                return ver;
            }

            public void setVer(String ver) {
                this.ver = ver;
            }

            public int getWish() {
                return wish;
            }

            public void setWish(int wish) {
                this.wish = wish;
            }
        }
    }
}
