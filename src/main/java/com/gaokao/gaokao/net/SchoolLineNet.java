package com.gaokao.gaokao.net;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import com.alibaba.fastjson.JSONObject;
import com.gaokao.gaokao.entity.School;
import com.gaokao.gaokao.entity.SchoolLine;
import com.gaokao.gaokao.entity.Score;
import com.gaokao.gaokao.entity.scoreItem;
import com.gaokao.gaokao.mapper.SchoolDao;
import com.gaokao.gaokao.mapper.SchoolLineDao;
import com.gaokao.gaokao.service.ScoreService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.seimicrawler.xpath.JXDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

@Crawler(name = "schoolLine")
@Slf4j
public class SchoolLineNet extends BaseSeimiCrawler {

    @Autowired
    SchoolLineDao schoolLineDao;
    @Autowired
    SchoolDao schoolDao;

    @Override
    public String[] startUrls() {
        return new String[]{"https://gaokao.chsi.com.cn/sch/search--ss-on,searchType-1,option-qg,start-120.dhtml"};
    }

    @Override
    public void start(Response response) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(2014,2600);
        map.put(2015,2351);
        map.put(2016,2427);
        map.put(2017,3610);
        map.put(2018,3400);
        try {
            for (int year = 2014; year < 2019; year++) {
                for (int i=1;i<=map.get(year);i++){
                    String url = "https://api.eol.cn/gkcx/api/?access_token=&admissions=&central=&department=&dual_class=&f211=&f985=&is_dual_class=&keyword=&local_batch_id=&page="+i+"&province_id&school_type=&signsafe=&size=20&type=&uri=apidata/api/gk/score/province&year="+year;
                    push(Request.build(url, SchoolLineNet::getScore));
                    Thread.sleep(1000);
                    logger.info("sroce year={} i={}",year,i);

                }
            }
        } catch (Exception e) {
        }
    }

    public void getScore(Response response){
        JXDocument doc = response.document();
        List<Object> sel = doc.sel("//body/text()");

        JSONObject jsonObject = JSONObject.parseObject(sel.get(0).toString());
        String data = jsonObject.getString("data");
        String item = JSONObject.parseObject(data).getString("item");
        if("[]".equals(item)){
            return;
        }
        List<scoreItem> list111 = JSONObject.parseArray(item, scoreItem.class);

        List<SchoolLine> list = new ArrayList<>();
        for (scoreItem scoreItem : list111) {
            SchoolLine s = new SchoolLine();
            School school = schoolDao.selectBySchoolCode(scoreItem.getSchoolId());
            s.setSchoolCode(scoreItem.getSchoolId());
            s.setSchoolName(school==null?"":school.getName());
            s.setStudentType(scoreItem.getLocalTypeName());
            s.setYear(scoreItem.getYear());
            s.setAdmissionBatch(scoreItem.getLocalBatchName());
            s.setLocalProvinceName(scoreItem.getLocalProvinceName());
            s.setMaxScore("--".equals(scoreItem.getMax())?0:Integer.valueOf(scoreItem.getMax()));
            s.setAverageScore("--".equals(scoreItem.getAverage())?0:Integer.valueOf(scoreItem.getAverage()));
            s.setMinScore("--".equals(scoreItem.getProscore())?0:Integer.valueOf(scoreItem.getProscore()));
            s.setOther("");
            s.setRemark("");
            s.setProvinceLine("--".equals(scoreItem.getProscore())?0:Integer.valueOf(scoreItem.getProscore()));
            list.add(s);
        }
        if(!CollectionUtils.isEmpty(list)){
            logger.info("insert size = {}",list.size());
            schoolLineDao.insertList(list);
        }
    }


}
