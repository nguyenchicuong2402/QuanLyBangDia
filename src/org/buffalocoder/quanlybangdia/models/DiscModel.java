package org.buffalocoder.quanlybangdia.models;

import java.util.Objects;

public class DiscModel {
    private long id;                // id - khoá chính
    private String name;            // tên đĩa
    private String type;            // thể loại
    private boolean status;         // tình trạng
    private String producer;        // nhà sản xuất
    private String note;            // ghi chú

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
     * Get name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Set type
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get status
     * @return
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Set status
     * @param status
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Get producer
     * @return
     */
    public String getProducer() {
        return producer;
    }

    /**
     * Set producer
     * @param producer
     */
    public void setProducer(String producer) {
        this.producer = producer;
    }

    /**
     * Get note
     * @return
     */
    public String getNote() {
        return note;
    }

    /**
     * Set note
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Constructor empty
     */
    public DiscModel() {
    }

    /**
     * Constructor with params
     * @param id
     * @param name
     * @param type
     * @param status
     * @param producer
     * @param note
     */
    public DiscModel(long id, String name, String type, boolean status, String producer, String note) {
        this.setId(id);
        this.setName(name);
        this.setType(type);
        this.setStatus(status);
        this.setProducer(producer);
        this.setNote(note);
    }

    /**
     * Override equals()
     * @param disc
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscModel discModel = (DiscModel) o;
        return id == discModel.id;
    }

    /**
     * Override hashCode()
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Override toString()
     * @return
     */
    @Override
    public String toString() {
        return "DiscModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", producer='" + producer + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
