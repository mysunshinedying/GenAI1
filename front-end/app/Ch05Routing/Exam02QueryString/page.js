'use client'

import { useState } from "react";
import { useRouter } from "next/navigation";
import Link from "next/link";

export default function Exam02QueryString() {

  const router = useRouter();

  const [key1, setKey1] = useState('value1');
  const [key2, setKey2] = useState('value2');
  const handleClick = () => {
    router.push(`/Ch05Routing/Exam02QueryString/ComponentA?key1=${encodeURIComponent(key1)}&key2=${encodeURIComponent(key2)}`);
  }

  return (
    <section className="m-6 overflow-hidden rounded-lg border border-gray-200 bg-white shadow-sm">
      <header className="border-b border-gray-200 bg-gray-50 px-5 py-3">
        <h1 className="text-lg font-semibold">Exam02QueryString</h1>
      </header>
      <div className="flex flex-wrap gap-3 p-5">
        <Link
          href={`/Ch05Routing/Exam02QueryString/ComponentA?key1=${key1}&key2=${key2}`}
          className="inline-flex items-center rounded-md bg-orange-500 px-4 py-2 text-sm font-medium text-white shadow-sm transition-colors hover:bg-orange-600 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-orange-600"
        >
          Exam02QueryString(절대 경로)
        </Link>
        <Link
          href={`Exam02QueryString/ComponentA?key1=${key1}&key2=${key2}`}
          className="inline-flex items-center rounded-md bg-orange-500 px-4 py-2 text-sm font-medium text-white shadow-sm transition-colors hover:bg-orange-600 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-orange-600"
        >
          Exam02QueryString(상대 경로)
        </Link>
        <button
          type="button"
          onClick={handleClick}
          className="rounded-md bg-orange-500 px-4 py-2 text-sm font-medium text-white shadow-sm transition-colors hover:bg-orange-600 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-orange-600"
        >
          버튼1
        </button>
        <button
          type="button"
          onClick={() => setKey2((value) => `${value}-updated`)}
          className="rounded-md bg-orange-500 px-4 py-2 text-sm font-medium text-white shadow-sm transition-colors hover:bg-orange-600 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-orange-600"
        >
          key2 변경
        </button>        
      </div>
    </section>
  );
}
