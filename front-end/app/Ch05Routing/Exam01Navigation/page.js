'use client'

import { useRouter } from "next/navigation";

export default function Exam01Navigation() {

    const router = useRouter();
    const handleClick = () => {
        router.push('/Ch02Component');
    }

  return (
    <section className="m-6 overflow-hidden rounded-lg border border-gray-200 bg-white shadow-sm">
      <header className="border-b border-gray-200 bg-gray-50 px-5 py-3">
        <h1 className="text-lg font-semibold">Exam01Navigation</h1>
      </header>
      <div className="p-5">
          <button
              type="button"
              onClick={handleClick}
              className="rounded bg-orange-500 px-4 py-2 text-sm text-white hover:bg-orange-600"
          >
              버튼
          </button>
      </div>
    </section>
  );
}
