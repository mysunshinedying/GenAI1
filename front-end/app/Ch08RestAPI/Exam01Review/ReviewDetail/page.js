"use client";

import { getReviewFileUrl, reviewDetail } from "@/apis/reviewApi";
import Image from "next/image";
import Link from "next/link";
import { useSearchParams } from "next/navigation";
import { useEffect, useState } from "react";

export default function ReviewDetail() {
    const searchParams = useSearchParams();
    const reviewId = searchParams.get("reviewId");
    const [review, setReview] = useState({
        reviewId: "",
        title: "",
        content: "",
        writer: "",
        productId: "",
        fileName: "",
        fileType: "",
        writedAt: "",
    });

    useEffect(() => {
        const work = async () => {
            try {
                console.log("work() 실행");
                const response = await reviewDetail(reviewId);
                setReview(response.data);
            } catch (error) {
                console.log(error);
            }
        };
        work();
    }, [reviewId]);

    return (
        <section className="min-w-0 w-full rounded-lg border border-zinc-200 bg-white text-zinc-900 shadow-sm dark:border-zinc-700 dark:bg-zinc-900 dark:text-zinc-100">
            <header className="rounded-t-lg border-b border-zinc-200 bg-zinc-100 px-4 py-1 dark:border-zinc-700 dark:bg-zinc-800">
                <h2 className="font-semibold">ReviewDetail</h2>
            </header>
            <div className="bg-zinc-50/60 p-3 sm:p-4 dark:bg-zinc-950/30">
                <article className="mx-auto max-w-[800px] overflow-hidden rounded-2xl border border-zinc-200 bg-white shadow-sm dark:border-zinc-700 dark:bg-zinc-900">
                    <div className="border-b border-zinc-100 p-4 sm:p-5 dark:border-zinc-800">
                        <div className="mb-2 flex flex-wrap items-center gap-2">
                            <span className="rounded-full bg-orange-50 px-3 py-1 text-xs font-semibold text-orange-700 dark:bg-orange-950/50 dark:text-orange-300">상품 후기</span>
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
                                <dd className="mt-1 font-medium">{review.writedAt ? new Date(review.writedAt).toLocaleString("ko-KR") : "-"}</dd>
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
                        <div className="mt-3 flex items-center gap-3 rounded-xl border border-zinc-200 bg-zinc-50 px-3 py-2 dark:border-zinc-700 dark:bg-zinc-800/40">
                            <span aria-hidden="true" className="flex h-8 w-8 shrink-0 items-center justify-center rounded-lg bg-white text-xl dark:bg-zinc-800">📎</span>
                            <div className="min-w-0">
                                <h4 className="text-xs font-medium text-zinc-500 dark:text-zinc-400">첨부파일</h4>
                                {review.fileName ? (
                                    <a href={getReviewFileUrl(review.reviewId)} download={review.fileName} className="mt-1 block break-all text-sm text-orange-600 underline dark:text-orange-400">
                                        {review.fileName} 다운로드
                                    </a>
                                ) : (
                                    <p className="mt-1 break-all text-sm">첨부파일이 없습니다.</p>
                                )}
                            </div>
                            {review.fileName && review.fileType?.startsWith("image/") && (
                                <Image
                                    src={getReviewFileUrl(review.reviewId) + `&fileName=${review.fileName}`}
                                    alt={review.fileName}
                                    width={80}
                                    height={80}
                                    unoptimized
                                    className="ml-auto h-20 w-20 shrink-0 rounded-lg border border-zinc-200 bg-white object-contain dark:border-zinc-700"
                                />
                            )}
                        </div>
                    </div>
                    <div className="flex flex-wrap items-center justify-between gap-3 border-t border-zinc-100 px-4 py-3 sm:px-5 dark:border-zinc-800">
                        <div className="flex gap-2">
                            <Link href={`/Ch08RestAPI/Exam01Review/ReviewUpdate?reviewId=${review.reviewId}`} className="rounded-lg border border-zinc-300 px-4 py-2 text-xs font-semibold transition hover:bg-zinc-100 dark:border-zinc-600 dark:hover:bg-zinc-800">
                                수정하기
                            </Link>
                            <button type="button" className="rounded-lg border border-red-200 px-4 py-2 text-xs font-semibold text-red-600 transition hover:bg-red-50 dark:border-red-900 dark:text-red-400 dark:hover:bg-red-950/40 cursor-pointer">
                                삭제하기
                            </button>
                        </div>
                        <Link href="/Ch08RestAPI/Exam01Review" className="rounded-lg bg-orange-500 px-4 py-2 text-xs font-semibold text-white transition hover:bg-orange-600 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-orange-500">
                            목록으로
                        </Link>
                    </div>
                </article>
            </div>
        </section>
    );
}
