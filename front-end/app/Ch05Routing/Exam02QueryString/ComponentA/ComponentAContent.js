"use client";

import { useSearchParams } from "next/navigation";

export default function ComponentAContent() {
  const queryString = useSearchParams();
  const key1 = queryString.get("key1");
  const key2 = queryString.get("key2");

  return (
    <section className="m-6 overflow-hidden rounded-lg border border-gray-200 bg-white shadow-sm">
      <header className="border-b border-gray-200 bg-gray-50 px-5 py-3">
        <h1 className="text-lg font-semibold">ComponentA</h1>
      </header>
      <div className="flex flex-wrap gap-3 p-5">
        <p>key1: {key1}</p>
        <p>key2: {key2}</p>
      </div>
    </section>
  );
}
