"use client";

import {useContext} from "react";
import {ColorContext} from "@/contexts/ColorContext";

export default function UiComponentB() {
    //ColorContext 전역 상태를 사용하겠다.
    const colorContext = useContext(ColorContext);
    const alertColorClasses = {
        red: "border-red-300 bg-red-100 text-red-900",
        orange: "border-orange-300 bg-orange-100 text-orange-900",
        yellow: "border-yellow-300 bg-yellow-100 text-yellow-900",
        green: "border-green-300 bg-green-100 text-green-900",
        blue: "border-blue-300 bg-blue-100 text-blue-900",
        indigo: "border-indigo-300 bg-indigo-100 text-indigo-900",
        violet: "border-violet-300 bg-violet-100 text-violet-900",
    };

    return (
        <div className="rounded-lg border border-zinc-200 p-4">
            <h2 className="font-semibold">UiComponentB</h2>
            <div
                role="alert"
                className={`rounded border p-4 ${alertColorClasses[colorContext.color] ?? "border-zinc-300 bg-zinc-100 text-zinc-900"}`}
            >
                경고: 내용을 확인해 주세요.
            </div>
        </div>
    );
}
