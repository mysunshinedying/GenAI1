'use client';

import Image from "next/image";

export default function ChildComponent({ imgFile, changeImg }) {
    return (
        <article className="rounded-lg border border-indigo-200 bg-indigo-50 p-4">
            <h3 className="text-sm font-semibold text-indigo-900">ChildComponent</h3>
            <div className="mt-3">
                <Image
                    alt="이미지"
                    src={`/images/${imgFile}`}
                    width={500}
                    height={300}
                    className="h-auto w-[200px] rounded-lg border border-white shadow-sm"
                />
                <button
                    type="button"
                    onClick={changeImg}
                    className="mt-3 inline-flex items-center gap-2 rounded-lg bg-indigo-600 px-4 py-2 text-sm font-semibold text-white shadow-sm transition duration-200 hover:-translate-y-0.5 hover:bg-indigo-700 hover:shadow-md active:translate-y-0 active:scale-95 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                >
                    이미지 변경
                </button>
            </div>
        </article>
    );
}
