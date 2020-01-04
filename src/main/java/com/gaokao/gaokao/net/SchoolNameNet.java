package com.gaokao.gaokao.net;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gaokao.gaokao.entity.School;
import com.gaokao.gaokao.entity.SchoolName;
import com.gaokao.gaokao.entity.Score;
import com.gaokao.gaokao.entity.scoreItem;
import com.gaokao.gaokao.mapper.SchoolDao;
import com.gaokao.gaokao.mapper.ScoreDao;
import com.gaokao.gaokao.service.SchoolService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.seimicrawler.xpath.JXDocument;
import org.springframework.beans.factory.annotation.Autowired;

@Crawler(name = "schoolName")
public class SchoolNameNet extends BaseSeimiCrawler {


    @Autowired
    private ScoreDao scoreDao;

    @Autowired
    private SchoolDao schoolDao;
    @Override
    public String[] startUrls() {
        return new String[]{"https://gaokao.chsi.com.cn/sch/search--ss-on,searchType-1,option-qg,start-120.dhtml"};
    }

    @Override
    public void start(Response response) {
        try {
            for (int i=1;i<=180;i++){
                String url = "https://api.eol.cn/gkcx/api/?access_token=&admissions=&central=&department=&dual_class=&f211=&f985=&is_dual_class=&keyword=&page="+i+"&province_id=&request_type=&school_type=&signsafe=&size=20&sort=view_total&type=&uri=apigkcx/api/school/hotlists";
                push(Request.build(url, SchoolNameNet::getSchool));
                Thread.sleep(1000);
                logger.info("schoolName i={}",i);

            }
        } catch (Exception e) {
           // e.printStackTrace();
        }
    }

    public void getSchool(Response response){
        JXDocument doc = response.document();
        List<Object> sel = doc.sel("//body/text()");

        JSONObject jsonObject = JSONObject.parseObject(sel.get(0).toString());
          String data = jsonObject.getString("data");
        String item = JSONObject.parseObject(data).getString("item");
        List<SchoolName> parseArray = JSONObject.parseArray(item, SchoolName.class);

        for (SchoolName schoolName : parseArray) {
            Score score = scoreDao.selectBySchoolCode(schoolName.getSchoolId());
            if(score!=null && "1".equals(score.getSchoolName())) {
                scoreDao.update(schoolName.getName(),schoolName.getSchoolId());
            }
            schoolDao.update(schoolName.getName(),schoolName.getF985()==2?0:schoolName.getF985(),schoolName.getF211()==2?0:schoolName.getF211(),schoolName.getSchoolId());
        }
    }
}
