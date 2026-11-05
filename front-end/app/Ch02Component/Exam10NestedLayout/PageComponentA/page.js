export default function PageComponentA() {
    return (
        <article className="rounded-lg border border-sky-200 bg-sky-50 p-5">
            <span className="text-xs font-semibold uppercase tracking-wider text-sky-700">Page A</span>
            <h2 className="mt-2 text-lg font-bold text-slate-900">PageComponentA</h2>
            <p className="mt-2 text-sm leading-6 text-slate-600">
                이 페이지는 Exam10NestedLayout의 공통 레이아웃 안에 표시됩니다.
            </p>
        </article>
    );
}
