package mileage;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Inquiry_table")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long memberId;
    private String inquiryStatus;
    private String inquiryContents;

    @PostPersist
    public void onPostPersist() {
        if(this.inquiryStatus.equals("INQUIRING")) {
            ReceiptInquiry receiptInquiry = new ReceiptInquiry();
            BeanUtils.copyProperties(this, receiptInquiry);
            receiptInquiry.setInquiryStatus("RECEIPT");
            receiptInquiry.setInquiryContents("TEST DONE");

            receiptInquiry.publishAfterCommit();
        }
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
