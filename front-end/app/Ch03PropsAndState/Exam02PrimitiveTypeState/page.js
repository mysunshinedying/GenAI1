'use client';

import { useState } from "react";

export default function Exam02PrimitiveTypeState() {
    const [number, setNumber] = useState(0);
    const [color, setColor] = useState("black");

    const addNumber = () => setNumber((current) => current + 1);
    const changeColor = () => {
        const randomColor = Math.floor(Math.random() * 0x1000000)
            .toString(16)
            .padStart(6, "0");
        setColor(`#${randomColor}`);
    };

    const buttonClass = "rounded-md px-3 py-1.5 text-sm font-medium text-white shadow-sm transition duration-200 hover:-translate-y-0.5 hover:shadow-md active:scale-95 focus-visible:outline-2 focus-visible:outline-offset-2";

    return (
        <section className="m-6 overflow-hidden rounded-xl border border-slate-200 bg-white shadow-sm">
            <header className="border-b border-slate-200 bg-slate-50 px-5 py-4">
                <h1 className="text-lg font-bold">Exam02PrimitiveTypeState</h1>
            </header>
            <div className="p-5">
                <h1 className="text-6xl transition-colors duration-500" style={{ color }}>
                    {number}
                </h1>
                <div className="mt-3 flex flex-wrap gap-2">
                    <button
                        type="button"
                        onClick={addNumber}
                        className={`${buttonClass} bg-indigo-600 hover:bg-indigo-700 focus-visible:outline-indigo-600`}
                    >
                        숫자 증가
                    </button>
                    <button
                        type="button"
                        onClick={changeColor}
                        className={`${buttonClass} bg-green-600 hover:bg-green-700 focus-visible:outline-green-600`}
                    >
                        색상 변경
                    </button>
                </div>
            </div>
        </section>
    );
}
