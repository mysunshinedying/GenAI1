"use client";

import Link from "next/link";
import { useRouter, useSearchParams } from "next/navigation";
import { useEffect, useRef, useState } from "react";
import { reviewDetail, reviewUpdate } from "@/apis/reviewApi";

export default function ReviewUpdate() {
  const router = useRouter();
  const searchParams = useSearchParams();
  const reviewId = searchParams.get("reviewId");
  const [review, setReview] = useState({
    reviewId: "",
    title: "",
    content: "",
    writer: "",
    productId: "",
    fileName: "",
  });
  const [isLoading, setIsLoading] = useState(true);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const [isCurrentFileRemoved, setIsCurrentFileRemoved] = useState(false);
  const [selectedFile, setSelectedFile] = useState(null);
  const fileInputRef = useRef(null);

  useEffect(() => {
    let isActive = true;

    if (!reviewId) {
      setIsLoading(false);
      setErrorMessage("후기 번호가 없습니다.");
      return () => {
        isActive = false;
      };
    }

    const loadReview = async () => {
      try {
        const response = await reviewDetail(reviewId);
        if (isActive) {
          setReview(response.data);
        }
      } catch (error) {
        console.error("후기 정보를 불러오지 못했습니다.", error);
        if (isActive) {
          setErrorMessage("후기 정보를 불러오지 못했습니다.");
        }
      } finally {
        if (isActive) {
          setIsLoading(false);
        }
      }
    };

    loadReview();
    return () => {
      isActive = false;
    };
  }, [reviewId]);

  const handleChange = (event) => {
    const { name, value } = event.target;
    setReview((previousReview) => ({ ...previousReview, [name]: value }));
  };

  const handleFileChange = (event) => {
    setSelectedFile(event.target.files?.[0] ?? null);
  };

  const clearSelectedFile = () => {
    setSelectedFile(null);
    if (fileInputRef.current) {
      fileInputRef.current.value = "";
    }
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setErrorMessage("");

    if (!review.title.trim()) {
      setErrorMessage("제목을 입력해 주세요.");
      return;
    }
    if (!review.content.trim()) {
      setErrorMessage("내용을 입력해 주세요.");
      return;
    }

    try {
      setIsSubmitting(true);
      await reviewUpdate({
        reviewId: review.reviewId,
        title: review.title,
        content: review.content,
      });
      router.push(`/Ch08RestAPI/Exam01Review/ReviewDetail?reviewId=${review.reviewId}`);
    } catch (error) {
      console.error("후기 수정에 실패했습니다.", error);
      setErrorMessage("후기 수정에 실패했습니다. 잠시 후 다시 시도해 주세요.");
      setIsSubmitting(false);
    }
  };

  if (isLoading) {
    return <p className="p-6 text-sm text-zinc-500">후기 정보를 불러오는 중입니다.</p>;
  }

  if (errorMessage && !review.reviewId) {
    return <p className="p-6 text-sm text-red-600">{errorMessage}</p>;
  }

  return (
    <section className="min-w-0 w-full rounded-lg border border-zinc-200 bg-white text-zinc-900 shadow-sm dark:border-zinc-700 dark:bg-zinc-900 dark:text-zinc-100">
      <header className="rounded-t-lg border-b border-zinc-200 bg-zinc-100 px-4 py-1 dark:border-zinc-700 dark:bg-zinc-800">
        <h2 className="font-semibold">ReviewUpdate</h2>
      </header>
      <div className="bg-zinc-50/60 p-4 sm:p-8 dark:bg-zinc-950/30">
        <div className="mx-auto max-w-[800px]">
          <div className="mb-6 border-l-4 border-orange-500 pl-4">
            <p className="text-xs font-semibold tracking-widest text-orange-600 dark:text-orange-400">REVIEW</p>
            <h3 className="mt-1 text-2xl font-bold tracking-tight">상품 후기 수정</h3>
            <p className="mt-2 text-sm leading-6 text-zinc-500 dark:text-zinc-400">작성한 후기 내용을 수정해 주세요.</p>
          </div>

          <form onSubmit={handleSubmit} className="grid grid-cols-1 gap-4 rounded-2xl border border-zinc-200 bg-white p-5 shadow-sm sm:grid-cols-2 dark:border-zinc-700 dark:bg-zinc-900">
            <div className="sm:col-span-2">
              <label htmlFor="title" className="mb-1 block text-sm font-semibold">제목</label>
              <input
                id="title"
                name="title"
                type="text"
                value={review.title}
                onChange={handleChange}
                maxLength={200}
                required
                className="w-full rounded-lg border border-zinc-200 bg-zinc-50 px-3 py-2 text-sm outline-none transition focus:border-orange-400 focus:bg-white focus:ring-2 focus:ring-orange-100 dark:border-zinc-600 dark:bg-zinc-800 dark:focus:bg-zinc-800 dark:focus:ring-orange-900/40"
              />
            </div>

            <div className="sm:col-span-2">
              <label htmlFor="content" className="mb-1 block text-sm font-semibold">내용</label>
              <textarea
                id="content"
                name="content"
                value={review.content}
                onChange={handleChange}
                rows={8}
                required
                className="w-full rounded-lg border border-zinc-200 bg-zinc-50 px-3 py-2 text-sm leading-6 outline-none transition focus:border-orange-400 focus:bg-white focus:ring-2 focus:ring-orange-100 dark:border-zinc-600 dark:bg-zinc-800 dark:focus:bg-zinc-800 dark:focus:ring-orange-900/40"
              />
            </div>

            <div>
              <label htmlFor="writer" className="mb-1 block text-sm font-semibold">글쓴이</label>
              <input id="writer" value={review.writer || "-"} readOnly className="w-full rounded-lg border border-zinc-200 bg-zinc-100 px-3 py-2 text-sm text-zinc-500 dark:border-zinc-700 dark:bg-zinc-800 dark:text-zinc-400" />
            </div>
            <div>
              <label htmlFor="productId" className="mb-1 block text-sm font-semibold">상품 번호</label>
              <input id="productId" value={review.productId ?? "-"} readOnly className="w-full rounded-lg border border-zinc-200 bg-zinc-100 px-3 py-2 text-sm text-zinc-500 dark:border-zinc-700 dark:bg-zinc-800 dark:text-zinc-400" />
            </div>

            <div className="sm:col-span-2 rounded-xl border border-dashed border-zinc-300 bg-zinc-50 p-4 dark:border-zinc-600 dark:bg-zinc-800/40">
              <div className="flex flex-wrap items-center justify-between gap-3">
                <div>
                  <p className="text-sm font-semibold">첨부파일 관리</p>
                  <p className="mt-1 text-xs text-zinc-500 dark:text-zinc-400">기존 파일을 삭제하거나 새 파일로 바꿀 수 있습니다.</p>
                </div>
                {review.fileName && !isCurrentFileRemoved && (
                  <button
                    type="button"
                    onClick={() => setIsCurrentFileRemoved(true)}
                    className="cursor-pointer rounded-lg border border-red-200 px-3 py-1.5 text-xs font-medium text-red-600 transition hover:bg-red-50 dark:border-red-900 dark:text-red-400 dark:hover:bg-red-950/40"
                  >
                    기존 파일 삭제
                  </button>
                )}
                {review.fileName && isCurrentFileRemoved && (
                  <button
                    type="button"
                    onClick={() => setIsCurrentFileRemoved(false)}
                    className="cursor-pointer rounded-lg border border-zinc-300 px-3 py-1.5 text-xs font-medium transition hover:bg-zinc-100 dark:border-zinc-600 dark:hover:bg-zinc-800"
                  >
                    삭제 취소
                  </button>
                )}
              </div>

              <div className="mt-3 rounded-lg border border-zinc-200 bg-white px-3 py-2 dark:border-zinc-700 dark:bg-zinc-900">
                <p className="text-xs font-medium text-zinc-500 dark:text-zinc-400">현재 파일</p>
                <p className={`mt-1 break-all text-sm ${isCurrentFileRemoved ? "text-red-500 line-through" : ""}`}>
                  {review.fileName
                    ? isCurrentFileRemoved ? `${review.fileName} (삭제 예정)` : review.fileName
                    : "첨부파일이 없습니다."}
                </p>
              </div>

              <div className="mt-3">
                <label htmlFor="replacementFile" className="mb-1 block text-xs font-medium text-zinc-600 dark:text-zinc-300">새 파일 선택</label>
                <input
                  ref={fileInputRef}
                  id="replacementFile"
                  type="file"
                  onChange={handleFileChange}
                  className="block w-full text-sm file:mr-3 file:cursor-pointer file:rounded-lg file:border-0 file:bg-white file:px-4 file:py-2 file:font-medium file:text-orange-600 dark:file:bg-zinc-800 dark:file:text-zinc-100"
                />
                {selectedFile && (
                  <div className="mt-2 flex flex-wrap items-center justify-between gap-2 rounded-lg bg-orange-50 px-3 py-2 text-sm dark:bg-orange-950/30">
                    <span className="break-all text-orange-800 dark:text-orange-200">선택한 파일: {selectedFile.name}</span>
                    <button type="button" onClick={clearSelectedFile} className="cursor-pointer text-xs font-medium text-zinc-600 underline dark:text-zinc-300">
                      선택 취소
                    </button>
                  </div>
                )}
              </div>
            </div>

            {errorMessage && <p role="alert" className="text-sm text-red-600 dark:text-red-400 sm:col-span-2">{errorMessage}</p>}

            <div className="flex flex-wrap items-center justify-end gap-3 border-t border-zinc-100 pt-3 sm:col-span-2 dark:border-zinc-800">
              <Link href={`/Ch08RestAPI/Exam01Review/ReviewDetail?reviewId=${review.reviewId}`} className="rounded-lg border border-zinc-300 px-3 py-1.5 text-xs font-medium transition hover:bg-zinc-100 focus-visible:outline-2 focus-visible:outline-orange-500 dark:border-zinc-600 dark:hover:bg-zinc-800">
                취소
              </Link>
              <button type="submit" disabled={isSubmitting} className="cursor-pointer rounded-lg bg-orange-500 px-3 py-1.5 text-xs font-semibold text-white shadow-sm transition hover:bg-orange-600 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-orange-500 disabled:cursor-not-allowed disabled:opacity-60">
                {isSubmitting ? "저장 중..." : "수정 완료"}
              </button>
            </div>
          </form>
        </div>
      </div>
    </section>
  );
}
