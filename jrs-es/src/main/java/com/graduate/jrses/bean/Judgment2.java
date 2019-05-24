//package com.graduate.jrses.bean;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.*;
//
//@Document(indexName = "apc",type = "message")
//public class Judgment2 {
//    @Id
//    private String id;//文书id
////    @Field(type = FieldType.Date,format = DateFormat.date)
////    private String judgeDate;
////    @Field(type = FieldType.Date,format = DateFormat.year)
////    private String date;
////    @Field(type = FieldType.Date,format = DateFormat.date_hour_minute_second)
////    private String date2;
//    @Field(type = FieldType.String,index = FieldIndex.analyzed)
//    private String caseType;
//    @Field(type = FieldType.String, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
//    private String message;
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public String getCaseType() {
//        return caseType;
//    }
//
//    public void setCaseType(String caseType) {
//        this.caseType = caseType;
//    }
//
//    //    public String getDate() {
////        return date;
////    }
////
////    public void setDate(String date) {
////        this.date = date;
////    }
//
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//}
