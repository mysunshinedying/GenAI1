import { Suspense } from "react";
import ComponentAContent from "./ComponentAContent";

export default function ComponentA() {
  return (
    <Suspense
      fallback={
        <section className="m-6 rounded-lg border border-gray-200 bg-white p-5 text-sm text-zinc-600 shadow-sm">
          쿼리 정보를 불러오는 중...
        </section>
      }
    >
      <ComponentAContent />
    </Suspense>
  );
}
