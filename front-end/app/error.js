"use client";

import { useEffect } from "react";
import Link from "next/link";

export default function Exam11Error({ error, retry }) {
    useEffect(() => {
        console.error(error);
    }, [error]);

    return (
        <section className="m-6 overflow-hidden rounded-xl border border-rose-300 bg-white shadow-sm">
            <header className="border-b border-rose-200 bg-rose-50 px-5 py-4">
                <p className="text-sm font-semibold text-rose-700">런타임 오류</p>
                <h1 className="mt-1 text-lg font-bold text-slate-900">페이지를 표시하지 못했습니다.</h1>
            </header>
            <div className="space-y-4 p-5">
                <p className="text-sm text-slate-600">오류를 복구한 뒤 다시 시도할 수 있습니다.</p>
                {error?.digest && (
                    <p className="text-xs text-slate-500">오류 식별자: {error.digest}</p>
                )}
                <div className="flex flex-wrap gap-3">
                    <button
                        type="button"
                        onClick={() => retry()}
                        className="rounded-lg bg-rose-600 px-4 py-2.5 text-sm font-semibold text-white transition hover:bg-rose-700 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-rose-600"
                    >
                        다시 시도
                    </button>
                    <Link
                        href="/"
                        className="rounded-lg border border-slate-300 bg-white px-4 py-2.5 text-sm font-semibold text-slate-700 transition hover:bg-slate-50 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-slate-400"
                    >
                        홈으로 이동
                    </Link>
                </div>
            </div>
        </section>
    );
}
