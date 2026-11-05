"use client";

import ChildComponent from "@/app/Ch02Component/Exam07EventHandling/ChildComponent";

export default function Exam07EventHandling() {

  function handleClick1(event) {
    console.log(event.target.name)
  }

  function handleClick3(event, name) {
    console.log(event.target)
    console.log(`${name} 클릭함`)
  }

  function handleClick4() {
    console.log(`버튼 4를 클릭함`)
  }  

  return (
      <section className="w-full rounded-lg border border-zinc-200 bg-white text-zinc-900 shadow-sm dark:border-zinc-700 dark:bg-zinc-900 dark:text-zinc-100">
        <header className="rounded-t-lg border-b border-zinc-200 bg-zinc-100 px-4 py-1 dark:border-zinc-700 dark:bg-zinc-800">
          <h2 className="font-semibold">Exam07EventHandling</h2>
        </header>
        <div className="p-4 flex items-center gap-2">
          <button
              name="btn1"
              type="button"
              onClick={handleClick1}
              className="rounded bg-green-600 px-4 py-2 text-white hover:bg-green-700 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-600"
          >
            버튼1
          </button>
          <hr/>
          <button
              name="btn2"
              type="button"
              onClick={() => { console.log('버튼 2을 클릭함') }}
              className="rounded bg-green-600 px-4 py-2 text-white hover:bg-green-700 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-600"
          >
            버튼2
          </button>
          <hr/>
          <button
              type="button"
              onClick={(e) => handleClick3(e, '버튼 3')}
              className="rounded bg-green-600 px-4 py-2 text-white hover:bg-green-700 focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-green-600"
          >
            버튼3
          </button>
          <hr/>
          <div
              onClick={() => handleClick3('컨테이너')}
          >
            컨테이너
          </div>
        </div>
        <hr/>
        <ChildComponent onClick={handleClick4} />
      </section>
  );
}