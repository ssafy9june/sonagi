package com.fa.sonagi.faq.service;

import java.util.List;

import com.fa.sonagi.faq.dto.FAQPostDto;
import com.fa.sonagi.faq.dto.FAQPutDto;
import com.fa.sonagi.faq.dto.FAQResDto;

public interface FAQService {
	List<FAQResDto> findAllFAQ();

	FAQResDto findFAQById(Long FAQId);

	void registFAQ(FAQPostDto faqPostDto);

	void updateFAQ(FAQPutDto faqPutDto);

	void deleteFAQ(Long FAQId);

	List<FAQResDto> findMemberFAQ();

	List<FAQResDto> findOperationFAQ();

	List<FAQResDto> findUseFAQ();

	List<FAQResDto> findEtcFAQ();
}
