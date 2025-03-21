package com.ohgiraffers.restapi.section05.swagger;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

@Schema(description = "회원 정보 DTO")
public class UserDTO {

    @Schema(description = "회원번호(PK)")
    private int number;

    @Schema(description = "회원 ID")
    private String id;
    private String pwd;
    private String name;
    private Date enrollDate;

    public UserDTO() {
    }

    public UserDTO(int number, String id, String pwd, String name, Date enrollDate) {
        this.number = number;
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.enrollDate = enrollDate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "number=" + number +
            ", id='" + id + '\'' +
            ", pwd='" + pwd + '\'' +
            ", name='" + name + '\'' +
            ", enrollDate=" + enrollDate +
            '}';
    }
}
