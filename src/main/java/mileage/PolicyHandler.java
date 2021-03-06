package mileage;

import mileage.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler {

    @Autowired
    InquiryRepository inquiryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString) {

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverInquirySent_SendInquiry(@Payload InquirySent inquirySent) {
        if (inquirySent.isMe()) {
            System.out.println("##### listener SendInquiry : " + inquirySent.toJson());
            if (!inquirySent.getInquiryStatus().equals("RECEIPTED")) {
                Optional<Inquiry> inquiryOptional = inquiryRepository.findByMemberId(inquirySent.getMemberId());

                Inquiry inquiry = inquiryOptional.orElseGet(Inquiry::new);

                inquiry.setInquiryStatus("RECEIPTED");
                inquiry.setMemberId(inquirySent.getMemberId());
                inquiry.setInquiryContents(inquirySent.getInquiryContents());

                inquiryRepository.save(inquiry);
            } else {
                Optional<Inquiry> inquiryOptional = inquiryRepository.findByMemberId(inquirySent.getMemberId());

                Inquiry inquiry = inquiryOptional.orElseGet(Inquiry::new);

                inquiry.setInquiryStatus("ALREADY RECEIPTED");
                inquiry.setMemberId(inquirySent.getMemberId());
                inquiry.setInquiryContents(inquirySent.getInquiryContents());

                inquiryRepository.save(inquiry);
            }
        }
    }
}