"use client";

import Link from "next/link";
import { useState } from "react";

export default function ReviewWrite() {
  const [errors, setErrors] = useState({});
  const [isValid, setIsValid] = useState(false);

  const validateTitle = (value) => {
    if (!value.trim()) return "제목을 입력해 주세요.";
    if (value.length > 200) return "제목은 200자 이하로 입력해 주세요.";
    return "";
  };

  const validateContent = (value) => {
    return value.trim() ? "" : "내용을 입력해 주세요.";
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const form = event.currentTarget;
    const data = new FormData(form);
    const nextErrors = {
      title: validateTitle(data.get("title")),
      content: validateContent(data.get("content")),
    };
    setErrors(nextErrors);
    const firstInvalidField = Object.keys(nextErrors).find((name) => nextErrors[name]);
    setIsValid(!firstInvalidField);
    if (firstInvalidField) form.elements.namedItem(firstInvalidField).focus();
  };

  return (
      <section className="min-w-0 w-full rounded-lg border border-zinc-200 bg-white text-zinc-900 shadow-sm dark:border-zinc-700 dark:bg-zinc-900 dark:text-zinc-100">
        <header className="rounded-t-lg border-b border-zinc-200 bg-zinc-100 px-4 py-1 dark:border-zinc-700 dark:bg-zinc-800">
          <h2 className="font-semibold">ReviewWrite</h2>
        </header>
        <div className="bg-zinc-50/60 p-4 sm:p-8 dark:bg-zinc-950/30">
          <div className="mx-auto max-w-[800px]">
            <div className="mb-6 border-l-4 border-orange-500 pl-4">
              <p className="text-xs font-semibold tracking-widest text-orange-600 dark:text-orange-400">REVIEW</p>
              <h3 className="mt-1 text-2xl font-bold tracking-tight">상품 후기 작성</h3>
              <p className="mt-2 text-sm leading-6 text-zinc-500 dark:text-zinc-400">직접 사용한 솔직한 후기를 들려주세요.</p>
            </div>
            <form encType="multipart/form-data" noValidate onSubmit={handleSubmit} onChange={() => setIsValid(false)} className="grid grid-cols-1 gap-4 rounded-2xl border border-zinc-200 bg-white p-5 shadow-sm sm:grid-cols-2 sm:p-5 dark:border-zinc-700 dark:bg-zinc-900">
              <div className="sm:col-span-2">
                <label htmlFor="title" className="mb-1 block text-sm font-semibold">제목</label>
                <input id="title" aria-invalid={Boolean(errors.title)} aria-describedby={errors.title ? "title-error" : undefined} name="title" type="text" required maxLength={200} placeholder="상품 사용 후기를 한 줄로 요약해 주세요 (최대 200자)" className="w-full rounded-lg border border-zinc-200 bg-zinc-50 px-3 py-2 text-sm outline-none transition placeholder:text-zinc-400 focus:border-orange-400 focus:bg-white focus:ring-2 focus:ring-orange-100 dark:border-zinc-600 dark:bg-zinc-800 dark:focus:bg-zinc-800 dark:focus:ring-orange-900/40" />
                {errors.title && <p id="title-error" role="alert" className="mt-1 text-xs text-red-600 dark:text-red-400">{errors.title}</p>}
              </div>
              <div className="sm:col-span-2">
                <label htmlFor="content" className="mb-1 block text-sm font-semibold">내용</label>
                <textarea id="content" aria-invalid={Boolean(errors.content)} aria-describedby={errors.content ? "content-error" : undefined} name="content" required rows={5} placeholder="사용 기간, 마음에 든 점, 아쉬운 점 등을 자유롭게 작성해 주세요." className="w-full rounded-lg border border-zinc-200 bg-zinc-50 px-3 py-2 text-sm outline-none transition placeholder:text-zinc-400 focus:border-orange-400 focus:bg-white focus:ring-2 focus:ring-orange-100 dark:border-zinc-600 dark:bg-zinc-800 dark:focus:bg-zinc-800 dark:focus:ring-orange-900/40" />
                {errors.content && <p id="content-error" role="alert" className="mt-1 text-xs text-red-600 dark:text-red-400">{errors.content}</p>}
              </div>
              <div>
                <label htmlFor="writer" className="mb-1 block text-sm font-semibold">글쓴이</label>
                <input id="writer" name="writer" type="text" value="user1" readOnly required maxLength={50} placeholder="가입된 회원 아이디를 입력하세요" className="w-full rounded-lg border border-zinc-200 bg-zinc-50 px-3 py-2 text-sm outline-none transition placeholder:text-zinc-400 focus:border-orange-400 focus:bg-white focus:ring-2 focus:ring-orange-100 dark:border-zinc-600 dark:bg-zinc-800 dark:focus:bg-zinc-800 dark:focus:ring-orange-900/40" />
              </div>
              <div>
                <label htmlFor="productId" className="mb-1 block text-sm font-semibold">상품 번호</label>
                <input id="productId" name="productId" type="number" value={1} readOnly required min={1} step={1} placeholder="후기를 작성할 상품 번호 (예: 1)" className="w-full rounded-lg border border-zinc-200 bg-zinc-50 px-3 py-2 text-sm outline-none transition placeholder:text-zinc-400 focus:border-orange-400 focus:bg-white focus:ring-2 focus:ring-orange-100 dark:border-zinc-600 dark:bg-zinc-800 dark:focus:bg-zinc-800 dark:focus:ring-orange-900/40" />
              </div>
              <div className="rounded-xl border border-dashed border-zinc-300 bg-zinc-50 p-3 sm:col-span-2 dark:border-zinc-600 dark:bg-zinc-800/40">
                <label htmlFor="file" className="mb-1 block text-sm font-semibold">📎 첨부파일 <span className="ml-1 text-xs font-normal text-zinc-500">선택</span></label>
                <p className="mb-3 text-xs text-zinc-500 dark:text-zinc-400">후기에 함께 올릴 사진이나 파일을 선택하세요.</p>
                <input id="file" name="file" type="file" className="block w-full text-sm file:mr-3 file:cursor-pointer file:rounded-lg file:border-0 file:bg-white file:px-4 file:py-2 file:font-medium file:text-orange-600 dark:file:bg-zinc-800 dark:file:text-zinc-100" />
              </div>
              {isValid && <p role="status" className="text-sm text-green-700 sm:col-span-2 dark:text-green-400">입력값 검사를 통과했습니다. 아직 서버로 전송하지 않았습니다.</p>}
              <div className="flex flex-wrap items-center justify-end gap-3 border-t border-zinc-100 pt-3 sm:col-span-2 dark:border-zinc-800">
                <Link href="/Ch08RestAPI/Exam01Review" className="rounded-lg border border-zinc-300 px-3 py-1.5 text-xs font-medium transition hover:bg-zinc-100 focus-visible:outline-2 focus-visible:outline-orange-500 dark:border-zinc-600 dark:hover:bg-zinc-800">
                  목록
                </Link>
                <button type="submit" className="rounded-lg bg-orange-500 px-3 py-1.5 text-xs font-semibold text-white shadow-sm transition hover:bg-orange-600 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-orange-500">후기 등록</button>
              </div>
            </form>
          </div>
        </div>
      </section>
  );
}
