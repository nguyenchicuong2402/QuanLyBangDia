package org.buffalocoder.quanlybangdia.models;

import java.sql.Date;
import java.util.Objects;

public class PersonModel {
    private long id_card;                // chứng minh nhân dân - khoá chính
    private String fullName;            // tên đầy đủ
    private boolean sex;                // giới tính
    private String phone;               // số điện thoại
    private String address;             // địa chỉ
    private Date birthday;              // ngày sinh

    /**
     * Get ID Card
     * @return
     */
    public long getId_card() {
        return id_card;
    }

    /**
     * Set ID Card
     * @param id
     */
    public void setId_card(long id_card) {
        this.id_card = id_card;
    }

    /**
     * Get fullname
     * @return
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Set fullname
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Get sex
     * @return
     */
    public boolean isSex() {
        return sex;
    }

    /**
     * Set sex
     * @param sex
     */
    public void setSex(boolean sex) {
        this.sex = sex;
    }

    /**
     * Get phone
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get birthday
     * @return
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Set birthday
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Constructor empty
     */
    public PersonModel() {
    }

    /**
     * Constructor with params
     * @param id
     * @param fullName
     * @param sex
     * @param phone
     * @param address
     * @param birthday
     */
    public PersonModel(long id_card, String fullName, boolean sex, String phone, String address, Date birthday) {
        this.setId_card(id_card);
        this.setFullName(fullName);
        this.setSex(sex);
        this.setPhone(phone);
        this.setAddress(address);
        this.setBirthday(birthday);
    }

    /**
     * Override equals()
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonModel that = (PersonModel) o;
        return id_card == that.id_card;
    }

    /**
     * Override hashCode()
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id_card);
    }

    /**
     * Override toString()
     * @return
     */
    @Override
    public String toString() {
        return "PersonModel{" +
                "id_card=" + id_card +
                ", fullName='" + fullName + '\'' +
                ", sex=" + sex +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
