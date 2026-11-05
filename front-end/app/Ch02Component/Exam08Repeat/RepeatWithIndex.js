export default function RepeatWithIndex() {
    const fruits = ["사과", "바나나", "포도", "딸기"];

    return (
        <section className="overflow-hidden rounded-xl border border-slate-200 bg-white shadow-sm">
            <header className="flex items-center justify-between border-b border-slate-200 bg-gradient-to-r from-emerald-50 to-white px-5 py-4">
                <div>
                    <h2 className="text-lg font-bold text-slate-900">과일 목록</h2>
                    <p className="mt-1 text-sm text-slate-500">map의 index 값과 함께 출력합니다.</p>
                </div>
                <span className="rounded-full bg-emerald-100 px-3 py-1 text-sm font-semibold text-emerald-700">
                    {fruits.length}개
                </span>
            </header>

            <ul className="space-y-2 p-5">
                {fruits.map((fruit, index) => (
                    <li
                        key={fruit}
                        className="flex items-center gap-4 rounded-lg border border-slate-100 bg-slate-50 px-4 py-3 transition-colors hover:border-emerald-200 hover:bg-emerald-50"
                    >
                        <span className="flex h-9 w-9 shrink-0 items-center justify-center rounded-full bg-white text-sm font-bold text-emerald-700 shadow-sm ring-1 ring-slate-200">
                            {index}
                        </span>
                        <span className="font-medium text-slate-800">{fruit}</span>
                    </li>
                ))}
            </ul>
        </section>
    );
}
