export default function Exam10NestedLayout({ children }) {
    return (
        <section className="m-6 overflow-hidden rounded-xl border border-violet-200 bg-white shadow-sm">
            <header className="border-b border-violet-200 bg-violet-50 px-5 py-4">
                <p className="text-xs font-semibold uppercase tracking-wider text-violet-600">Ch02Component / Layout</p>
                <h1 className="mt-1 text-lg font-bold text-slate-900">Exam10NestedLayout</h1>
                <p className="mt-1 text-sm text-slate-600">이 영역은 중첩 레이아웃이 하위 페이지를 감싸서 표시합니다.</p>
            </header>
            <div className="grid gap-5 p-5 md:grid-cols-[200px_minmax(0,1fr)]">
                <aside className="rounded-lg bg-slate-50 p-4 text-sm text-slate-600">
                    <p className="font-semibold text-slate-800">공통 영역</p>
                    <p className="mt-2">같은 경로 아래 페이지에서 이 레이아웃을 공유합니다.</p>
                </aside>
                <main className="min-w-0 rounded-lg border border-slate-100 p-4">
                    {children}
                </main>
            </div>
        </section>
    );
}
