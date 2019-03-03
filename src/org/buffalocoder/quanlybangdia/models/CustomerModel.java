package org.buffalocoder.quanlybangdia.models;

import java.sql.Date;
import java.util.Objects;

public class CustomerModel extends PersonModel{
    private long id;                    // mã thẻ thành viên - khoá chính
    private Date expirationDate;        // ngày hết hạn

    /**
     * Get ID
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Set ID
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get Expiration Date
     * @return
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Set ExpirationDate
     * @param expirationDate
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Constructor Parent
     * @param id
     * @param fullName
     * @param sex
     * @param phone
     * @param address
     * @param birthday
     */
    public CustomerModel(long id, String fullName, boolean sex, String phone, String address, Date birthday) {
        super(id, fullName, sex, phone, address, birthday);
    }

    /**
     * Constructor with params
     * @param id
     * @param fullName
     * @param sex
     * @param phone
     * @param address
     * @param birthday
     * @param id1
     * @param expirationDate
     */
    public CustomerModel(long id_card, String fullName, boolean sex, String phone, String address, Date birthday, long id, Date expirationDate) {
        super(id_card, fullName, sex, phone, address, birthday);
        this.setId(id);
        this.setExpirationDate(expirationDate);
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
        if (!super.equals(o)) return false;
        CustomerModel that = (CustomerModel) o;
        return id == that.id;
    }

    /**
     * Override hashCode()
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    /**
     * Override toString
     * @return
     */
    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", expirationDate=" + expirationDate +
                "} " + super.toString();
    }
}
