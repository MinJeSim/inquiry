package mileage;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="InquiryHst_table")
class InquiryHst {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long memberId;
    private String inquiryStatus;
    private String inquiryContents;

    @PrePersist
    public void onPrePersist(){
        System.out.println("onPre InquiryHst");

        CancelInquiry cancelInquiry = new CancelInquiry();
        BeanUtils.copyProperties(this, cancelInquiry);
        cancelInquiry.publishAfterCommit();

        try {
            System.out.println("Thread Sleep");
            Thread.currentThread().sleep((long) (400 + Math.random() * 230));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PostPersist
    public void onPostPersist(){
        System.out.println("onPost InquiryHst");


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getInquiryStatus() {
        return inquiryStatus;
    }

    public void setInquiryStatus(String inquiryStatus) {
        this.inquiryStatus = inquiryStatus;
    }

    public String getInquiryContents() {
        return inquiryContents;
    }

    public void setInquiryContents(String inquiryContents) {
        this.inquiryContents = inquiryContents;
    }
}
