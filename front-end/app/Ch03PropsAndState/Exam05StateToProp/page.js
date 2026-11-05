"use client";

import ChildComponent from "@/app/Ch03PropsAndState/Exam05StateToProp/ChildComponent";
import {useState} from "react";

export default function Exam05StateToProp(
) {
    const [imgFile, setImgFile] = useState("photo1.jpg");

    // const changeImage = (e) => {
    //     if (imgFile === 'photo1.jpg') {
    //         setImgFile('photo2.jpg');
    //     } else {
    //         setImgFile('photo1.jpg');
    //     }
    // }

    const changeImg = () => (imgFile === 'photo1.jpg' ? setImgFile('photo2.jpg') : setImgFile('photo1.jpg'));


    return (
        <section className="w-full rounded-lg border border-zinc-200 bg-white text-zinc-900 shadow-sm dark:border-zinc-700 dark:bg-zinc-900 dark:text-zinc-100">
            <header className="rounded-t-lg border-b border-zinc-200 bg-zinc-100 px-4 py-1 dark:border-zinc-700 dark:bg-zinc-800">
                <h2 className="font-semibold">Exam05StateToProp</h2>
            </header>
            <div className="p-4">
                <ChildComponent imgFile={imgFile} changeImg={changeImg}/>
            </div>
        </section>
    );
}
