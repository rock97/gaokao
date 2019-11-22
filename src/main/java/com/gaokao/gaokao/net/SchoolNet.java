package com.gaokao.gaokao.net;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import com.gaokao.gaokao.entity.School;
import com.gaokao.gaokao.service.SchoolService;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.seimicrawler.xpath.JXDocument;
import org.springframework.beans.factory.annotation.Autowired;

@Crawler(name = "school")
public class SchoolNet extends BaseSeimiCrawler {


    @Autowired
    private SchoolService schoolService;
    @Override
    public String[] startUrls() {
        return new String[]{"https://gaokao.chsi.com.cn/sch/search--ss-on,searchType-1,option-qg,start-120.dhtml"};
    }

    @Override
    public void start(Response response) {
        int aaa = 0;
        try {
            for (int i=1;i<=139;i++){
                aaa = i;
                String url = "https://gaokao.chsi.com.cn/sch/search--ss-on,searchType-1,option-qg,start-"+i*20+".dhtml";
                push(Request.build(url, SchoolNet::getSchool));
                Thread.sleep(1000);
                logger.info("school i={}",i);

            }
        } catch (Exception e) {

        }
    }

    public void getSchool(Response response){
        JXDocument doc = response.document();
        List<Object> sel = doc.sel("//tr");

        List<School> schoolList = new ArrayList<>();
        for (Object o : sel) {
            Element document =  ((Element) o);
            School school = buildSchool(document);
            schoolList.add(school);
        }

        bachInstet(schoolList);
    }

    public School buildSchool(Element document){
         String name= document.child(0).text();
         String city= document.child(1).text();
        String subjection= document.child(2).text();
        String type= document.child(3).text();
         String academicLevel= document.child(4).text();
         boolean isStreamUniversity= StringUtils.isEmpty(document.child(0).text())?false:true;
         boolean isStreamCourse= StringUtils.isEmpty(document.child(0).text())?false:true;
        School school = new School();
        school.set985(false);
        school.setName(name);
        school.set211(false);
        school.setAcademicLevel(academicLevel);
        school.setCity(city);
        school.setStreamCourse(isStreamCourse);
        school.setStreamUniversity(isStreamUniversity);
        school.setSubjection(subjection);
        school.setType(type);
        school.setOther("1");
        school.setRemark("1");
        school.setCode(0);
        return school;
    }
    private void bachInstet( List<School> schoolList){
        schoolService.insertList(schoolList.subList(1,schoolList.size()));
        logger.info("bachInsert size={}",schoolList.size());
    }
}
