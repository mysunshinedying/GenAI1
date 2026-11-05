export default function Exam10NestedLayoutPage() {
    return (
        <div>
            <h2 className="text-base font-semibold text-slate-900">하위 페이지</h2>
            <p className="mt-2 text-sm leading-6 text-slate-600">
                이 내용은 <code className="rounded bg-slate-100 px-1.5 py-0.5 text-violet-700">layout.js</code>의
                <code className="mx-1 rounded bg-slate-100 px-1.5 py-0.5 text-violet-700">children</code>으로 전달됩니다.
                경로 아래에 페이지를 추가하면 공통 레이아웃 안에 렌더링됩니다.
            </p>
        </div>
    );
}
