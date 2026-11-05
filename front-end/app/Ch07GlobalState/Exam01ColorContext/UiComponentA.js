"use client";

import {useContext} from "react";
import {ColorContext} from "@/contexts/ColorContext";

export default function UiComponentA() {

    const colorContext = useContext(ColorContext);
    const colors = ["red", "orange", "yellow", "green", "blue", "indigo", "violet"]

    const handleOnClick = (color) => {
        colorContext.setColor(color);
    }

    return (
        <div className="rounded-lg border border-zinc-200 p-4">
            <h2 className="font-semibold">UiComponentA</h2>
            <div className="p-5 flex gap-1">
                {colors.map((color, index) => (
                    <div key={index}
                         onClick={() => handleOnClick(color)}
                         style={{
                        backgroundColor: color,
                        width: "24px",
                        height: "24px",
                        cursor: "pointer"
                    }}>
                    </div>
                ))}
            </div>
            <hr/>
            <p>color 전역 상태값: {colorContext.color}</p>

        </div>
    );
}
