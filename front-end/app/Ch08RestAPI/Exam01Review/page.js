"use client";

import { useEffect, useState } from "react";
import Link from "next/link";
import { getReviewList } from "@/apis/reviewApi";

export default function Exam01Review() {
  const [reviews, setReviews] = useState([]);
  const [pager, setPager] = useState(null);
  const [pageNo, setPageNo] = useState(1);

  useEffect(() => {
    const loadReviews = async () => {
      try {
        const result = await getReviewList(pageNo);
        setReviews(result.data.data);
        setPager(result.data.pager);
      } catch (error) {
        console.error("리뷰 목록 조회에 실패했습니다.", error);
      }
    };

    loadReviews();
  }, [pageNo]);

  return (
    <section className="m-6 overflow-hidden rounded-lg border border-gray-200 bg-white shadow-sm">
      <header className="border-b border-gray-200 bg-gray-50 px-5 py-3">
        <h1 className="text-lg font-semibold text-gray-900">리뷰 목록</h1>
      </header>
      <div className="p-5">
        <div className="mb-3 flex items-center justify-between">
          <p className="text-sm text-gray-600">
            {pager ? `전체 ${pager.totalRows}개 리뷰` : "리뷰 목록"}
          </p>
          <Link
            href="/Ch08RestAPI/Exam01Review/ReviewWrite"
            className="rounded bg-orange-600 px-4 py-2 text-sm font-medium text-white hover:bg-orange-700"
          >
            글쓰기
          </Link>
        </div>
        <div className="overflow-x-auto">
          <table className="w-full min-w-[900px] table-auto border-collapse text-left text-sm">
            <thead>
              <tr className="border-b border-orange-100 bg-orange-50 text-center text-orange-900">
                <th className="w-px whitespace-nowrap px-3 py-2">번호</th>
                <th className="px-3 py-2">제목</th>
                <th className="w-px whitespace-nowrap px-3 py-2">작성자</th>
                <th className="w-px whitespace-nowrap px-3 py-2">작성일</th>
                <th className="w-px whitespace-nowrap px-3 py-2">상품 번호</th>
                <th className="w-px whitespace-nowrap px-3 py-2">첨부 파일</th>
              </tr>
            </thead>
            <tbody className="text-center">
              {reviews.map((review) => (
                <tr key={review.reviewId} className="border-b border-gray-100 transition-colors hover:bg-gray-50">
                  <td className="w-px whitespace-nowrap px-3 py-2">{review.reviewId}</td>
                  <td className="px-3 py-2 text-left">
                    <Link
                      href={`/Ch08RestAPI/Exam01Review/ReviewDetail?reviewId=${review.reviewId}`}
                      className="block w-full truncate rounded px-2 py-1 text-orange-700"
                      title={review.title}
                    >
                      {review.title}
                    </Link>
                  </td>
                  <td className="w-px whitespace-nowrap px-3 py-2">{review.writer}</td>
                  <td className="w-px whitespace-nowrap px-3 py-2">
                    {review.writtenAt
                      ? new Date(review.writtenAt).toLocaleDateString("ko-KR")
                      : "-"}
                  </td>
                  <td className="w-px whitespace-nowrap px-3 py-2">{review.productId}</td>
                  <td className="w-px whitespace-nowrap px-3 py-2">
                    {review.fileName ? (
                      <span title={review.fileName} aria-label={`첨부 파일: ${review.fileName}`} className="inline-flex text-orange-600">
                        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.7" className="h-5 w-5" aria-hidden="true">
                          <path strokeLinecap="round" strokeLinejoin="round" d="M13 3H6.75A1.75 1.75 0 0 0 5 4.75v14.5A1.75 1.75 0 0 0 6.75 21h10.5A1.75 1.75 0 0 0 19 19.25V9l-6-6Z" />
                          <path strokeLinecap="round" strokeLinejoin="round" d="M13 3v6h6M8 13h8M8 16.5h8" />
                        </svg>
                      </span>
                    ) : "-"}
                  </td>
                </tr>
              ))}
              {reviews.length === 0 && (
                <tr>
                  <td colSpan={6} className="px-3 py-8 text-center text-gray-500">
                    등록된 리뷰가 없습니다.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
        {pager && pager.totalPageNo > 0 && (
          <nav
            aria-label="리뷰 페이지 이동"
            className="mt-5 flex flex-wrap items-center justify-center gap-1"
          >
            <button
              type="button"
              onClick={() => setPageNo(1)}
              disabled={pager.pageNo === 1}
              aria-label="첫 페이지"
              title="첫 페이지"
              className="cursor-pointer rounded border px-3 py-1.5 text-sm disabled:cursor-not-allowed disabled:opacity-40"
            >
              처음
            </button>
            <button
              type="button"
              onClick={() => setPageNo(Math.max(1, pager.startPageNo - 1))}
              disabled={pager.startPageNo === 1}
              aria-label="이전 페이지 그룹"
              title="이전 페이지 그룹"
              className="cursor-pointer rounded border px-3 py-1.5 text-sm disabled:cursor-not-allowed disabled:opacity-40"
            >
              이전
            </button>
            {pager.pageArray.map((number) => (
              <button
                key={number}
                type="button"
                onClick={() => setPageNo(number)}
                aria-current={pager.pageNo === number ? "page" : undefined}
                className={`rounded border px-3 py-1.5 text-sm ${
                  pager.pageNo === number
                    ? "border-orange-600 bg-orange-600 text-white"
                    : "cursor-pointer hover:bg-gray-100"
                }`}
              >
                {number}
              </button>
            ))}
            <button
              type="button"
              onClick={() => setPageNo(Math.min(pager.totalPageNo, pager.endPageNo + 1))}
              disabled={pager.endPageNo === pager.totalPageNo}
              aria-label="다음 페이지 그룹"
              title="다음 페이지 그룹"
              className="cursor-pointer rounded border px-3 py-1.5 text-sm disabled:cursor-not-allowed disabled:opacity-40"
            >
              다음
            </button>
            <button
              type="button"
              onClick={() => setPageNo(pager.totalPageNo)}
              disabled={pager.pageNo === pager.totalPageNo}
              aria-label="마지막 페이지"
              title="마지막 페이지"
              className="cursor-pointer rounded border px-3 py-1.5 text-sm disabled:cursor-not-allowed disabled:opacity-40"
            >
              마지막
            </button>
          </nav>
        )}
      </div>
    </section>
  );
}
