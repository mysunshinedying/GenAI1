"use client";

import UiComponentA from "@/app/Ch07GlobalState/Exam01ColorContext/UiComponentA";
import UiComponentB from "@/app/Ch07GlobalState/Exam01ColorContext/UiComponentB";

export default function Exam01ColorContext() {
  return (
    <section className="m-6 overflow-hidden rounded-lg border border-gray-200 bg-white shadow-sm">
      <header className="border-b border-gray-200 bg-gray-50 px-5 py-3">
        <h1 className="text-lg font-semibold">Exam01ColorContext</h1>
      </header>
      <div className="p-5">
          <UiComponentA/>
          <UiComponentB/>
      </div>
    </section>
  );
}
