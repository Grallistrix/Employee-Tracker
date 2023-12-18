package com.example.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "rating")
public class Rate {

    @Id
    @Column(name="Rate_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Rate_ID;

    private int value;
    private int which_group;
    @Temporal(TemporalType.DATE)
    private Date date;
    private String comment;



    public Rate(){}


    public int getRate_ID() {
        return this.Rate_ID;
    }

    public void setRate_ID(int ID) {
        this.Rate_ID = ID;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWhich_group() {
        return this.which_group;
    }

    public void setWhich_group(int which_group) {
        this.which_group = which_group;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(long l) {
        Date newDate = new Date(l);
        this.date = newDate;
    }
        public void setDate(Date newDate) {
        this.date = newDate;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    

    @Override
    public String toString() {
        String returnal = "{" +
            " ID='" + getRate_ID() + "'" +
            ", value='" + getValue() + "'" +
            ", which_group='" + getWhich_group() + "'" +
            ", date='" + getDate() + "'";
            if(comment!=null){
                returnal+= ", comment='" + getComment() + "'" + "}";
            }else{
                returnal += ",BRAK KOMENTARZA}";
            }

        return  returnal;
    }


}
