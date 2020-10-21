package mileage;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Inquiry_table")
public class Inquiry {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long memberId;
    private String inquiryStatus;
    private Long memberId;
    private Long inquiryId;
    private String inquiryContents;

    @PostPersist
    public void onPostPersist(){
        ReceiptInquiry receiptInquiry = new ReceiptInquiry();
        BeanUtils.copyProperties(this, receiptInquiry);
        receiptInquiry.publishAfterCommit();


        CancelInquiry cancelInquiry = new CancelInquiry();
        BeanUtils.copyProperties(this, cancelInquiry);
        cancelInquiry.publishAfterCommit();


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
    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
    public Long getInquiryId() {
        return inquiryId;
    }

    public void setInquiryId(Long inquiryId) {
        this.inquiryId = inquiryId;
    }
    public String getInquiryContents() {
        return inquiryContents;
    }

    public void setInquiryContents(String inquiryContents) {
        this.inquiryContents = inquiryContents;
    }




}
