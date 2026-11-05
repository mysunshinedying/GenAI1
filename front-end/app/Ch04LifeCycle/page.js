'use client'

import {useEffect, useState} from "react";

export default function Ch04LifeCycle() {

    const [count1, setCount1] = useState(1);
    const [count2, setCount2] = useState(101);

    useEffect(() => {
        console.log('마운트 상태')
        return () => {
            console.log('언마운트 상태')
        }
    }, [])

    useEffect(() => {
        console.log('마운트 상태/count1 업데이트 후 실행')
        return () => {
            console.log('언마운트 상태/count1 업데이트 전 실행')
        }
    }, [count1])


  return (
    <section className="m-6 overflow-hidden rounded-lg border border-gray-200 bg-white shadow-sm">
      <header className="border-b border-gray-200 bg-gray-50 px-5 py-3">
        <h1 className="text-lg font-semibold">Ch04LifeCycle</h1>
      </header>
      <div className="p-5">
          <button
              type="button"
              onClick={() => setCount1((prevCount) => prevCount + 1)}
              className="rounded bg-blue-600 px-3 py-2 text-white hover:bg-blue-700 focus:outline-none">
              count: {count1}
          </button>
          <button
              type="button"
              onClick={() => setCount2((prevCount) => prevCount + 1)}
              className="rounded bg-blue-600 px-3 py-2 text-white hover:bg-blue-700 focus:outline-none">
              count: {count2}
          </button>
      </div>
    </section>
  );
}
