'use client'

import {createContext, useState} from "react";

export const ColorContext = createContext();

export default function ColorContextProvider({children}) {

    const [color, setColor] = useState("black")
    const value = {
        color,
        setColor,
    }

    return (
        <ColorContext.Provider value={value}>
            {children}
        </ColorContext.Provider>
    )
}