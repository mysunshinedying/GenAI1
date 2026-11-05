"use client";

import { useState } from "react";

export default function Exam11RuntimeErrorHandling() {
    const [shouldThrow, setShouldThrow] = useState(false);

    if (shouldThrow) {
        throw new Error("Exam11에서 발생시킨 런타임 오류입니다.");
    }

    var name;

    return (
        <section className="m-6 overflow-hidden rounded-xl border border-rose-200 bg-white shadow-sm">
            <header className="border-b border-rose-200 bg-rose-50 px-5 py-4">
                <h1 className="text-lg font-bold text-slate-900">Exam11RuntimeErrorHandling</h1>
                <p className="mt-1 text-sm text-slate-600">오류가 발생하면 error.js의 대체 화면이 표시됩니다.</p>
            </header>
            <div className="space-y-4 p-5">
                런타임 오류 처리 예제
                {/*name.length}*/}
            </div>
        </section>
    );
}
