export default function PageComponentB() {
    return (
        <article className="rounded-lg border border-amber-200 bg-amber-50 p-5">
            <span className="text-xs font-semibold uppercase tracking-wider text-amber-700">Page B</span>
            <h2 className="mt-2 text-lg font-bold text-slate-900">PageComponentB</h2>
            <p className="mt-2 text-sm leading-6 text-slate-600">
                Page A와 같은 layout.js를 공유하면서 본문만 다르게 렌더링합니다.
            </p>
        </article>
    );
}
