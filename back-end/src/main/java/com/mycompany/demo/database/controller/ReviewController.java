package com.mycompany.demo.database.controller;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.mycompany.demo.database.dto.Pager;
import com.mycompany.demo.database.dto.ReviewDetailResponse;
import com.mycompany.demo.database.dto.ReviewListItemResponse;
import com.mycompany.demo.database.dto.ReviewUpdateRequest;
import com.mycompany.demo.database.dto.ReviewUpdateResponse;
import com.mycompany.demo.database.dto.ReviewWriteRequest;
import com.mycompany.demo.database.dto.ReviewWriteResponse;
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
        // productId mapping is pending because the DTO does not bind it.


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

    @GetMapping("/detail")
    public ReviewDetailResponse detail(@RequestParam("reviewId") long reviewId) {
        Review dbReview = reviewService.getReview(reviewId);
        if (dbReview == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "?�기�?찾을 ???�습?�다.");
        }

        ReviewDetailResponse response = new ReviewDetailResponse();
        response.setReviewId(dbReview.getReviewId());
        response.setTitle(dbReview.getTitle());
        response.setContent(dbReview.getContent());
        response.setWriter(dbReview.getWriter());
        response.setProductId(dbReview.getProductId());
        response.setFileName(dbReview.getFileName());
        response.setFileType(dbReview.getFileType());
        response.setWrittenAt(dbReview.getWrittenAt());

        return response;
    }

    @GetMapping("/file")
    public ResponseEntity<byte[]> file(@RequestParam("reviewId") long reviewId) {
        Review review = reviewService.getReview(reviewId);
        if (review == null || review.getFileData() == null) {
            return ResponseEntity.notFound().build();
        }
        //응답 본문에 있는 데이터의 타입을 설정(MediaType)
        String type = review.getFileType();  // image/jpeg, image/png, ... (MIME Type)
        MediaType mediaType = MediaType.parseMediaType(type);


        // 브라우저가 다운로드할 파일 이름을 설정
        String fileName = review.getFileName();
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+", "%20");
        String disposition = "attachment; filename*=UTF-8''" + encodedFileName;

        // 200 응답
        return ResponseEntity.ok()
                .contentType(mediaType) // Content-Type: image/jpeg
                .header(HttpHeaders.CONTENT_DISPOSITION, disposition) //Content-Disposition: attachment; filename*=UTF-8''encodedFileName
                //.header("Content-Type", type)
                .body(review.getFileData());
    }

    @PutMapping("/update")
    public ReviewUpdateResponse update(@ModelAttribute ReviewUpdateRequest reviewUpdateRequest) throws IOException {
        MultipartFile file = reviewUpdateRequest.getFile();

        Review review = new Review();
        review.setReviewId(reviewUpdateRequest.getReviewId());
        review.setTitle(reviewUpdateRequest.getTitle());
        review.setContent(reviewUpdateRequest.getContent());

        if (reviewUpdateRequest.isDeleteFile()) {
            review.setDeleteFile(true);
        }

        if (reviewUpdateRequest.getFile() != null && !reviewUpdateRequest.getFile().isEmpty()) {
            review.setFileName(reviewUpdateRequest.getFile().getOriginalFilename());
            review.setFileType(reviewUpdateRequest.getFile().getContentType());
            review.setFileData(reviewUpdateRequest.getFile().getBytes());
        }

        reviewService.update(review);

        return new ReviewUpdateResponse("success", "Review updated successfully");
    }

}
