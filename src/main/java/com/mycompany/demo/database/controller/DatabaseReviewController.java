package com.mycompany.demo.database.controller;

import com.mycompany.demo.database.dao.ReviewDao;
import com.mycompany.demo.database.dto.Pager;
import com.mycompany.demo.database.entity.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/database/review")
@RequiredArgsConstructor
@Slf4j
public class DatabaseReviewController {
    //reviewDao 주입
    private final ReviewDao reviewDao;

    // DTO 객체로 감싸지 않은 상태로 전개
    @GetMapping("/get-review")
    public Review getReview(@RequestParam("reviewId") long reviewId) {
        return reviewDao.selectById(reviewId);
    }

    @PostMapping("/write-review")
    public Review writeReview(@RequestBody Review review) {
        reviewDao.insert(review);
        return reviewDao.selectById(review.getReviewId());
    }

    @PutMapping("update-review")
    public Review updateReview(@RequestBody Review review) {
        // DB에서 가져오기
        Review postReview = reviewDao.selectById(review.getReviewId());
        postReview.setTitle(review.getTitle());
        postReview.setContent(review.getContent());

        reviewDao.update(postReview);
        return reviewDao.selectById(postReview.getReviewId());
    }

    @DeleteMapping("delete-review")
    public String deleteReview(@RequestParam("reviewId") long reviewId) {
        int rows = reviewDao.deleteById(reviewId);
        if(rows > 0) {
            return "reviewId: " + reviewId + "가 삭제 되었습니다. 삭제된 행 수: " + rows;
        } else {
            return "reviewId: " + reviewId + "가 존재하지 않습니다.";
        }
    }

    @GetMapping("list-review")
    public Map<String, Object> listReview(@RequestParam("pageNo") int pageNo) {
        // 예제는 content 찾기로 해보기로 한다
        Map<String, Object> where = Map.of("content", "product10");

        int totalRows = reviewDao.selectCount(where);
        Pager pager = new Pager(10, 10, totalRows, pageNo);
        List<Review> list = reviewDao.selectPage(pager, where);

        return Map.of(
                "pager", pager,
                "data", list
        );
    }
}
