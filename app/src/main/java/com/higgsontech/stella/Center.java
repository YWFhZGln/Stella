package com.higgsontech.stella;

/**
 * Created by aman on 28/3/17.
 */

public class Center {

    private int sno;
    private String name ;
    private String address;
    private String placeId;
    private double lat;
    private double lng;
    private int noOfRooms;
    private int studentCapacity;
    private int facultyCapacity;
    private String studentType;
   Center(int b,int c,String d){
       this.studentCapacity=b;
       this.facultyCapacity=c;
       this.studentType=d;

   }
   Center(int sno,String n,String add,int rn,int sc,int fc,String st){
       this.sno=sno;
       name=n;
       address=add;
       noOfRooms=rn;
       studentCapacity=sc;
       facultyCapacity=fc;
       studentType=st;


   }

   Center(int sno,String n,String add,String placeId,double lat,double lng){
       this.sno=sno;
       this.name=n;
       this.address=add;
       this.placeId=placeId;
       this.lat=lat;
       this.lng=lng;
   }

    public int getSno() {
        return sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getStudentCapacity() {
        return studentCapacity;
    }

    public void setStudentCapacity(int studentCapacity) {
        this.studentCapacity = studentCapacity;
    }

    public int getFacultyCapacity() {
        return facultyCapacity;
    }

    public void setFacultyCapacity(int facultyCapacity) {
        this.facultyCapacity = facultyCapacity;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }
}
