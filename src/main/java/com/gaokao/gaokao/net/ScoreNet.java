package com.gaokao.gaokao.net;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import com.alibaba.fastjson.JSONObject;
import com.gaokao.gaokao.entity.Score;
import com.gaokao.gaokao.entity.scoreItem;
import com.gaokao.gaokao.service.ScoreService;
import java.util.ArrayList;
import java.util.List;
import org.seimicrawler.xpath.JXDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

@Crawler(name = "sroce")
public class ScoreNet extends BaseSeimiCrawler {

    @Autowired
    ScoreService scoreService;

    @Override
    public String[] startUrls() {
        return new String[]{"https://gaokao.chsi.com.cn/sch/search--ss-on,searchType-1,option-qg,start-120.dhtml"};
    }

    @Override
    public void start(Response response) {
        int aaa = 0;
        try {
            for (int i=1;i<=26000;i++){//10060
                for (int year = 2014; year < 2019; year++) {
                    String url = "https://api.eol.cn/gkcx/api/?access_token=&admissions=&central=&department=&dual_class=&f211=&f985=&is_dual_class=&keyword=&local_batch_id=&local_type_id=&page="+i+"&province_id=&school_type=&signsafe=&size=20&type=&uri=apidata/api/gk/score/special&year="+year;
                    push(Request.build(url, ScoreNet::getScore));
                    Thread.sleep(100);
                }
            }
        } catch (Exception e) {
            System.out.println("aaa = " + aaa);
            e.printStackTrace();
        }
    }

    public void getScore(Response response){
        JXDocument doc = response.document();
        List<Object> sel = doc.sel("//body/text()");

        JSONObject jsonObject = JSONObject.parseObject(sel.get(0).toString());
        String data = jsonObject.getString("data");
        String item = JSONObject.parseObject(data).getString("item");
        List<scoreItem> list111 = JSONObject.parseArray(item, scoreItem.class);

        List<Score> list = new ArrayList<>();
        for (scoreItem scoreItem : list111) {
            Score s = new Score();
            s.setSchoolCode(scoreItem.getSchoolId());
            s.setSchoolName("1");
            s.setStudentType(scoreItem.getLocalTypeName());
            s.setSubjectName(scoreItem.getSpname());
            s.setYear(scoreItem.getYear());
            s.setAdmissionBatch(scoreItem.getLocalBatchName());
            s.setMaxScore(0);
            s.setAverageScore("--".equals(scoreItem.getAverage())?0:Integer.valueOf(scoreItem.getAverage()));
            s.setMinScore("--".equals(scoreItem.getProscore())?0:Integer.valueOf(scoreItem.getProscore()));
            s.setOther("");
            s.setRemark("");
            list.add(s);
        }
        System.out.println("list = " + list);
        if(!CollectionUtils.isEmpty(list)){
            scoreService.insertList(list);
        }
    }


}
