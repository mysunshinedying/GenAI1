"use client";

import { useRef } from "react";

export default function Exam09DomRef() {

    const inputRef = useRef(null);

    const handleKeyDown = () => {
        inputRef.current.style.backgroundColor = 'orange';
        const inputRef2 = document.querySelector("input");
        console.log(inputRef2);
    }
    const handleClick = () => {
        console.log(inputRef.current.value);
    }

    return (
        <section className="m-6 overflow-hidden rounded-xl border border-slate-200 bg-white shadow-sm">
            <header className="border-b border-slate-200 bg-slate-50 px-5 py-4">
                <h1 className="text-lg font-bold text-slate-900">Exam09DomRef</h1>
                <p className="mt-1 text-sm text-slate-500">useRef로 DOM 요소에 접근합니다.</p>
            </header>
            <div className="space-y-4 p-5">
                <label htmlFor="dom-ref-input" className="block text-sm font-medium text-slate-700">
                    입력값
                </label>
                <input
                    id="dom-ref-input"
                    ref={inputRef}
                    onKeyDown={handleKeyDown}
                    type="text"
                    placeholder="텍스트를 입력하세요"
                    className="w-full rounded-lg border px-4 py-2.5  outline-none transition placeholder:text-slate-400 focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200"
                />
                <div className="flex flex-wrap gap-2">
                    <button
                        onClick={handleClick}
                        type="button"
                        className="rounded-lg bg-indigo-600 px-4 py-2 text-sm font-semibold text-white transition hover:bg-indigo-700 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                    >
                        확인
                    </button>
                    <button
                        type="button"
                        className="rounded-lg border border-slate-300 bg-white px-4 py-2 text-sm font-semibold text-slate-700 transition hover:bg-slate-50 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-slate-400"
                    >
                        입력값 지우기
                    </button>
                </div>
            </div>
        </section>
    );
}
