package com.mycompany.demo.database.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.demo.database.dto.ReviewWriteRequest;
import com.mycompany.demo.database.dto.ReviewWriteResponse;
import com.mycompany.demo.database.dto.ReviewListItemResponse;
import com.mycompany.demo.database.dto.Pager;
import com.mycompany.demo.database.entity.Review;
import com.mycompany.demo.database.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/review")
@Slf4j 
@RequiredArgsConstructor 
public class ReviewController {
    
    private final ReviewService reviewService;

    @PostMapping(value = "/write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ReviewWriteResponse write(@ModelAttribute ReviewWriteRequest reviewWriteRequest) throws IOException {
        MultipartFile file = reviewWriteRequest.getFile();
        /*
        log.info(
                "Review write request: title={}, content={}, writer={}, fileName={}, fileType={}, fileSize={}",
                reviewWriteRequest.getTitle(),
                reviewWriteRequest.getContent(),
                reviewWriteRequest.getWriter(),
                file != null ? file.getOriginalFilename() : null,
                file != null ? file.getContentType() : null,
                file != null ? file.getSize() : 0
        );
        */

        Review review = new Review();
        review.setTitle(reviewWriteRequest.getTitle());
        review.setContent(reviewWriteRequest.getContent());
        if (reviewWriteRequest.getFile() != null && !reviewWriteRequest.getFile().isEmpty()) {
            review.setFileName(reviewWriteRequest.getFile().getOriginalFilename());
            review.setFileType(reviewWriteRequest.getFile().getContentType());
            review.setFileData(reviewWriteRequest.getFile().getBytes());
        }
        review.setWriter(reviewWriteRequest.getWriter());
        review.setProductId(reviewWriteRequest.getProductId());        

        // productId가 DTO에 바인딩되지 않아 우선 매핑 보류


        reviewService.write(review);
        // Review dbReview = reviewService.write(review);

        // ReviewListItemResponse response = new ReviewListItemResponse();
        // response.setReviewId(dbReview.getReviewId());
        // response.setTitle(dbReview.getTitle());
        // response.setFileName(dbReview.getFileName());
        // response.setWriter(dbReview.getWriter());
        // response.setWrittenAt(dbReview.getWrittenAt());
        // response.setProductId(dbReview.getProductId());

        return new ReviewWriteResponse("success", "Review written successfully");
    }

    @GetMapping("/list")
    public Map<String, Object> listReview(@RequestParam("pageNo") int pageNo) {

        // String name = null;
        // name.length();

        Map<String, Object> where = null;
        int totalRows = reviewService.pagingTotalRows(where);
        Pager pager = new Pager(10, 5, totalRows, pageNo);
        List<Review> list = reviewService.listReview(pager, where);
        List<ReviewListItemResponse> listReviewResponse = new ArrayList<>();

        for(Review dbReview : list) {
            ReviewListItemResponse reviewResponse = new ReviewListItemResponse();
            reviewResponse.setReviewId(dbReview.getReviewId());
            reviewResponse.setTitle(dbReview.getTitle());
            reviewResponse.setFileName(dbReview.getFileName());
            reviewResponse.setWriter(dbReview.getWriter());
            reviewResponse.setWrittenAt(dbReview.getWrittenAt());
            reviewResponse.setProductId(dbReview.getProductId());
            listReviewResponse.add(reviewResponse);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("pager", pager);
        map.put("data", listReviewResponse);

        return map;
    }

    

}
