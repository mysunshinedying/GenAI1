"use client";

import Link from "next/link";
import {useSearchParams} from "next/navigation";
import {useEffect, useState} from "react";
import {getReviewFileUrl, reviewDetail} from "@/apis/reviewApi";

export default function ReviewDetail() {
    const searchParams = useSearchParams();
    const reviewId = searchParams.get("reviewId");
    const [isLoading, setIsLoading] = useState(true);
    const [loadError, setLoadError] = useState(false);
    const [review, setReview] = useState({
        reviewId,
        title: "",
        content: "",
        writer: "",
        productId: 0,
        fileName: "",
        fileType: "",
        writtenAt: "",
    });

    useEffect(() => {
        let isActive = true;

        const work = async () => {
            try {
                setIsLoading(true);
                setLoadError(false);
                const response = await reviewDetail(reviewId);
                if (isActive) {
                    setReview(response.data);
                }
            } catch (error) {
                console.error("게시물 확인에 실패했습니다.", error);
                if (isActive) {
                    setLoadError(true);
                }
            } finally {
                if (isActive) {
                    setIsLoading(false);
                }
            }
        };

        work();
        return () => {
            isActive = false;
        };
    }, [reviewId]);

    if (isLoading) {
        return <p className="p-6 text-sm text-zinc-500">후기 정보를 불러오는 중입니다.</p>;
    }

    if (!reviewId) {
        return <p className="p-6 text-sm text-zinc-500">후기 번호가 없습니다.</p>;
    }

    if (loadError) {
        return <p className="p-6 text-sm text-red-600">후기 정보를 불러오지 못했습니다.</p>;
    }

    return (
        <section
            className="min-w-0 w-full rounded-lg border border-zinc-200 bg-white text-zinc-900 shadow-sm dark:border-zinc-700 dark:bg-zinc-900 dark:text-zinc-100">
            <header
                className="rounded-t-lg border-b border-zinc-200 bg-zinc-100 px-4 py-1 dark:border-zinc-700 dark:bg-zinc-800">
                <h2 className="font-semibold">ReviewDetail</h2>
            </header>
            <div className="bg-zinc-50/60 p-3 sm:p-4 dark:bg-zinc-950/30">
                <article
                    className="mx-auto max-w-[800px] overflow-hidden rounded-2xl border border-zinc-200 bg-white shadow-sm dark:border-zinc-700 dark:bg-zinc-900">
                    <div className="border-b border-zinc-100 p-4 sm:p-5 dark:border-zinc-800">
                        <div className="mb-2 flex flex-wrap items-center gap-2">
                            <span
                                className="rounded-full bg-orange-50 px-3 py-1 text-xs font-semibold text-orange-700 dark:bg-orange-950/50 dark:text-orange-300">상품 후기</span>
                            <span className="text-xs text-zinc-400">후기 번호 #{review.reviewId || "-"}</span>
                        </div>
                        <h3 className="break-words text-xl font-bold leading-snug tracking-tight">{review.title || "후기 제목"}</h3>
                        <dl className="mt-3 grid grid-cols-1 gap-3 rounded-xl bg-zinc-50 p-3 text-sm sm:grid-cols-3 dark:bg-zinc-800/50">
                            <div>
                                <dt className="text-xs text-zinc-500 dark:text-zinc-400">글쓴이</dt>
                                <dd className="mt-1 break-all font-medium">{review.writer || "-"}</dd>
                            </div>
                            <div>
                                <dt className="text-xs text-zinc-500 dark:text-zinc-400">작성일</dt>
                                <dd className="mt-1 font-medium">{review.writtenAt ? new Date(review.writtenAt).toLocaleString("ko-KR") : "-"}</dd>
                            </div>
                            <div>
                                <dt className="text-xs text-zinc-500 dark:text-zinc-400">상품 번호</dt>
                                <dd className="mt-1 font-medium">{review.productId ?? "-"}</dd>
                            </div>
                        </dl>
                    </div>
                    <div className="p-4 sm:p-5">
                        <h4 className="mb-2 text-sm font-semibold text-zinc-500 dark:text-zinc-400">사용 후기</h4>
                        <p className="min-h-20 whitespace-pre-wrap break-words text-sm leading-6 text-zinc-700 dark:text-zinc-200">
                            {review.content || "후기 내용을 불러오면 이곳에 표시됩니다."}
                        </p>
                        <div
                            className="mt-3 flex items-center justify-between gap-3 rounded-xl border border-zinc-200 bg-zinc-50 px-3 py-2 dark:border-zinc-700 dark:bg-zinc-800/40">
                            <div className="flex min-w-0 flex-1 items-center gap-3">
              <span aria-hidden="true"
                    className="flex h-8 w-8 shrink-0 items-center justify-center rounded-lg bg-white text-orange-600 dark:bg-zinc-800 dark:text-orange-400">
                <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.7" className="h-5 w-5"
                     aria-hidden="true">
                  <path strokeLinecap="round" strokeLinejoin="round"
                        d="M13 3H6.75A1.75 1.75 0 0 0 5 4.75v14.5A1.75 1.75 0 0 0 6.75 21h10.5A1.75 1.75 0 0 0 19 19.25V9l-6-6Z"/>
                  <path strokeLinecap="round" strokeLinejoin="round" d="M13 3v6h6M8 13h8M8 16.5h8"/>
                </svg>
              </span>
                            <div className="min-w-0">
                                <h4 className="text-xs font-medium text-zinc-500 dark:text-zinc-400">첨부파일</h4>
                                <p className="mt-1 break-all text-sm">{review.fileName || "첨부파일이 없습니다."}</p>
                            </div>
                            </div>
                            {review.fileName && review.fileType?.startsWith("image/") && (
                                <img
                                    src={getReviewFileUrl(review.reviewId)}
                                    alt={review.fileName}
                                    className="h-12 w-12 shrink-0 self-center rounded-md border border-zinc-200 object-cover dark:border-zinc-700"
                                />
                            )}
                        </div>
                    </div>
                    <div className="flex items-center justify-between border-t border-zinc-100 px-4 py-3 sm:px-5 dark:border-zinc-800">
                        <div className="flex items-center gap-2">
                        <Link href={`/Ch08RestAPI/Exam01Review/ReviewUpdate?reviewId=${review.reviewId}`}
                              className="cursor-pointer rounded-lg border border-zinc-300 px-4 py-2 text-xs font-semibold text-zinc-700 transition hover:bg-zinc-100 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-zinc-400 dark:border-zinc-600 dark:text-zinc-200 dark:hover:bg-zinc-800">
                            수정
                        </Link>
                        <button type="button"
                                className="cursor-pointer rounded-lg border border-red-200 px-4 py-2 text-xs font-semibold text-red-600 transition hover:bg-red-50 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-red-400 dark:border-red-900 dark:text-red-400 dark:hover:bg-red-950/40">
                            삭제
                        </button>
                        </div>
                        <Link href="/Ch08RestAPI/Exam01Review"
                              className="rounded-lg bg-orange-500 px-4 py-2 text-xs font-semibold text-white transition hover:bg-orange-600 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-orange-500">
                            목록으로
                        </Link>
                    </div>
                </article>
            </div>
        </section>
    );
}
