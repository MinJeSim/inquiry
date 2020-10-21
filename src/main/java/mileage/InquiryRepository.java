package mileage;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface InquiryRepository extends PagingAndSortingRepository<Inquiry, Long>{
    Optional<Inquiry> findByMemberId(Long memberId);
}